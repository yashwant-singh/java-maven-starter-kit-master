package com.example.geektrust.expence;

import com.example.geektrust.Util;
import com.example.geektrust.bo.DuesSheet;
import com.example.geektrust.bo.Expense;
import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    if (spendForList.size() == 1) {
      adjustAmount(paidBy, spendForList.get(0).getUser().getName());
    }
  }

  private void adjustAmount(String paidBy, String paidTo) {
    Map<String, Integer> paidByDue = balanceSheet.get(paidBy);
    Map<String, Integer> paidToDue = balanceSheet.get(paidTo);
    String paidBykey = (String) paidByDue.keySet().toArray()[0];
    Integer paidByValue = paidByDue.get(paidBykey);
    if (paidToDue.containsKey(paidBykey)) {
      Integer val = paidToDue.get(paidBy);
      Integer balance = paidByValue - val;
      if (Math.signum(balance) == -1) {
        paidToDue.put(paidBy, paidToDue.get(paidBy) - val - balance);
        paidToDue.put(paidBykey, paidToDue.get(paidBykey) + val + balance);
        paidByDue.put(paidBykey, 0);
      } else {
        paidToDue.put(paidBy, paidToDue.get(paidBy) - val);
        paidToDue.put(paidBykey, paidToDue.get(paidBykey) + val);
        paidByDue.put(paidBykey, balance);
      }
    }
  }

  public void showBalance(String userId) {
    List<String> s = new ArrayList<String>(memberMap.keySet());
    s.remove(userId);
    Map<String, Integer> m = balanceSheet.get(userId);
    if (m == null) {
      System.out.println("MEMBER_NOT_FOUND");
      return;
    }
    List<DuesSheet> values = new ArrayList<>();
    for (Map.Entry<String, Integer> userBalance : balanceSheet.get(userId).entrySet()) {
      s.remove(userBalance.getKey());
      Integer v = userBalance.getValue();
      values.add(new DuesSheet(v, userBalance.getKey()));
    }
    if (s.size() == 1) {
      values.add(new DuesSheet(0, s.get(0)));
    }
    printMemberDues(values);
  }

  private void printMemberDues(List<DuesSheet> values) {
    String msg = "%s%s%s";
    Collections.sort(values, Collections.reverseOrder());
    int i = 0;
    while (i < values.size() - 1) {
      DuesSheet curVal = values.get(i);
      DuesSheet nextVal = values.get(i + 1);
      if (curVal.getAmount().equals(nextVal.getAmount())) {
        i = i + 2;
        if (curVal.getName().compareTo(nextVal.getName()) > 0) {
          print(nextVal.getName(), nextVal.getAmount());
          print(curVal.getName(), curVal.getAmount());
          continue;
        } else {
          print(curVal.getName(), curVal.getAmount());
          print(nextVal.getName(), nextVal.getAmount());
        }
      } else {
        print(curVal.getName(), curVal.getAmount());
        i++;
      }
    }
    if (values.size() != i) {
      DuesSheet curVal = values.get(i);
      System.out.println(curVal.getName() + " " + curVal.getAmount());
    }
  }

  private void print(String name, Integer amt) {
    System.out.println(
        String.format("%s%s%s", name, Util.SINGLE_SPACE, amt));
  }

  private void printBalance(String user1, String user2, Integer amount) {
    String user2Name = memberMap.get(user2).getName();
    System.out.println(user2Name + " " + Math.abs(amount));
  }

  public void clearDues(String paidBy, String paidTo, Integer amount) {
    if (!(memberMap.containsKey(paidBy) && memberMap.containsKey(paidTo))) {
      System.out.println("MEMBER_NOT_FOUND");
      return;
    }
    Map<String, Integer> balances = balanceSheet.get(paidBy);
    Integer dues = balances.get(paidTo);
    if (Objects.nonNull(dues) && dues >= amount) {
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
    for (Map.Entry<String, Member> memberEntry : memberMap.entrySet()) {
      Map<String, Integer> balance = balanceSheet.get(memberEntry.getKey());
      if (balance.containsKey(memberName)) {
        if (balance.get(memberName) > 0) {
          System.out.println("FAILURE");
          return;
        }
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

}