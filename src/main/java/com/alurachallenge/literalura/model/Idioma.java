package com.alurachallenge.literalura.model;

public enum Idioma {
    INGLES("en", "Inglés"),
    ESPANOL("es", "Español"),
    FRANCES("fr", "Francés"),
    PORTUGUES("pt", "Portugués"), //de
    OTRO("OTRO", "Otro"); // Valor por defecto cuando no se encuentra un idioma válido

    private String idiomaOMDB;
    private String idiomaEspanol;

    Idioma(String idiomaOMDB, String idiomaEspanol) {
        this.idiomaOMDB = idiomaOMDB;
        this.idiomaEspanol = idiomaEspanol;
    }

    public static Idioma fromString(String text) {
        for(Idioma idioma : Idioma.values()) {
            if(idioma.idiomaOMDB.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        return OTRO; // Devuelve OTRO si no se encuentra el idioma
    }

    public static Idioma fromEspanol(String text) {
        for(Idioma idioma : Idioma.values()) {
            if(idioma.idiomaEspanol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        return OTRO; // Devuelve OTRO si no se encuentra el idioma
    }

}
