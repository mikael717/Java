package br.com.hope.tabelafipe.principal;

import br.com.hope.tabelafipe.model.Data;
import br.com.hope.tabelafipe.model.Models;
import br.com.hope.tabelafipe.services.ConsumeApi;
import br.com.hope.tabelafipe.services.ConvertData;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumeApi consumeApi = new ConsumeApi();
    private ConvertData convertData = new ConvertData();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void displayMenu() {

        String menu = """
                 ************* OPÇÕES *************
                 1- CARRO
                 2- MOTO
                 3 - CAMINHÃO
                
                Digite uma das opções para consulta;
                """;

        System.out.println(menu);
        var optionSelect = sc.nextLine();
        String address;

        if (optionSelect.toLowerCase().contains("carr")) {
            address = URL_BASE + "carros/marcas";
        } else if (optionSelect.toLowerCase().contains("mot")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "caminhoes/marcas";
        }

        System.out.println(address);
        var json = consumeApi.obtainData(address);
        System.out.println(json);

        var brands = convertData.obtainList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta");
        var brandCode = sc.nextLine();

        address = address + "/" + brandCode + "/modelos";

        System.out.println(address);

        json = consumeApi.obtainData(address);
        var listModel = convertData.obtainData(json, Models.class);
        System.out.println("\nModelos dessa marca: "+listModel);

        listModel.models().stream()
                .sorted(Comparator.comparing(Data::codigo))
                .forEach(System.out::println);



    }

}
