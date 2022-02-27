package com.example.geektrust;

import com.example.geektrust.bo.Member;
import com.example.geektrust.expence.ExpenseManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Main {


  public static void main(String[] args) {
    ExpenseManager expenseManager = new ExpenseManager();
    if (args.length > 0) {
      String filePath = args[0];
      readInput(expenseManager, filePath);
    }
  }

  private static void readInput(ExpenseManager expenseManager, String filePath) {

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = br.readLine();
      int count = 1;
      while (line != null) {
        List<String> words = Arrays.asList(line.split(" "));
        if (words.contains("MOVE_IN")) {
          expenseManager.addMember(new Member(Integer.toString(count++), words.get(1)));
        }
        if (words.contains("SPEND")) {
          Util.spend(words, expenseManager);
        }
        if (words.contains("DUES")) {
          expenseManager.showBalance(words.get(1));
        }
        if (words.contains("CLEAR_DUE")) {
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
  }
}
