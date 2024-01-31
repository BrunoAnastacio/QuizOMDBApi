package org.quizapi.dto;

import jakarta.validation.constraints.NotBlank;

public record InsertDto(@NotBlank String name, @NotBlank int score) {
}
