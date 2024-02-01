package org.quizapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDto(@NotNull Long id, @NotBlank int score ) {
}
