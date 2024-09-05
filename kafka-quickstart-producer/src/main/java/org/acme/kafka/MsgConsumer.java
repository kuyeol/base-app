package org.acme.kafka;


import java.util.Locale;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MsgConsumer {

    @Incoming("msg-input")
    public String recive(String msg) {

     String m=msg.toLowerCase(Locale.ROOT);
     return m;
    }

}
