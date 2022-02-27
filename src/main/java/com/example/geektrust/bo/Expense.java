package com.example.geektrust.bo;

import java.util.List;

public abstract class Expense {

  private String id;
  private final Integer amount;
  private final Member paidBy;
  private final List<SpendFor> spendFor;

  public Expense(int amount, Member paidBy, List<SpendFor> spendFor) {
    this.amount = amount;
    this.paidBy = paidBy;
    this.spendFor = spendFor;
  }

  public List<SpendFor> getSpendFor() {
    return spendFor;
  }

}