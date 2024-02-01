package org.quizapi.dto;

import jakarta.validation.constraints.NotNull;

public record GetByIdDto(@NotNull int id) {
}
