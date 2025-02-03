package br.com.hope.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle (
        @JsonAlias("Valor") String value,
        @JsonAlias("Marca") String brand,
        @JsonAlias("Modelo") String model,
        @JsonAlias("AnoModelo") Integer year,
        @JsonAlias("Combustivel") String fuel
){
    @Override
    public String toString() {
        return String.format("%s %s  ano: %s valor: %s combustível: %s",
                brand, model, year, value,fuel);
    }
}
