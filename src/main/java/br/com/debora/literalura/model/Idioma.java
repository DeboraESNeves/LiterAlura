package br.com.debora.literalura.model;

public enum Idioma {
    PORTUGUES("pt"),
    INGLES("en"),
    ESPANHOL("es"),
    FRANCES("fr"),
    ALEMAO("de"),
    ITALIANO("it");

    private final String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Idioma fromString(String idioma) {
        if (idioma == null || idioma.trim().isEmpty()) {
            return null;
        }

        String idiomaLower = idioma.toLowerCase().trim();

        for (Idioma i : Idioma.values()) {
            if (i.codigo.equalsIgnoreCase(idiomaLower)) {
                return i;
            }
        }

        switch (idiomaLower) {
            case "portuguese":
            case "português":
            case "portugues":
                return PORTUGUES;
            case "english":
            case "inglês":
            case "ingles":
                return INGLES;
            case "spanish":
            case "español":
            case "espanhol":
                return ESPANHOL;
            case "french":
            case "français":
            case "francês":
            case "frances":
                return FRANCES;
            case "german":
            case "deutsch":
            case "alemão":
            case "alemao":
                return ALEMAO;
            case "italian":
            case "italiano":
                return ITALIANO;
            default:
                throw new IllegalArgumentException("Idioma não reconhecido: " + idioma);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PORTUGUES: return "Português";
            case INGLES: return "Inglês";
            case ESPANHOL: return "Espanhol";
            case FRANCES: return "Francês";
            case ALEMAO: return "Alemão";
            case ITALIANO: return "Italiano";
            default: return name();
        }
    }
}

