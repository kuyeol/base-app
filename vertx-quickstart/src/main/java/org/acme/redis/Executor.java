package org.acme.redis;

import com.redis.smartcache.core.config.Config;
import io.vertx.redis.client.impl.RedisURI;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.Closeable;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import com.redis.smartcache.core.Mappers;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class Executor implements AutoCloseable {

  private static final Logger log = Logger.getLogger(Executor.class.getName());

  private Config smartcache = new Config();

  private final RedisURI redisURI;
  private final DataSourceProperties dataSourceProperties;
  private final RedisConfig config;
  private final List<QueryTask> tasks = new ArrayList<>();

  public Executor(RedisURI redisURI, DataSourceProperties dataSourceProperties, RedisConfig config) {
    this.redisURI = redisURI;
    this.dataSourceProperties = dataSourceProperties;
    this.config = config;
  }



  //
  //private static final String QUERY="SELECT orders.orderNumber, orders.orderDate, orders.requiredDate, orders.shippedDate, orders.status, orders.customerNumber, customers.customerName, orderdetails.productCode, products.productName, orderdetails.quantityOrdered FROM orders JOIN customers ON orders.customerNumber = customers.customerNumber JOIN orderdetails ON orders.orderNumber = orderdetails.orderNumber JOIN products ON orderdetails.productCode = products.productCode, (SELECT SLEEP(?)) as sleep WHERE orders.orderNumber = ?";

 // private final RedisURI redisURI;

 // private final DataSourceProperties dataSourceProperties;
 // private final DemoConfig config;
 // private final List<QueryTask> tasks = new ArrayList<>();

  private static class QueryTask implements Callable<Integer> {

    private final Random random = new Random();
    private final DataSource dataSource;

    private final int totalRows;
    private boolean stopped;

    public QueryTask(DataSource dataSource, int totalRows) {
      this.dataSource = dataSource;
      this.totalRows = totalRows;

    }

    //public void execute() throws InterruptedException, ExecutionException,
    //    IOException {
    //  HikariConfig hikariConfig = new HikariConfig();
    //  hikariConfig.setJdbcUrl("jdbc:" + redisURI.toString());
    //  hikariConfig.setDriverClassName(Driver.class.getName());
    //  config.getSmartcache().getDriver().setUrl(dataSourceProperties.determineUrl());
    //  config.getSmartcache().getDriver().setClassName(dataSourceProperties.determineDriverClassName());
    //  Properties props = Mappers.properties(config.getSmartcache());
    //  for (String propertyName : props.stringPropertyNames()) {
    //    hikariConfig.addDataSourceProperty(propertyName, props.getProperty(propertyName));
    //  }
    //  hikariConfig.setUsername(dataSourceProperties.determineUsername());
    //  hikariConfig.setPassword(dataSourceProperties.determinePassword());
    //  try (HikariDataSource dataSource = new HikariDataSource(hikariConfig)) {
    //    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //    executor.setCorePoolSize(config.getDemo().getThreads());
    //    executor.setMaxPoolSize(config.getDemo().getThreads());
    //    executor.setThreadNamePrefix("query-task");
    //    executor.initialize();
    //    ProgressBarBuilder progressBarBuilder = new ProgressBarBuilder();
    //    progressBarBuilder.setTaskName("Querying");
    //    progressBarBuilder.showSpeed();
    //    progressBar = progressBarBuilder.build();
    //    List<Future<Integer>> futures = new ArrayList<>();
    //    for (int index = 0; index < config.getDemo().getThreads(); index++) {
    //      QueryTask task = new QueryTask(dataSource, config.getDemo().getOrders(), progressBar);
    //      tasks.add(task);
    //      futures.add(executor.submit(task));
    //    }
    //    for (Future<?> future : futures) {
    //      future.get();
    //    }
    //    executor.shutdown();
    //  }
    //}


    @Override
    public Integer call() throws Exception {
      int count = 0;
      while (!stopped) {
        try (Connection connection = dataSource.getConnection();

             // TODO: SQL SCRIPT INSERT
             PreparedStatement statement = connection.prepareStatement("")) {
          int orderNumber = random.nextInt(totalRows) + 1;
          statement.setInt(1, random.nextInt(5));
          statement.setInt(2, orderNumber);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              for (int index = 0; index < resultSet.getMetaData().getColumnCount(); index++) {
                resultSet.getObject(index + 1);
              }
            }
          }

          count++;
        } catch (SQLException e) {
          log.log(Level.SEVERE, "Could not run query", e);
        }
      }
      return count;
    }

    public void stop() {
      this.stopped = true;
    }

  }


  @Override
  public void close() throws
      Exception {

  }
}
