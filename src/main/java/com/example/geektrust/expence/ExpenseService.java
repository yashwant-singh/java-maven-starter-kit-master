package com.example.geektrust.expence;

import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.bo.Expense;
import com.example.geektrust.bo.Member;
import com.example.geektrust.member.MemberExpense;
import java.util.List;

public class ExpenseService {

  public static Expense createExpense(Integer amount, Member paidBy, List<SpendFor> spendFor) {

    int totalSplits = spendFor.size() + 1;
    Integer splitAmount = amount / totalSplits;
    for (SpendFor split : spendFor) {
      split.setAmount(splitAmount);
    }
    return new MemberExpense(amount, paidBy, spendFor);
  }
}