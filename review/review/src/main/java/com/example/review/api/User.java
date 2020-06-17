package com.example.review.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private String userid;
  private List<Review> reviews;
  public User(final String userid) {
      this.userid= userid;
  }
}
