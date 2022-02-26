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

  public void addExpense(Integer amount, String paidBy, List<SpendFor> spendForList) {
    Expense expense = ExpenseService.createExpense(amount, memberMap.get(paidBy), spendForList);
    for (SpendFor spendFor : expense.getSpendFor()) {
      Map<String, Integer> balances = balanceSheet.get(spendFor.getUser().getName());
      if (!balances.containsKey(paidBy)) {
        balances.put(paidBy, 0);
      }
      balances.put(paidBy, balances.get(paidBy) + spendFor.getAmount());
    }
    if(spendForList.size() == 1) {
      adjustAmount(paidBy, spendForList.get(0).getUser().getName() );
    }
  }

  private void adjustAmount(String paidBy, String paidTo) {
    Map<String, Integer> paidByBalance = balanceSheet.get(paidBy);
    Map<String, Integer> paidToBalance = balanceSheet.get(paidTo);
    String pkey = (String) paidByBalance.keySet().toArray()[0];
    Integer pValue = paidByBalance.get(pkey);
    if (paidToBalance.containsKey(pkey)) {
      Integer val = paidToBalance.get(paidBy);
        Integer balance = pValue - val;
        paidToBalance.put(paidBy, paidToBalance.get(paidBy) - val);
        paidToBalance.put(pkey, paidToBalance.get(pkey) + val);
        paidByBalance.put(paidBy, balance);
    }
  }

  public void showBalance(String userId) {
    for (Map.Entry<String, Integer> userBalance : balanceSheet.get(userId).entrySet()) {
      if (userBalance.getValue() != 0) {
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
  public void removeMember(String memberName) {
    for (Map.Entry<String, Integer> userBalance : balanceSheet.get(memberName).entrySet()) {
      if (userBalance.getValue() != 0) {
        System.out.println("FAILURE");
        return;
      }
    }
    memberMap.remove(memberName);
    balanceSheet.remove(memberName, new HashMap<String, Double>());
    System.out.println("SUCCESS");
  }

  public Map<String, Member> getMemberMap() {
    return memberMap;
  }

  public Map<String, Map<String, Integer>> getBalanceSheet() {
    return balanceSheet;
  }

  private void adjustment(String paidBy, List<SpendFor> spendForList) {
    Map<String, Integer> balances = balanceSheet.get(paidBy);
    /*SpendFor sp = (SpendFor) spendForList.stream().filter(spendFor -> !spendFor.getUser().getName().equals(paidBy));
    for (Map.Entry<String, Member> memberEntry : memberMap.entrySet()) {
      if(!(memberEntry.getKey().equals(paidBy) || memberEntry.getKey().equals(sp.getUser().getName()))) {
        Integer xPersonValue = balances.get(memberEntry.getKey());
        Integer sharedAmt = sp.getAmount();
        Integer remAmt = xPersonValue - sharedAmt;
          balances.put(sp.getUser().getName(), remAmt);
      }
    }*/
  }
}