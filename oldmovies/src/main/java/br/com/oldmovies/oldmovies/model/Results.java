package br.com.oldmovies.oldmovies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Results(Integer page, List<Data> results) {

    @Override
    public String toString() {
        return
                "page= " + page +
                "\nresults= " + results +"\n"
                ;
    }
}
