package by.nintendoot.testhesfintech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {

    private long id;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{3,16}",
            message = "Username: должен быть больше 3 и меньше 16 и содержать только латинские символы.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,16}$",
            message = "Password: должен быть больше 3 и меньше 16 и содержать только латинские символы и цифры.Минимум один символ и минимум одна цифра ")
    private String password;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{1,16}", message = "FirstName: должен быть больше 1 и меньше 16 и содержать только латинские символы.")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{1,16}", message = "LastName: должен быть больше 1 и меньше 16 и содержать только латинские символы.")
    private String lastName;

    private Role role;

    private Status status;
    private String createdAt;

}
