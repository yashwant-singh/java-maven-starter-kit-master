package com.example.geektrust;

import java.util.List;

public abstract class Expense {

  private String id;
  private double amount;
  private Member paidBy;
  private List<SpendFor> spendFor;

  public Expense(double amount, Member paidBy, List<SpendFor> spendFor) {
    this.amount = amount;
    this.paidBy = paidBy;
    this.spendFor = spendFor;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Member getPaidBy() {
    return paidBy;
  }

  public void setPaidBy(Member paidBy) {
    this.paidBy = paidBy;
  }

  public List<SpendFor> getSpendFor() {
    return spendFor;
  }

  public void setSpendFor(List<SpendFor> spendFor) {
    this.spendFor = spendFor;
  }

}