package com.example.geektrust;

import java.util.List;

public class ExpenseService {

  public static Expense createExpense(Integer amount, Member paidBy, List<SpendFor> spendFor) {

    int totalSplits = spendFor.size();
    Integer splitAmount = amount / spendFor.size();
    for (SpendFor split : spendFor) {
      split.setAmount(splitAmount);
    }
    spendFor.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
    return new MemberExpense(amount, paidBy, spendFor);
  }
}