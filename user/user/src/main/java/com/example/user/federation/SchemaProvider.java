package com.example.user.federation;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.coxautodev.graphql.tools.SchemaParserBuilder;
import com.example.user.api.User;
import com.example.user.data.Data;
import com.example.user.resolver.Query;
import graphql.schema.GraphQLSchema;
import graphql.servlet.config.DefaultGraphQLSchemaProvider;
import graphql.servlet.config.GraphQLSchemaProvider;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchemaProvider extends DefaultGraphQLSchemaProvider implements GraphQLSchemaProvider {

  public SchemaProvider(Data data) throws Exception {
    super(
        Federation.transform(buildSchema())
            .fetchEntities(
                env ->
                    env.<List<Map<String, Object>>>getArgument(_Entity.argumentName).stream()
                        .map(
                            values -> {
                              if ("User".equals(values.get("__typename"))) {
                                final Object id = values.get("userid");
                                if (id instanceof String) {
                                  return lookupUser((String) id);
                                }
                              }
                              return null;
                            })
                        .collect(Collectors.toList()))
            .resolveEntityType(
                env -> {
                  final Object src = env.getObject();
                  if (src instanceof User) {
                    return env.getSchema().getObjectType("User");
                  }
                  return null;
                })
            .build());
  }
  // https://github.com/graphql-java-kickstart/graphql-java-tools/issues/300
  private static GraphQLSchema buildSchema() throws Exception {
    SchemaParserBuilder parserBuilder = com.coxautodev.graphql.tools.SchemaParser.newParser();
    /* parserBuilder.schemaString(
    CharStreams.toString(new InputStreamReader(sdl.getInputStream(), StandardCharsets.UTF_8)));*/
    parserBuilder.files("graphql/user.graphqls");
    parserBuilder.resolvers(new Query());
    GraphQLSchema schema = parserBuilder.build().makeExecutableSchema();
    return GraphQLSchema.newSchema(schema).clearAdditionalTypes().build();
  }

  private static User lookupUser(String id) {
    return Data.getUsers().stream().filter(var -> var.getUserid().equals(id)).findAny().get();
  }
}
