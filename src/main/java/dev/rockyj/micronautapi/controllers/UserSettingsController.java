package dev.rockyj.micronautapi.controllers;

import dev.rockyj.micronautapi.domain.UserSettings;
import dev.rockyj.micronautapi.domain.Weather;
import dev.rockyj.micronautapi.entities.User;
import dev.rockyj.micronautapi.entities.UserCity;
import dev.rockyj.micronautapi.entities.UserPreference;
import dev.rockyj.micronautapi.services.UserService;
import dev.rockyj.micronautapi.services.UserValidationService;
import dev.rockyj.micronautapi.services.WeatherService;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;

@Controller("/v1/user-settings")
@AllArgsConstructor
public class UserSettingsController {

    protected final UserService userService;
    protected final UserValidationService userValidationService;
    protected final WeatherService weatherService;

    @Get("/{userId}")
    public Mono<UserSettings> getUserPreferences(String userId) {
        return userValidationService
                .isValidUserId(userId)
                .flatMap((var userIdUUID) -> {
                    var monoOfUser = userService.findByUserId(userIdUUID);
                    var monoOfUserPreferences = userService.findPreferencesByUserId(userIdUUID);
                    var fluxOfUserCities = userService.findCitiesByUserId(userIdUUID);

                    return Mono.zip(monoOfUser, monoOfUserPreferences, fluxOfUserCities.collectList());
                })
                .flatMap((Tuple3<User, UserPreference, List<UserCity>> tuple) -> {
                    var cities = tuple.getT3();
                    var fluxOfWeather = Flux.fromIterable(cities).flatMap((var city) -> weatherService.getWeather(city.getCityName()));

                    return Mono.zip(Mono.just(tuple.getT1()), Mono.just(tuple.getT2()), fluxOfWeather.collectList());
                })
                .flatMap((Tuple3<User, UserPreference, List<Weather>> tuple) -> {
                    var userSettings = new UserSettings(
                            tuple.getT1().getEmail(),
                            tuple.getT2().isMember(),
                            tuple.getT3());

                    return Mono.just(userSettings);
                });
    }
}
