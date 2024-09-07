package org.acme.kafka.event;

import lombok.Builder;
import lombok.Data;
import org.acme.kafka.model.User;


@Data
@Builder
public class UserCreateEvent implements UserEvent
{

  private EventStatus status;

  private User userDetails;





}
