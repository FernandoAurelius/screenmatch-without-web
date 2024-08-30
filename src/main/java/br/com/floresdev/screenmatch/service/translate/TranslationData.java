package br.com.floresdev.screenmatch.service.translate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TranslationData(TranslationResponseData responseData) {
}
