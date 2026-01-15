package com.zevra.zevra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @Size(min = 1, message = "Le prénom ne peut pas être vide")
    private String firstName;

    @Size(min = 1, message = "Le nom ne peut pas être vide")
    private String lastName;

    @Size(min = 4, max = 20, message = "Le nom d'utilisateur doit être entre 4 et 20 caractères")
    private String username;

    @Email(message = "L'email doit être valide")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
