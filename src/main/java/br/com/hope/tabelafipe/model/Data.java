package br.com.hope.tabelafipe.model;

/*mapeando os nomes retornados pela API exatamente como está sendo retornado ;
de modo que não é necessário o uso do @JSONAlias*/

public record Data (String codigo, String nome) {
}
