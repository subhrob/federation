package com.example.user.data;

import com.example.user.api.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Data {
  private static List<User> users = new ArrayList<>();

  public Data() {
    users.add(new User("1", "Cameron Ellis"));
    users.add(new User("2", "Bradley Hayes"));
    users.add(new User("3", "Charlotte Rose"));
  }

  public static List<User> getUsers() {
    return users;
  }
}
