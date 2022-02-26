package com.example.geektrust;

public class Member {

  private final String name;
  private String id;

  public Member(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
}