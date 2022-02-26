package com.example.geektrust;

import java.util.List;

public class MemberExpense extends Expense {

  public MemberExpense(double amount, Member paidBy, List<SpendFor> spendFor) {
    super(amount, paidBy, spendFor);
  }
}