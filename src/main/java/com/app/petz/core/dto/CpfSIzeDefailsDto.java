package com.app.petz.core.dto;

import lombok.Builder;

@Builder
public record CpfSIzeDefailsDto(
    String sizeDifference,
    String cpfSize,
    String defaultCpfSize
) {
}
