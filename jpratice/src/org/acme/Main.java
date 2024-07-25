package org.acme;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EventDispatcher eventDispatcher = new EventDispatcher();


        User user= new User();

        eventDispatcher.registerHandler(CreatedEvent.class , new EventHandler()::onUserCreated);




        //user.setName("John");
       // user.setAge(11);

       eventDispatcher.dispatch(new CreatedEvent(user));





    }
}

