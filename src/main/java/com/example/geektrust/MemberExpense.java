package com.example.geektrust;

import java.util.List;

public class MemberExpense extends Expense {

  public MemberExpense(Integer amount, Member paidBy, List<SpendFor> spendFor) {
    super(amount, paidBy, spendFor);
  }
}