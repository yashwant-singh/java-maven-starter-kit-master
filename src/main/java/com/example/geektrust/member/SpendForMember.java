package com.example.geektrust.member;

import com.example.geektrust.bo.Member;
import com.example.geektrust.bo.SpendFor;

/**
 * The type Spend for member.
 *
 * @author Yashwant
 */
public class SpendForMember extends SpendFor {

  /**
   * Instantiates a new Spend for member.
   *
   * @param user the user
   */
  public SpendForMember(Member user) {
    super(user);
  }
}