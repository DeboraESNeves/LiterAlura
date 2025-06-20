package br.com.debora.literalura.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class ConsultaApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Livro: " + response.body());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter dados da API: " + e.getMessage(), e);
        }
    }
}



