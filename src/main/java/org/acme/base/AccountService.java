package org.acme.base;

import io.smallrye.reactive.messaging.annotations.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.stream.Collectors;
import org.acme.base.repo.DomainRepository;

@ApplicationScoped
public class AccountService implements Service {

  //private final OrderRepository orderRepository;

  // private final UserHolder userHolder;

  private final DomainRepository domainRepository;
  private final DomainMapper domainMapper;

  @Inject
  public AccountService(DomainRepository repository, DomainMapper domainMapper) {
    this.domainRepository = repository;
    this.domainMapper = domainMapper;

  }

  @Override
  public DomainDTO persist(DomainDTO domainDTO) {
    return null;
  }

  //@Transactional
  //@POST
  //public Uni<Response> create(Domain u) {
  //  Domain user =new Domain();
  //  user.setName(u.getName());
  //  user.setRole(u.getRole());
  //  user.setTimeStamp();
  //  return u.per
  //}
  //
  //@Override
  //public DomainDTO persist(DomainDTO domainDTO) {
  //
  //  Domain domain = (Domain) domainRepository.persist(domainMapper.toEntity(domainDTO));
  //  if (domainDTO.getName() != null && !domainDTO.getName().isEmpty()) {
  //    domainEmitter.send(new DomainAvro(domain.getDomains()
  //                                            .stream()
  //                                            .map(newdomain -> new Domain(newdomain.getName().toString()))
  //                                            .collect(Collectors.toList())));
  //  }
  //
  //  return domainMapper.toDto(domain);
  //}
  //



}
