package com.example.geektrust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

  static final String FILE_NAME = "input1.txt";
  static final String PARENT_FOLDER = "sample_input";

  public static void main(String[] args) {
    ExpenseManager expenseManager = new ExpenseManager();
    readInput(expenseManager, FILE_NAME);
  }

  private static void readInput(ExpenseManager expenseManager, String fileName) {

    try (BufferedReader br = new BufferedReader(new FileReader(PARENT_FOLDER +
        File.separator + fileName))) {
      String line = br.readLine();
      int count = 1;
      while (line != null) {
        List<String> words = Arrays.asList(line.split(" "));
        if (words.contains("MOVE_IN")) {
          expenseManager.addMember(new Member(Integer.toString(count++), words.get(1)));
        }
        if (words.contains("SPEND")) {
          List<SpendFor> spendForList = new ArrayList<>();
          boolean isMemberExists = true;
          for (int i = words.size() - 1; i > 1; i--) {
            Member member = expenseManager.memberMap.get(words.get(i));
            if (member != null) {
              spendForList.add(new SpendForMember(expenseManager.memberMap.get(words.get(i))));
            } else {
              isMemberExists = false;
              System.out.println("MEMBER_NOT_FOUND");
            }
          }
          if (isMemberExists) {
            if (spendForList.size() != expenseManager.memberMap.size()) {
              for (Map.Entry<String, Member> entry : expenseManager.memberMap.entrySet()) {
                Member member = entry.getValue();
                if (!words.contains(member.getName())) {
                  spendForList.add(
                      new SpendForMember(member));
                }
              }
            }
            expenseManager.addExpense(Integer.parseInt(words.get(1)), words.get(2), spendForList);
            System.out.println("SUCCESS");
          }
        }
        if (words.contains("DUES")) {
          expenseManager.showBalance(words.get(1));
        }
        if(words.contains("CLEAR_DUE")) {
          expenseManager.clearDues(words.get(1), words.get(2), Integer.parseInt(words.get(3)));
        }
        if (words.contains("MOVE_OUT")) {
          expenseManager.movingOut(words.get(1));
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // expenseManager.showBalances();
  }
}
