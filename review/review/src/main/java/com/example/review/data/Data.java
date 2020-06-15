package com.example.review.data;

import com.example.review.api.Review;
import com.example.review.api.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Data {
  private static List<Review> reviews = new ArrayList<>();
  private static List<User> users = new ArrayList<>();

  public Data() {
    reviews.add(new Review("R1", "Excellent", new User("1")));
    reviews.add(new Review("R2", "Bavo", new User("1")));
    reviews.add(new Review("R3", "Amazing", new User("2")));
    reviews.add(new Review("R4", "Super", new User("3")));
    reviews.add(new Review("R5", "Nice", new User("3")));
    reviews.add(new Review("R6", "Cool", new User("3")));

    users.add(new User("1"));
    users.add(new User("2"));
    users.add(new User("3"));
  }

  public static List<Review> getReviews() {
    return reviews;
  }

  public static List<User> getUsers() {
    return users;
  }
}
