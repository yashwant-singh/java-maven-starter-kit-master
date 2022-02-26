package com.example.geektrust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        } else if (words.contains("SPEND")) {
          List<SpendFor> spendForList = new ArrayList<>();
          Map<String, Member> paidFor = new HashMap<>();
          
          for (int i = 3; i <= words.size(); i++) {
            spendForList.add(new SpendForMember(expenseManager.memberMap.get(words.get(i))));
          }
          ExpenseService.createExpense(Double.parseDouble(words.get(1)),
              expenseManager.memberMap.get(words.get(2)), spendForList);
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
