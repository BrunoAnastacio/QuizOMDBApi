package org.quizapi.dto;

import jakarta.validation.constraints.NotBlank;

public record GetByIdDto(@NotBlank int id) {
}
