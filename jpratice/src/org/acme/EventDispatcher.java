package org.acme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventDispatcher {
  private Map<Class<? extends Event>, List<Consumer<Event>>> handlers = new HashMap<>();



  public void dispatch(Event event) {
    System.out.println("EventDispatcher dispatch");
    List<Consumer<Event>> eventHandlers = handlers.get(event.getClass());
    if (eventHandlers != null) {
      eventHandlers.forEach(handler -> handler.accept(event));
    }
  }

public <E extends Event> void registerHandler(Class<E> eventClass, Consumer<E> handler) {
  System.out.println("EventDispatcher registerHandler");
    List<Consumer<Event>> eventHandlers = handlers.get(eventClass);
}


}
