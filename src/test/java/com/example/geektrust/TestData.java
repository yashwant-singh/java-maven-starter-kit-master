package com.example.geektrust;

import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.expence.ExpenseManager;
import com.example.geektrust.member.SpendForMember;
import java.util.ArrayList;
import java.util.List;

public class TestData {

  public static interface Const {
    String USER1 = "ANDY";
    String USER2 = "WOODY";
    String USER3 = "BO";
    String USER4 = "RX";
  }

  public static List<SpendFor> getSpendForList(ExpenseManager expenseManager) {
    List<SpendFor> spendForList = new ArrayList<>();
    spendForList.add(new SpendForMember(expenseManager.getMemberMap().get(Const.USER2)));
    spendForList.add(new SpendForMember(expenseManager.getMemberMap().get(Const.USER3)));
    return spendForList;
  }
}
