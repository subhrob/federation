package com.example.user.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.user.api.User;
import com.example.user.data.Data;
import java.util.List;

public class Query implements GraphQLQueryResolver {

  public List<User> getUsers() {
    return Data.getUsers();
  }

  public User getUser(final String id) {
    return Data.getUsers().stream().filter(var -> var.getUserid().equals(id)).findAny().get();
  }
}
