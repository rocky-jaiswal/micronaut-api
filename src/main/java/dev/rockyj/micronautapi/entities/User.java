package dev.rockyj.micronautapi.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Serdeable
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String email;

//    @OneToMany
//    // @JoinTable(name = "user_cities")
//    @JoinColumn(name = "user_id")
//    private List<UserCity> cities;

//    @OneToOne(mappedBy = "UserPreference")
//    @JoinColumn(name = "user_id")
//    private UserPreference userPreference;
}
