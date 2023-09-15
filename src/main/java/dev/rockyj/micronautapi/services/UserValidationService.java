package dev.rockyj.micronautapi.services;

import jakarta.inject.Singleton;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.regex.Pattern;

@Singleton
@Log
public class UserValidationService {

    private static final Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public Mono<UUID> isValidUserId(String userId) {
        if (UUID_REGEX.matcher(userId).matches()) {
            return Mono.just(UUID.fromString(userId));
        }
        throw new RuntimeException("Bad user id!");
    }
}
