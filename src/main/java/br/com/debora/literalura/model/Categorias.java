package br.com.debora.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") String autor,
                         @JsonAlias("languages") String idioma,
                         @JsonAlias("download_count") Integer downloads) {
}
