package com.example.geektrust;

import com.example.geektrust.bo.Expense;
import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;
import com.example.geektrust.expence.ExpenseService;
import com.example.geektrust.member.SpendForMember;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExpenseServiceTest {

  @Test
  public void createExpense() {

    List<SpendFor> splits = new ArrayList<>();
    splits.add(new SpendForMember(new Member("1", "ANDY")));
    splits.add(new SpendForMember(new Member("2", "BO")));

    Expense spend = ExpenseService.createExpense(3000, new Member("1", "WOODY"), splits);

  }
}