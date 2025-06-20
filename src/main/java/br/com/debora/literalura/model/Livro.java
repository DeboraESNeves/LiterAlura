package br.com.debora.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autorNome;
    private Integer autorAnoNascimento;
    private Integer autorAnoFalecimento;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Integer downloads;

    public Livro() {
    }

    public Livro(String titulo, String autorNome, Integer autorAnoNascimento, Integer autorAnoFalecimento, String idioma, Integer downloads){
        this.titulo = titulo;
        this.autorNome = autorNome;
        this.autorAnoNascimento = autorAnoNascimento;
        this.autorAnoFalecimento = autorAnoFalecimento;
        this.idioma = Idioma.fromString(idioma);
        this.downloads = downloads;
    }


    // Getters e setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public Integer getAutorAnoNascimento() {
        return autorAnoNascimento;
    }

    public void setAutorAnoNascimento(Integer autorAnoNascimento) {
        this.autorAnoNascimento = autorAnoNascimento;
    }

    public Integer getAutorAnoFalecimento() {
        return autorAnoFalecimento;
    }

    public void setAutorAnoFalecimento(Integer autorAnoFalecimento) {
        this.autorAnoFalecimento = autorAnoFalecimento;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "Livro: " +
                "título='" + titulo + '\'' +
                ", autor='" + autorNome + '\'' +
                ", nascimento=" + autorAnoNascimento +
                ", falecimento=" + autorAnoFalecimento +
                ", idioma='" + idioma + '\'' +
                ", downloads=" + downloads;
    }
}

