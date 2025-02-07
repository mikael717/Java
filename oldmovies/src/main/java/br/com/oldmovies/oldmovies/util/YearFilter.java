package br.com.oldmovies.oldmovies.util;

import br.com.oldmovies.oldmovies.model.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class YearFilter {
    public List<Data> yearFilter(List<Data> dataList, int limitYear){
        return dataList.stream()
                .filter(d -> d.release() != null && d.release().getYear() <= limitYear)
                .filter(lang -> lang.language() == null || !"ja".equalsIgnoreCase(lang.language()))
                .sorted(Comparator.comparing(Data::rating).reversed())
                .collect(Collectors.toList());
    }
}
