package com.example.geektrust.bo;


public class DuesSheet implements Comparable {

  Integer amount;
  String name;

  public DuesSheet(Integer amount, String name) {
    this.amount = amount;
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public int compareTo(Object o) {
    return this.amount - ((DuesSheet) o).getAmount();
  }
}
