package dev.rockyj.micronautapi.repositories;

import dev.rockyj.micronautapi.entities.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;

import java.util.UUID;

@Repository
public interface UsersRepository extends ReactorPageableRepository<User, UUID> {
//    @Override
//    @Query("select id, name, email, pg_sleep(2) from users")
//    Flux<User> findAll();
}
