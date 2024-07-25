package org.acme.graphql.repository;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.acme.graphql.model.SocialGroup;
import org.acme.graphql.model.User;

@ApplicationScoped
public class MockData {

  private SocialGroupRepository socialGroupRepository;
  private UserRepository userRepository;

  public MockData(SocialGroupRepository socialGroupRepository, UserRepository userRepository) {
    this.socialGroupRepository = socialGroupRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  void onStart(
      @Observes
      StartupEvent ev) {

    //유저 생성
    User user1 = new User(null, "user1", null);
    User user2 = new User(null, "user2", null);
    User user3 = new User(null, "user3", null);

    //생성 유저 레포지토리에 퍼시스트
    userRepository.persist(user1);
    userRepository.persist(user2);
    userRepository.persist(user3);

    SocialGroup socialGroup1 = new SocialGroup(null, "user2", user1);
    socialGroupRepository.persist(socialGroup1);

    SocialGroup socialGroup2 = new SocialGroup(null, "user1", user2);
    socialGroupRepository.persist(socialGroup2);

    SocialGroup socialGroup3 = new SocialGroup(null, "user2", user3);
    socialGroupRepository.persist(socialGroup3);





  }
}
