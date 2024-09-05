package org.acme.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class MsgProducer {

   // @Inject
        // @Channel("msg-out")
    //Emitter<String> emitter;

    public void mdmd(String m) {
      //  emitter.send(m);

    }

}
