package org.acme;

public class CreatedEvent extends Event {


private User user;

public User getUser() {
  System.out.println("CreatedEvent getUser");
  return user;
}

public CreatedEvent(User user) {
  System.out.println("CreatedEvent CreatedEvent UserEvent");
  this.user = user;
}



}
