package dev.rockyj.micronautapi.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record UserSettings(String email, Boolean isMember, List<Weather> citiesWeather) {
}
