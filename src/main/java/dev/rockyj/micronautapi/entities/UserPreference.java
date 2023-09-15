package dev.rockyj.micronautapi.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Serdeable
@Entity(name = "user_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @NotNull
//    @PrimaryKeyJoinColumn(name = "user_id")
//    @OneToOne
//    private User user;

    @NotNull
    @Column(name = "is_member")
    private boolean isMember;

    //@OneToOne
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private UUID userId;
}
