package dev.rockyj.micronautapi.repositories;

import dev.rockyj.micronautapi.entities.UserCity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface UserCitiesRepository extends ReactorPageableRepository<UserCity, UUID> {

    @Query("from user_cities where userId = :userId")
    Flux<UserCity> findByUserId(UUID userId);
}