package com.example.geektrust;

import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.expence.ExpenseManager;
import com.example.geektrust.member.SpendForMember;
import java.util.ArrayList;
import java.util.List;

public class Util {

  public static final String SINGLE_SPACE = " ";

  public static void spend(List<String> words, ExpenseManager expenseManager) {
    List<SpendFor> spendForList = new ArrayList<>();
    boolean isMemberExists = true;
    for (int i = words.size() - 1; i > 2; i--) {
      Member member = expenseManager.getMemberMap().get(words.get(i));
      if (member != null) {
        spendForList.add(new SpendForMember(expenseManager.getMemberMap().get(words.get(i))));
      } else {
        isMemberExists = false;
        System.out.println("MEMBER_NOT_FOUND");
      }
    }
    if (isMemberExists) {
      expenseManager.addExpense(Integer.parseInt(words.get(1)), words.get(2), spendForList);
      System.out.println("SUCCESS");
    }
  }
}
