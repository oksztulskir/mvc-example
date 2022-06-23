package pl.sdacademy.springdatauserdemo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        MVC_ROLE, REST_ROLE, ADMIN
    }
}
