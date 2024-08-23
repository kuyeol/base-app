package me.escoffier.quarkus.supes.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepo implements PanacheRepositoryBase<Role,String> {



}
