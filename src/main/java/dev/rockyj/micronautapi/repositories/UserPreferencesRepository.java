package dev.rockyj.micronautapi.repositories;

import dev.rockyj.micronautapi.entities.UserPreference;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserPreferencesRepository extends ReactorPageableRepository<UserPreference, UUID> {

    @Query("from user_preferences where userId = :userId")
    Mono<UserPreference> findByUserId(UUID userId);
}
