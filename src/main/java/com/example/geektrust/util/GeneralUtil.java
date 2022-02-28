package com.example.geektrust.util;

import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.expence.ExpenseManager;
import com.example.geektrust.member.SpendForMember;
import java.util.ArrayList;
import java.util.List;

/**
 * The type General util.
 *
 * @author Yashwant
 */
public class GeneralUtil {

  /**
   * Spend.
   *
   * @param words          the words
   * @param expenseManager the expense manager
   */
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

  /**
   * The interface Const.
   */
  public interface Const {

    /**
     * The constant SINGLE_SPACE.
     */
    String SINGLE_SPACE = " ";
    /**
     * The constant MOVE_IN.
     */
    String MOVE_IN = "MOVE_IN";
    /**
     * The constant SPEND.
     */
    String SPEND = "SPEND";
    /**
     * The constant DUES.
     */
    String DUES = "DUES";
    /**
     * The constant CLEAR_DUE.
     */
    String CLEAR_DUE = "CLEAR_DUE";
    /**
     * The constant MOVE_OUT.
     */
    String MOVE_OUT = "MOVE_OUT";
    /**
     * The constant DUES_MEMBER_NAME_INDEX.
     */
    int DUES_MEMBER_NAME_INDEX = 1;
    /**
     * The constant CLEAR_DUE_PAID_BY_INDEX.
     */
    int CLEAR_DUE_PAID_BY_INDEX = 1;
    /**
     * The constant CLEAR_DUE_PAID_TO_INDEX.
     */
    int CLEAR_DUE_PAID_TO_INDEX = 2;
    /**
     * The constant CLEAR_DUE_PAID_AMT_INDEX.
     */
    int CLEAR_DUE_PAID_AMT_INDEX = 3;
    /**
     * The constant MOVE_OUT_MEMBER_NAME_INDEX.
     */
    int MOVE_OUT_MEMBER_NAME_INDEX = 1;
    /**
     * The constant MOVE_IN_MEMBER_NAME_INDEX.
     */
    int MOVE_IN_MEMBER_NAME_INDEX = 1;
  }
}
