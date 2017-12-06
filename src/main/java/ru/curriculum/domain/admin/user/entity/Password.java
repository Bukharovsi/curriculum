package ru.curriculum.domain.admin.user.entity;

import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;

public class Password {
    private PasswordEncoder encoder;
    @Column(name = "password")
    private String hashedPassword;

    public Password(@NonNull String password) {
        this.encoder = new BCryptPasswordEncoder(11);
        this.hashedPassword = encoder.encode(password);
    }

    public String hash() {
        return hashedPassword;
    }
}
