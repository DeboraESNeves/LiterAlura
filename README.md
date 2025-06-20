
# 📚 Literalura

Aplicação Java que consome a API do [Gutendex](https://gutendex.com/) para buscar livros por título, armazená-los em um banco de dados e permitir a visualização e análise de estatísticas dos livros registrados.

---

## 🚀 Funcionalidades

- 🔍 **Busca de livros por título**  
  Faz uma consulta à API do Gutendex, retorna o primeiro livro encontrado e salva as informações em banco de dados.

- 📚 **Listagem de livros salvos**  
  Exibe todos os livros já registrados localmente, com detalhes como título, autor, idioma e número de downloads.

- 🖊️ **Listagem de autores registrados**  
  Lista todos os autores únicos dos livros salvos.

- ⏳ **Filtragem de autores vivos em um determinado ano**  
  Exibe autores que estavam vivos no ano informado pelo usuário.

- 🌍 **Estatísticas por idioma**  
  Exibe a quantidade de livros armazenados por idioma, com base nos dados cadastrados.

---

## 🛠️ Tecnologias e Dependências

- Java 17+
- Spring Boot (para persistência com Spring Data JPA)
- Maven
- PostgreSQL (ou outro banco relacional configurado)
- API pública [Gutendex](https://gutendex.com/)

---

## 💾 Estrutura da Aplicação

- `Principal.java`: Classe principal com o menu de interação via terminal.
- `Livro`: Entidade JPA representando um livro salvo no banco.
- `LivroRepository`: Interface para acesso ao banco usando Spring Data.
- `ConsultaApi`: Realiza chamadas HTTP para a API do Gutendex.
- `ConverteDados`: Converte o JSON da API em objetos Java.
- `Categorias` e `RespostaApi`: Classes para mapear os dados da API.

---

## 📦 Como executar

1. Clone o repositório:

```bash
git https://github.com/DeboraESNeves/LiterAlura.git
cd literalura
```

2. Configure o banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Compile e execute o projeto:

```bash
./mvnw spring-boot:run
```

4. O menu da aplicação será exibido no terminal.

---

## 📸 Exemplo de Uso

```
-----------------------
Escolha o número da sua opção:
1- Buscar livro pelo Título
2- Listar livros registrados
3- Listar autores registrados
4- Listar autores vivos em um determinado ano
5- Exibir estatísticas por idioma
0- Sair
```

---

## 🧠 Ideias para melhorias futuras

- Permitir busca por autor
- Suporte a múltiplos idiomas por livro
- Interface gráfica (GUI ou Web)
- Filtro por número mínimo de downloads
- Paginação na exibição dos livros

---

## 📄 Licença

Este projeto está licenciado sob a licença MIT. Sinta-se à vontade para usar, modificar e compartilhar.