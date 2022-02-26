package com.example.geektrust;

public abstract class SpendFor {

  double amount;
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

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}