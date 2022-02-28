package com.example.geektrust.expence;

import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.bo.Expense;
import com.example.geektrust.bo.Member;
import com.example.geektrust.member.MemberExpense;
import java.util.List;

/**
 * The type Expense service.
 *
 * @author Yashwant
 */
public class ExpenseService {

  /**
   * Create expense expense.
   *
   * @param amount   the amount
   * @param paidBy   the paid by
   * @param spendFor the spend for
   * @return the expense
   */
  public static Expense createExpense(Integer amount, Member paidBy, List<SpendFor> spendFor) {

    int totalSplits = spendFor.size() + 1;
    Integer splitAmount = amount / totalSplits;
    for (SpendFor split : spendFor) {
      split.setAmount(splitAmount);
    }
    return new MemberExpense(amount, paidBy, spendFor);
  }
}