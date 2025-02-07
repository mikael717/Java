package br.com.oldmovies.oldmovies.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data (
        Boolean adult,
        @JsonAlias("first_air_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate release,
        String name,
        @JsonAlias("vote_average") Double rating,
        @JsonAlias("original_language") String language
        ) {

    @Override
    public String toString() {
        return String.format("Adulto? %s;\n" +
                "Nome: %s\n" +
                "Linguagem original: %s\n" +
                "Data de lançamento: %s\n" +
                "Rating: %f\n", adult, name, language ,release, rating );
    }


}
