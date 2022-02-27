package com.example.geektrust.expence;

import com.example.geektrust.TestData;
import com.example.geektrust.TestData.Const;
import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.member.SpendForMember;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class ExpenseManagerTest {

  ExpenseManager expenseManager = new ExpenseManager();

  @Test
  public void testAddMember() {
    expenseManager.addMember(new Member("1", TestData.Const.USER1));
    expenseManager.addMember(new Member("2", TestData.Const.USER2));
    expenseManager.addMember(new Member("3", TestData.Const.USER3));
    Assert.assertTrue(expenseManager.getMemberMap().size() == 3);
    expenseManager.addMember(new Member("4", TestData.Const.USER4));
    Assert.assertTrue(expenseManager.getMemberMap().size() == 3);
  }

  @Test
  public void testAddExpense() {
    testAddMember();
    expenseManager.addExpense(3000, Const.USER1, TestData.getSpendForList(expenseManager));
    Assert.assertTrue(expenseManager.getBalanceSheet().get(Const.USER2).get(Const.USER1) == 1000);
  }

  @Test
  public void testMemberDues() {
    testAddExpense();
    expenseManager.memberDues(Const.USER2);
  }

  @Test
  public void testClearDues() {
    testAddExpense();
    expenseManager.clearDues(Const.USER2, Const.USER1, 500);
  }

  @Test
  public void testMovingOut() {
    testAddExpense();
    expenseManager.movingOut(Const.USER1);
  }

  @Test
  public void testRemoveMember() {
    testAddExpense();
    expenseManager.removeMember(Const.USER1);
  }

  @Test
  public void testAddExpenseTwo() {
    testAddExpense();
    SpendFor s1 = new SpendForMember(new Member("2", Const.USER3));
    List<SpendFor> spendForList = new ArrayList<>();
    spendForList.add(s1);
    expenseManager.addExpense(300, Const.USER2, spendForList);

  }
}
