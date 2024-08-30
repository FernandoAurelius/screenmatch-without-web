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
        for (Category category : Category.values()) {
            if (category.omdbCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category found for received string: " + text);
    }
}
