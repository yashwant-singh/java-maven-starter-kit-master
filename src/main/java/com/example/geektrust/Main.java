package com.example.geektrust;

import com.example.geektrust.bo.Member;
import com.example.geektrust.expence.ExpenseManager;
import com.example.geektrust.util.GeneralUtil;
import com.example.geektrust.util.GeneralUtil.Const;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The type Main.
 *
 * @author Yashwant
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
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
        List<String> words = Arrays.asList(line.split(GeneralUtil.Const.SINGLE_SPACE));
        if (words.contains(GeneralUtil.Const.MOVE_IN)) {
          expenseManager.addMember(new Member(Integer.toString(count++),
              words.get(GeneralUtil.Const.MOVE_IN_MEMBER_NAME_INDEX)));
        }
        if (words.contains(GeneralUtil.Const.SPEND)) {
          GeneralUtil.spend(words, expenseManager);
        }
        if (words.contains(GeneralUtil.Const.DUES)) {
          expenseManager.memberDues(words.get(GeneralUtil.Const.DUES_MEMBER_NAME_INDEX));
        }
        if (words.contains(GeneralUtil.Const.CLEAR_DUE)) {
          expenseManager.clearDues(words.get(GeneralUtil.Const.CLEAR_DUE_PAID_BY_INDEX), words.get(
                  Const.CLEAR_DUE_PAID_TO_INDEX),
              Integer.parseInt(words.get(Const.CLEAR_DUE_PAID_AMT_INDEX)));
        }
        if (words.contains(GeneralUtil.Const.MOVE_OUT)) {
          expenseManager.movingOut(words.get(GeneralUtil.Const.MOVE_OUT_MEMBER_NAME_INDEX));
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
