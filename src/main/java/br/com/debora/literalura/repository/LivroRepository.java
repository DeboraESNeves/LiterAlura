package br.com.debora.literalura.repository;

import br.com.debora.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
