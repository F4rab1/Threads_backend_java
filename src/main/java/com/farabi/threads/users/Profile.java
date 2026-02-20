package com.farabi.threads.users;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "bio")
    private String bio;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;
}
