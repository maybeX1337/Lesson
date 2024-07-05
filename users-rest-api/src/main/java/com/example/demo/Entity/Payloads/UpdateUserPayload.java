package com.example.demo.Entity.Payloads;

import jakarta.validation.constraints.*;

public record UpdateUserPayload(
        @NotEmpty(message = "Имя не должно быть пустым")
        @Size(min = 2, max = 20, message = "{errors.create.name_size}")
        String name,
        @NotEmpty(message = "Email is null")
        @Email(message = "Неверный емаил")
        @Size(min = 5, max = 100, message = "{errors.create.email_null}")
        String email,
        @NotNull(message = "Age is null")
        @Min(value = 18, message = "Age must be greater than 18")
        Integer age) {
}
