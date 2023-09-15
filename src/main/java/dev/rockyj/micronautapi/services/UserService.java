package dev.rockyj.micronautapi.services;

import dev.rockyj.micronautapi.entities.User;
import dev.rockyj.micronautapi.entities.UserCity;
import dev.rockyj.micronautapi.entities.UserPreference;
import dev.rockyj.micronautapi.repositories.UserCitiesRepository;
import dev.rockyj.micronautapi.repositories.UserPreferencesRepository;
import dev.rockyj.micronautapi.repositories.UsersRepository;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
@Log
public class UserService {
    private final UsersRepository usersRepository;
    private final UserPreferencesRepository userPreferencesRepository;
    private final UserCitiesRepository userCitiesRepository;

    public Flux<User> listAll() {
        return usersRepository.findAll();
    }

    public Mono<User> findByUserId(UUID userId) {
        log.info("->1");
        return usersRepository.findById(userId);
    }

    public Mono<UserPreference> findPreferencesByUserId(UUID userId) {
        log.info("->2");
        return Mono.delay(Duration.ofSeconds(3)).then(userPreferencesRepository.findByUserId(userId));
    }

    public Flux<UserCity> findCitiesByUserId(UUID userId) {
        log.info("->3");
        return Mono.delay(Duration.ofSeconds(3)).thenMany(userCitiesRepository.findByUserId(userId));
    }
}