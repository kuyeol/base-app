package org.acme.base.repo;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import java.util.List;
import org.acme.base.Domain;

public interface DomainRepository extends PanacheRepositoryBase<Domain, Long> {

  Uni<Domain> findByName(String name);

  List<Domain> findByAll(String name);
}
