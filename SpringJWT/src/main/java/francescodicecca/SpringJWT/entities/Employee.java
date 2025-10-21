package francescodicecca.SpringJWT.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(unique = true)
    private String username;

    private String name;

    @Column(unique = true)
    private String surname;
    private String email;
    private String avatarUrl;
}
