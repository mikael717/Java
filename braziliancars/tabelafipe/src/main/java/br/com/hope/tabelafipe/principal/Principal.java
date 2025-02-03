package br.com.hope.tabelafipe.principal;

import br.com.hope.tabelafipe.model.Data;
import br.com.hope.tabelafipe.model.Models;
import br.com.hope.tabelafipe.model.Vehicle;
import br.com.hope.tabelafipe.service.ConsumeApi;
import br.com.hope.tabelafipe.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        String optionSelect = this.sc.nextLine();
        String address;

        if (optionSelect.toLowerCase().contains("carr")) {
            address = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        } else if (optionSelect.toLowerCase().contains("mot")) {
            address = "https://parallelum.com.br/fipe/api/v1/motos/marcas";
        } else {
            address = "https://parallelum.com.br/fipe/api/v1/caminhoes/marcas";
        }

        System.out.println(address);

        String json = this.consumeApi.obtainData(address);
        System.out.println(json);
        List<Data> brands = this.convertData.obtainList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::codigo))
                .forEach(System.out::println);

        System.out.println("\nInforme o código da marca para consulta: ");
        var brandCode = sc.nextLine();

        address = address + "/" + brandCode + "/modelos";
        json = consumeApi.obtainData(address);
        var listModel = convertData.obtainData(json, Models.class);

        System.out.println("\nModelos dessa marca: ");
        listModel.models().stream()
                .sorted(Comparator.comparing(Data::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado: ");
        var vehicleName = sc.nextLine();

        List<Data> filteredModels = listModel.models().stream()
                .filter(m -> m.nome().toLowerCase().contains(vehicleName.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        filteredModels.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var modelCode = sc.nextLine();

        address = address + "/" + modelCode + "/anos";
        json = consumeApi.obtainData(address);
        List<Data> years = convertData.obtainList(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var yearsAddress = address + "/" + years.get(i).codigo();
            json = consumeApi.obtainData(yearsAddress);
            Vehicle vehicle = convertData.obtainData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        vehicles.forEach(System.out::println);

    }
}