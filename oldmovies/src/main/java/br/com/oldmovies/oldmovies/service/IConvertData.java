package br.com.oldmovies.oldmovies.service;

import java.util.List;

public interface IConvertData {
    <T> T obtainData (String json, Class<T> tClass);

    <T>List<T> obtainList (String json, Class<T> tClass);
}
