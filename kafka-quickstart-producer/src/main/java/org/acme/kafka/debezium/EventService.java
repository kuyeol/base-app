package org.acme.kafka.debezium;

import io.debezium.outbox.quarkus.ExportedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.kafka.User;


//@ApplicationScoped
public class EventService
{

  //
  //@PersistenceContext
  //EntityManager entityManager;
  //
  //@Inject
  //Event< ExportedEvent<?, ?> > event;
  //
  //


//  //@Transactional
//  public UserRegisterForm registerUser(UserRegisterForm register) {
//    register = entityManager.merge(register);
//
//    // Fire events for newly created PurchaseOrder
//    event.fire(UserRegisterEvent.of(register));
////    event.fire(InvoiceCreatedEvent.of(order));
//
//    return register;
//  }
//
//



}
