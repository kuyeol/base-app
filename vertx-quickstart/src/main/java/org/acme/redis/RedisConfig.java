package org.acme.redis;

import com.redis.smartcache.core.config.Config;
import jakarta.enterprise.context.Dependent;
import org.eclipse.microprofile.config.inject.ConfigProperties;

@Dependent
@ConfigProperties(prefix = "")
public class RedisConfig {

  private Config smartcache = new Config();
  private DataConfig redisData = new DataConfig();

  /*

 smartcahe : 레디스 패키지 포함 클래스
              - 클래스에서 인스턴스 생성
              - 겟,셋 정의
              -
 redisData : 클래스 내부 신규 클래스
              - 정적 클래스 생성 -> 초기화 -> 인스턴스 생성
              -겟 셋 정의


   */

  public Config getSmartcache() {
    return smartcache;
  }

  public void setSmartcache(Config config) {
    this.smartcache = config;
  }

  // <------[CACHE]-----[CONFIG]---------->  //





  public DataConfig getRedisData() {
    return redisData;
  }

  public void setRedisData(DataConfig dataConfig) {
    this.redisData = dataConfig;
  }

  public static class DataConfig {

    private boolean flush;
    private int threads;
    private int batch;

    public boolean isFlush() {
      return flush;
    }

    public void setFlush(boolean flush) {
      this.flush = flush;
    }

    public int getThreads() {
      return threads;
    }

    public void setThreads(int threads) {
      this.threads = threads;
    }

    public int getBatch() {
      return batch;
    }

    public void setBatch(int size) {
      this.batch = size;
    }
    //private int customers;
    //private int products;
    //private int orders;
    //private int orderdetails;
  }
}
