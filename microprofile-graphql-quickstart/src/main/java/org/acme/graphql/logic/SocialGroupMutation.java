package org.acme.graphql.logic;

import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;
import org.acme.graphql.model.SocialGroup;
import org.acme.graphql.model.SocialGroupInput;
import org.acme.graphql.model.User;
import org.acme.graphql.repository.SocialGroupRepository;
import org.acme.graphql.repository.UserRepository;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;

public class SocialGroupMutation {

  public static void driverConnect() {

    try {
      DriverManager.registerDriver(new OracleDriver());
    } catch (SQLException e) {
      e.printStackTrace();
      Runtime.getRuntime().exit(1);
    }
  }

  SocialGroupRepository socialGroupRepository;
  UserRepository userRepository;

  SocialGroupMutation(SocialGroupRepository socialGroupRepository, UserRepository userRepository) {
    this.socialGroupRepository = socialGroupRepository;
    this.userRepository = userRepository;
  }

  @Mutation("newSocialGroup")
  public SocialGroup newSocialGroup(
      @Name("input")
      SocialGroupInput socialGroupInput) {
    User user = (User) userRepository.findById(socialGroupInput.getUserId());
    SocialGroup socialGroup = new SocialGroup(null, socialGroupInput.getName(), user);
    socialGroupRepository.persist(socialGroup);
    return socialGroup;
  }
}
