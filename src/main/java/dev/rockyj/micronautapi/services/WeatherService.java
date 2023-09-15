package dev.rockyj.micronautapi.services;

import dev.rockyj.micronautapi.domain.Weather;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.reactor.http.client.ReactorHttpClient;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import static io.micronaut.http.HttpHeaders.ACCEPT;

@Singleton
public class WeatherService {

    private final ReactorHttpClient httpClient;

    public WeatherService(@Client(id = "weatherService") ReactorHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Get("/v1/weatherByCity/${city}")
    @SingleResult
    public Mono<Weather> getWeather(String city) {
        var uri = UriBuilder.of("/v1/weatherByCity/")
                .path(city)
                .build();

        HttpRequest<?> req = HttpRequest.GET(uri)
                .header(ACCEPT, "application/json");

        return httpClient.retrieve(req, Weather.class);
    }
}
