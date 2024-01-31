package org.quizapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateDto(@NotBlank Long id, @NotBlank int score ) {
}
