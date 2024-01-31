
package org.quizapi.dto;

import jakarta.validation.constraints.NotBlank;

public record OmdbGetTitleDto(
        @NotBlank
        String Title,
        @NotBlank
        String Plot,
        @NotBlank
        String imdbID,
        @NotBlank
        String Error) {

}