package ru.devolw.tacos.security;

import ru.devolw.tacos.model.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public Client toClient(PasswordEncoder passwordEncoder) {
        return new Client(
                username, passwordEncoder.encode(password),
                fullName, street, city, state, zip, phone);
    }
}