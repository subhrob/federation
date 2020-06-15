package com.example.review.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.review.api.Review;
import com.example.review.data.Data;
import java.util.List;

public class Query implements GraphQLQueryResolver {

  public List<Review> getReviews() {
    return Data.getReviews();
  }

  public Review getReivew(final String id) {
    return Data.getReviews().stream().filter(var -> var.getReviewid().equals(id)).findAny().get();
  }
}
