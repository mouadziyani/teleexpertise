package ma.youcode.clinic.model.entity;

import jakarta.persistence.*;
import ma.youcode.clinic.model.enums.UserRole;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole Role;

    public User() {};
}
