package com.example.geektrust.bo;

public abstract class SpendFor {

  Integer amount;
  private final Member user;

  public SpendFor(Member user) {
    this.user = user;
  }

  public Member getUser() {
    return user;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}