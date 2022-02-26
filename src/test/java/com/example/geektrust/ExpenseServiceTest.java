package com.example.geektrust;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseServiceTest {

	@Test
	public void createExpense() {

		List<SpendFor> splits = new ArrayList<>();
		splits.add(new SpendForMember(new Member("1", "ANDY")));
		splits.add(new SpendForMember(new Member("2", "BO")));

		Expense spend = ExpenseService.createExpense(3000, new Member("1", "WOODY"), splits);

		Assertions.assertEquals(3000, spend.getAmount());

	}
}