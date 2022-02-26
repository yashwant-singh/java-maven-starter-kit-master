package com.example.geektrust;

public abstract class SpendFor {

  Integer amount;
  private Member user;

  public SpendFor(Member user) {
    this.user = user;
  }

  public Member getUser() {
    return user;
  }

  public void setUser(Member user) {
    this.user = user;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}