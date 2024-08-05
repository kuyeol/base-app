package org.acme.redis;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisInit {



  private RedisInit redisInit;

  public RedisInit() {}

  public RedisInit(RedisInit redisInit) {
    this.redisInit = redisInit;
  }


  @PostConstruct
  public void init() {
    redisInit.init();
  }




}
