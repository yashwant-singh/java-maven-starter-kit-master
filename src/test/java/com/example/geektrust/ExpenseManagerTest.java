package com.example.geektrust;

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
  public void addMember() {

    ExpenseManager manager = new ExpenseManager();

    manager.addMember(new Member("1", "ANDY"));

    Assertions.assertEquals(1, manager.getMemberMap().size());

  }

}