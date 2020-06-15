package com.example.review.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {
  private String reviewid;
  private String review;
  private User user;
}
