package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseManagerTest {

	@Test
	public void removeMember() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member("1", "ANDY"));
		manager.addMember(new Member("2", "BO"));
		manager.addMember(new Member("3", "REX"));

		manager.removeMember("ANDY");

		Assertions.assertEquals(2, manager.getMemberMap().size());

	}

	@Test
	public void showDues() {

		ExpenseManager manager = new ExpenseManager();

		manager.showBalance("ANDY");
		Map<String, Integer> map = new HashMap<>();
		map.put("ANDY", 1000);

		Assertions.assertEquals(false, manager.getBalanceSheet().containsKey("ANDY"));

	}

	@Test
	public void clearDues() {

		ExpenseManager manager = new ExpenseManager();

		manager.clearDues("ANDY", "BO", 1000);

		Assertions.assertEquals(1, manager.getMemberMap().size());

	}

	@Test
	public void addMember() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member("1", "ANDY"));

		Assertions.assertEquals("2", manager.getMemberMap().size());

	}

}