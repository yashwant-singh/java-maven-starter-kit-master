package com.example.geektrust.member;

import com.example.geektrust.bo.Expense;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.bo.Member;
import java.util.List;

public class MemberExpense extends Expense {

  public MemberExpense(Integer amount, Member paidBy, List<SpendFor> spendFor) {
    super(amount, paidBy, spendFor);
  }
}