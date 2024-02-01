package org.quizapi.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteDto(@NotNull int id) {
}
