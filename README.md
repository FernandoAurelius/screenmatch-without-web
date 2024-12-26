# ScreenMatch - Seu gerenciador de séries e filmes!

**Descrição:**

O ScreenMatch é um back-end robusto em Java com Spring, construído para gerenciar e pesquisar títulos cinematográficos. Atualmente, focamos em séries e seus episódios, mas temos grandes planos para o futuro!

**Funcionalidades Atuais:**

- **Cadastro de Séries:** Cadastre suas séries favoritas com todos os detalhes: título, gênero, número de temporadas, atores e muito mais!
- **Busca de Séries:** Encontre rapidamente as séries que você procura, mesmo digitando apenas parte do título. A busca é case-insensitive (ignora maiúsculas e minúsculas).
- **Busca de Episódios:** Localize episódios específicos dentro de uma série.
- **Listagem de Séries:** Veja todas as séries cadastradas de forma organizada.

**Tecnologias:**

- Java
- Spring Data JPA
- PostgreSQL
- Java Stream API ️

# Modelo de domínio (Mermaid)
```mermaid
classDiagram
    class Category {
        <<enumeration>>
        +ACTION : String
        +ADVENTURE : String
        +DRAMA : String
        +COMEDY : String
        +CRIME : String
        +fromString(text: String) : Category
    }

    class Episode {
        +Long id
        +Integer season
        +String title
        +Integer episodeNumber
        +Double rating
        +LocalDate releaseDate
        +Series series
        +Episode()
        +Episode(Integer episodeNumber, EpisodeData episode)
        +String getTitle()
        +Double getRating()
        +LocalDate getReleaseDate()
        +Number getSeason()
        +Integer getEpisodeNumber()
        +void setSeries(Series series)
        +String toString()
    }

    class EpisodeData {
        +String title
        +String releaseDate
        +Integer number
        +String rating
        +String seasonNumber
        +String toString()
    }

    class SeasonData {
        +Integer number
        +List~EpisodeData~ episodes
        +String toString()
    }

    class Series {
        +Long id
        +String title
        +String yearsInActivity
        +String runtime
        +Category genre
        +String language
        +String plot
        +Double imdbRating
        +Integer totalSeasons
        +String actors
        +String posterAddress
        +List~Episode~ episodes
        +Series()
        +Series(SeriesData seriesData)
        +Series(Series series, String language)
        +String getTitle()
        +String getYearsInActivity()
        +String getRuntime()
        +Category getGenre()
        +String getLanguage()
        +String getPlot()
        +Double getImdbRating()
        +Integer getTotalSeasons()
        +String getActors()
        +String getPosterAddress()
        +void setEpisodes(List~Episode~ episodes)
        +String toString()
    }

    class SeriesData {
        +String title
        +String yearsInActivity
        +String runtime
        +String genre
        +String language
        +String plot
        +String rating
        +int seasons
        +String actors
        +String posterAddress
    }

    Category <|-- Series : genre
    Series "1" --> "0..*" Episode : episodes
    Episode "0..*" --> "1" Series : series
    SeasonData "1" --> "0..*" EpisodeData : episodes
    SeriesData "1" --> "0..*" SeasonData : seasons
```

**Funcionalidades Futuras:**

- **Avaliação de Séries e Episódios:** Avalie seus títulos favoritos e compartilhe sua opinião!
- **Interface Gráfica:** Uma interface amigável para facilitar a interação com o sistema.
- **Suporte a Filmes:** Expanda o ScreenMatch para incluir filmes em sua base de dados.
- **E muito mais... **

**Contribuições:**

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.