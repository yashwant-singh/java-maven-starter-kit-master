package com.example.geektrust.member;

import com.example.geektrust.bo.Expense;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.bo.Member;
import java.util.List;

/**
 * The type Member expense.
 *
 * @author Yashwant
 */
public class MemberExpense extends Expense {

  /**
   * Instantiates a new Member expense.
   *
   * @param amount   the amount
   * @param paidBy   the paid by
   * @param spendFor the spend for
   */
  public MemberExpense(Integer amount, Member paidBy, List<SpendFor> spendFor) {
    super(amount, paidBy, spendFor);
  }
}