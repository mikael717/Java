package br.com.hope.tabelafipe.services;

import java.util.List;

public interface IConvertData {
    <T> T obtainData (String json, Class<T> tClass);

    <T> List<T> obtainList (String json, Class<T>tClass);
}
