package dev.rockyj.micronautapi.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Weather(String cityName, Integer maxTemp, Integer minTemp) {}
