package br.com.debora.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaApi {
    private List<Categorias> results;

    public List<Categorias> getResults() {
        return results;
    }

    public void setResults(List<Categorias> results) {
        this.results = results;
    }
}

