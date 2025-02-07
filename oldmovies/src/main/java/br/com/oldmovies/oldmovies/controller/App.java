package br.com.oldmovies.oldmovies.controller;

import br.com.oldmovies.oldmovies.model.Data;
import br.com.oldmovies.oldmovies.model.Results;
import br.com.oldmovies.oldmovies.service.ConsumeApi;
import br.com.oldmovies.oldmovies.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    private ConsumeApi consumeApi = new ConsumeApi();
    private ConvertData convertData = new ConvertData();
    private Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "https://api.themoviedb.org/3/tv/top_rated";

    public void displayResults(){
        String json = this.consumeApi.obtainData(URL_BASE);
        System.out.println("\n"+json+"\n");

        System.out.println("Digite o número da página que deseja: ");
        var pageNumber = sc.nextLine();
        var address = URL_BASE;

        address = address + "?page=" + pageNumber;
        json = this.consumeApi.obtainData(address);
        System.out.println(address);

        var results = convertData.obtainData(json, Results.class);
        /*results.results().stream()
                .sorted(Comparator.comparing(Data::rating).reversed())
                .forEach(System.out::println);*/

        List<Results> resultsList = new ArrayList<>();


        System.out.println("Até qual página deseja ver?");
        var numberPage = sc.nextInt();
        for (int i = 1; i <= numberPage; i++) {
            var pageAddress = URL_BASE + "?page=" + i;
            json = consumeApi.obtainData(pageAddress);
            Results results1 = convertData.obtainData(json, Results.class);
            resultsList.add(results1);
        }
        System.out.println("\n****************LISTA PAGINADA****************\n");
        resultsList.forEach(System.out::println);

        List<Data> sortedData = results.results().stream()
                        .filter(data -> data.release() != null && data.release().getYear() <= 2009)
                                .sorted(Comparator.comparing(Data::rating).reversed())
                                        .collect(Collectors.toList());

        sortedData.forEach(System.out::println);





    }

}
