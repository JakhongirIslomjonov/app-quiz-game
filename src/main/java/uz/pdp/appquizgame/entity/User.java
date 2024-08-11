package uz.pdp.appquizgame.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String email;

    private String fullName;


    private String phoneNumber;


    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @CreationTimestamp
    private LocalDateTime signedAt;

    @CreationTimestamp
    private LocalDateTime lastUpdateAt;

}