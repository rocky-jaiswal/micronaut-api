package dev.rockyj.micronautapi.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Serdeable
@Entity(name = "user_cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "user_id")
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    private UUID userId;

    @NotNull
    @Column(name = "city_name", columnDefinition = "TEXT")
    // @Lob
    private String cityName;
}
