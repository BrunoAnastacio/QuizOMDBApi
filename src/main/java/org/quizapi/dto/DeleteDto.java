package org.quizapi.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteDto(@NotBlank int id) {
}
