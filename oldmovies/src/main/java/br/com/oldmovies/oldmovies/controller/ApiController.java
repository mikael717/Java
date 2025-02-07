package br.com.oldmovies.oldmovies.controller;

import br.com.oldmovies.oldmovies.model.Data;
import br.com.oldmovies.oldmovies.model.Results;
import br.com.oldmovies.oldmovies.service.ConsumeApi;
import br.com.oldmovies.oldmovies.service.ConvertData;
import br.com.oldmovies.oldmovies.service.SimpleFileWriter;
import br.com.oldmovies.oldmovies.util.YearFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ApiController {

    private ConsumeApi consumeApi = new ConsumeApi();
    private ConvertData convertData = new ConvertData();
    private Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "https://api.themoviedb.org/3/tv/top_rated";
    String json;

    public void displayResults() {

        System.out.println("Até qual página deseja ver?");
        var numberPage = sc.nextInt();
        sc.nextLine();

        //Armaneza o resultado de cada página;
        List<Results> resultsList = new ArrayList<>();


        for (int i = 1; i <= numberPage; i++) {
            var address = URL_BASE + "?page=" + i;
            json = consumeApi.obtainData(address);
            Results pageResults = convertData.obtainData(json, Results.class);
            resultsList.add(pageResults);
        }
        System.out.println("\n****************LISTA PAGINADA****************\n");
        resultsList.forEach(System.out::println);

        //Acumula todos os itens (Data) do for em uma única lista;
        List<Data> allData = resultsList.stream()
                .flatMap(resultsPage -> resultsPage.results().stream())
                .collect(Collectors.toList());

        //Aplica o filtro por data e ordena o Dados;
        YearFilter filter = new YearFilter();
        List<Data> filteredData = filter.yearFilter(allData,2009);

        System.out.println("\n****************RESULTADO FILTRADO ACUMULADO****************\n");
        /*sortedData.yearFilter(allData, 2009).forEach(System.out::println);*/
        filteredData.forEach(System.out::println);

       /*String filepath = "bests-tv-show-v003.txt";
       SimpleFileWriter.exportToTxt(filteredData,filepath);*/

        sc.close();
    }

}
