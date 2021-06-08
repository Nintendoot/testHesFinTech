package by.nintendoot.testhesfintech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userAccount")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @Pattern(regexp = "[A-Za-z]{3,16}",
            message = "Username: должен быть больше 3 и меньше 16 и содержать только латинские символы.")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    @Pattern(regexp = "[A-Za-z]{1,16}", message = "FirstName: должен быть больше 1 и меньше 16 и содержать только латинские символы.")
    private String firstName;

    @Column(name = "lastName")
    @Pattern(regexp = "[A-Za-z]{1,16}", message = "LastName: должен быть больше 1 и меньше 16 и содержать только латинские символы.")
    private String lastName;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "createdAt")
    private String createdAt;
}
