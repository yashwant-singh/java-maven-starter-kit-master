package com.example.geektrust.bo;

public class Member {

  private final String name;
  private String id;

  public Member(String id, String name) {
    this.id = id;
    this.name = name;
  }
  public String getName() {
    return name;
  }
}