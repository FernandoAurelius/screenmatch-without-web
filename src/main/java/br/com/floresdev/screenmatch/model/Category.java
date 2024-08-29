package br.com.floresdev.screenmatch.model;

public enum Category {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    DRAMA("Drama"),
    COMEDY("Comedy"),
    CRIME("Crime");

    private final String omdbCategory;

    Category(String omdbCategory) {
        this.omdbCategory = omdbCategory;
    }

    public static Category fromString(String text) {
        for (Category categoria : Category.values()) {
            if (categoria.omdbCategory.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
