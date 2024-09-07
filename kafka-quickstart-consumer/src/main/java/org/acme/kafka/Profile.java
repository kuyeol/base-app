package org.acme.kafka;

public interface Profile {
  //
  //saveOrUpdate(balance: Balance): Balance
  //
  //findByAccountId(accountId: String): Balance?
  //
  //recalculate(transaction: Transaction): Balance
  UserProfile saveOrUpdate(UserProfile userProfile);
  UserProfile findByName(String name);


}
