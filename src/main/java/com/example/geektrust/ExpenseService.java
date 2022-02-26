package com.example.geektrust;

import java.util.List;

public class ExpenseService {

  public static Expense createExpense(double amount, Member paidBy, List<SpendFor> spendFor) {

    int totalSplits = spendFor.size();
    double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
    for (SpendFor split : spendFor) {
      split.setAmount(splitAmount);
    }
    spendFor.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
    return new MemberExpense(amount, paidBy, spendFor);
  }
}