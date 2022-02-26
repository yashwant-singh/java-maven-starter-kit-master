package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

  final int PERSON_ALLOWED_IN_ROOM = 3;
  List<Expense> expenses;
  Map<String, Member> memberMap;
  Map<String, Map<String, Integer>> balanceSheet;

  public ExpenseManager() {
    expenses = new ArrayList<>();
    memberMap = new HashMap<>();
    balanceSheet = new HashMap<String, Map<String, Integer>>();
  }

  private boolean validateMember() {
    return memberMap.size() < PERSON_ALLOWED_IN_ROOM;
  }

  public void addMember(Member member) {
    if (!validateMember()) {
      System.out.println("HOUSEFUL");
      return;
    }
    memberMap.put(member.getName(), member);
    balanceSheet.put(member.getName(), new HashMap<String, Integer>());
    System.out.println("SUCCESS");
  }

  public void addExpense(Integer amount, String paidBy, List<SpendFor> spendFor) {
    Expense expense = ExpenseService.createExpense(amount, memberMap.get(paidBy), spendFor);
    expenses.add(expense);
    for (SpendFor split : expense.getSpendFor()) {
      String paidTo = split.getUser().getName();
      Map<String, Integer> balances = balanceSheet.get(paidBy);
      if (!balances.containsKey(paidTo)) {
        balances.put(paidTo, 0);
      }
      balances.put(paidTo, balances.get(paidTo) + split.getAmount());

      balances = balanceSheet.get(paidTo);
      if (!balances.containsKey(paidBy)) {
        balances.put(paidBy, 0);
      }
      balances.put(paidBy, split.getAmount() - balances.get(paidBy));
    }
  }

  public void showBalance(String userId) {
    boolean isEmpty = true;
    for (Map.Entry<String, Integer> userBalance : balanceSheet.get(userId).entrySet()) {
      if (userBalance.getValue() != 0) {
        isEmpty = false;
        printBalance(userId, userBalance.getKey(), userBalance.getValue());
      }
    }
  }

  public void showBalances() {
    boolean isEmpty = true;
    for (Map.Entry<String, Map<String, Integer>> allBalances : balanceSheet.entrySet()) {
      for (Map.Entry<String, Integer> userBalance : allBalances.getValue().entrySet()) {
        if (userBalance.getValue() > 0) {
          isEmpty = false;
          printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
        }
      }
    }
  }

  private void printBalance(String user1, String user2, Integer amount) {
    String user1Name = memberMap.get(user1).getName();
    String user2Name = memberMap.get(user2).getName();
    if (amount < 0) {
      System.out.println(user1Name + " " + Math.abs(amount));
    } else if (amount > 0) {
      System.out.println(user2Name + " " + Math.abs(amount));
    }
  }

  public void clearDues(String paidBy, String paidTo, Integer amount) {
    Map<String, Integer> balances = balanceSheet.get(paidBy);
    Integer dues = balances.get(paidTo);
    if (dues >= amount) {
      balances.put(paidTo, dues - amount);
      System.out.println(dues - amount);
    } else {
      System.out.println("INCORRECT_PAYMENT");
    }
  }

  public void movingOut(String memberName) {
    for (Map.Entry<String, Integer> userBalance : balanceSheet.get(memberName).entrySet()) {
      if (userBalance.getValue() != 0) {
        System.out.println("FAILURE");
        return;
      }
    }
    System.out.println("SUCCESS");
  }
}