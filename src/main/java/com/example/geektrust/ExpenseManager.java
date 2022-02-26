package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

  final int PERSON_ALLOWED_IN_ROOM = 3;
  List<Expense> expenses;
  Map<String, Member> memberMap;
  Map<String, Map<String, Double>> balanceSheet;

  public ExpenseManager() {
    expenses = new ArrayList<>();
    memberMap = new HashMap<>();
    balanceSheet = new HashMap<String, Map<String, Double>>();
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
    balanceSheet.put(member.getName(), new HashMap<String, Double>());
    System.out.println("SUCCESS");
  }

  public void addExpense(double amount, String paidBy, List<SpendFor> spendFor) {
    Expense expense = ExpenseService.createExpense(amount, memberMap.get(paidBy), spendFor);
    expenses.add(expense);
    for (SpendFor split : expense.getSpendFor()) {
      String paidTo = split.getUser().getId();
      Map<String, Double> balances = balanceSheet.get(paidBy);
      if (!balances.containsKey(paidTo)) {
        balances.put(paidTo, 0.0);
      }
      balances.put(paidTo, balances.get(paidTo) + split.getAmount());

      balances = balanceSheet.get(paidTo);
      if (!balances.containsKey(paidBy)) {
        balances.put(paidBy, 0.0);
      }
      balances.put(paidBy, balances.get(paidBy) - split.getAmount());
    }
  }

  public void showBalance(String userId) {
    boolean isEmpty = true;
    for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
      if (userBalance.getValue() != 0) {
        isEmpty = false;
        printBalance(userId, userBalance.getKey(), userBalance.getValue());
      }
    }

    if (isEmpty) {
      System.out.println("No balances");
    }
  }

  public void showBalances() {
    boolean isEmpty = true;
    for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
      for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
        if (userBalance.getValue() > 0) {
          isEmpty = false;
          printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
        }
      }
    }

    if (isEmpty) {
      System.out.println("No balances");
    }
  }

  private void printBalance(String user1, String user2, double amount) {
    String user1Name = memberMap.get(user1).getName();
    String user2Name = memberMap.get(user2).getName();
    if (amount < 0) {
      System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
    } else if (amount > 0) {
      System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
    }
  }
}