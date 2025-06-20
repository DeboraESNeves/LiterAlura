package br.com.debora.literalura.principal;

import br.com.debora.literalura.model.Categorias;
import br.com.debora.literalura.model.Livro;
import br.com.debora.literalura.model.RespostaApi;
import br.com.debora.literalura.repository.LivroRepository;
import br.com.debora.literalura.service.ConsultaApi;
import br.com.debora.literalura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsultaApi consulta = new ConsultaApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO1 = "https://gutendex.com/books?search=";

    private List<Categorias> dados = new ArrayList<>();

    private LivroRepository repositorio;

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    -----------------------
                    Escolha o número da sua opção:
                    1- Buscar livro pelo Título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Exibir estatísticas por idioma
                    0- Sair
                    """;
            System.out.println(menu);
            if (leitura.hasNextInt()) {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1 -> buscaTitulo();
                    case 2 -> listaLivros();
                    case 3 -> listaAutores();
                    case 4 -> listaAutoresVivosEmAno();
                    case 5 -> exibirEstatisticasPorIdioma();
                    case 0 -> System.out.println("Encerrando aplicação...");
                    default -> System.out.println("Opção inválida!");
                }
            } else {
                System.out.println("Por favor, digite uma opção válida!");
                leitura.nextLine();
            }
        }
    }

    private void buscaTitulo() {
        try {
            Categorias categorias = getCategorias();

            if (categorias == null) {
                System.out.println("Nenhum livro encontrado!");
                return;
            }

            String titulo = categorias.titulo();

            String autorNome = "Autor desconhecido";
            Integer autorAnoNascimento = null;
            Integer autorAnoFalecimento = null;

            if (categorias.autores() != null && !categorias.autores().isEmpty()) {
                var primeiroAutor = categorias.autores().get(0);
                autorNome = primeiroAutor.nome();
                autorAnoNascimento = primeiroAutor.anoNascimento();
                autorAnoFalecimento = primeiroAutor.anoMorte();
            }

            String idioma = categorias.idiomas() != null && !categorias.idiomas().isEmpty()
                    ? categorias.idiomas().get(0) : "Idioma desconhecido";

            Integer downloads = categorias.downloads();

            Livro livro = new Livro(titulo, autorNome, autorAnoNascimento, autorAnoFalecimento, idioma, downloads);

            repositorio.save(livro);

            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    private void listaLivros() {
        List<Livro> livros = repositorio.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutorNome());

            if (livro.getAutorAnoNascimento() != null) {
                System.out.println("Ano de nascimento do autor: " + livro.getAutorAnoNascimento());
            } else {
                System.out.println("Ano de nascimento do autor: Desconhecido");
            }

            if (livro.getAutorAnoFalecimento() != null) {
                System.out.println("Ano de falecimento do autor: " + livro.getAutorAnoFalecimento());
            } else {
                System.out.println("Ano de falecimento do autor: Desconhecido");
            }

            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Downloads: " + livro.getDownloads());
            System.out.println("-----------------------------");
        }
    }

    private void listaAutores() {
        List<Livro> livros = repositorio.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
            return;
        }

        System.out.println("Autores registrados:");
        livros.stream()
                .map(Livro::getAutorNome)
                .distinct()
                .forEach(System.out::println);
    }

    private void listaAutoresVivosEmAno() {
        System.out.print("Digite o ano: ");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Livro> livros = repositorio.findAll();
        boolean encontrou = false;

        for (Livro livro : livros) {
            Integer nascimento = livro.getAutorAnoNascimento();
            Integer falecimento = livro.getAutorAnoFalecimento();

            if (nascimento != null && nascimento <= ano && (falecimento == null || falecimento >= ano)) {
                System.out.println("Autor: " + livro.getAutorNome() +
                        " (Nascimento: " + nascimento +
                        ", Falecimento: " + (falecimento != null ? falecimento : "Vivo") + ")");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum autor encontrado vivo no ano informado.");
        }
    }

    private void exibirEstatisticasPorIdioma() {
        List<Livro> livros = repositorio.findAll();

        Map<String, Long> contagemPorIdioma = livros.stream()
                .collect(Collectors.groupingBy(l -> l.getIdioma().toString(), Collectors.counting()));

        System.out.println("\nEstatísticas de livros por idioma:");
        contagemPorIdioma.forEach((idioma, quantidade) ->
                System.out.println("Idioma " + idioma + ": " + quantidade + " livro(s)"));
    }

    private Categorias getCategorias() {
        System.out.println("Digite o título para busca: ");
        String nomeLivro = leitura.nextLine().toLowerCase().replace(" ", "%20");
        String url = ENDERECO1 + nomeLivro;

        String json = consulta.obterDados(url);
        RespostaApi resposta = conversor.obterDados(json, RespostaApi.class);

        if (resposta == null || resposta.getResults() == null || resposta.getResults().isEmpty()) {
            return null;
        }

        return resposta.getResults().get(0);
    }
}
