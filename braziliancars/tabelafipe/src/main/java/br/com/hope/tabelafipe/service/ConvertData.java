package br.com.hope.tabelafipe.service;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.util.List;
public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obtainData(String json, Class<T> tClass) {
        try {
            return this.mapper.readValue(json, tClass);
        } catch (JsonProcessingException var4) {
            JsonProcessingException e = var4;
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obtainList(String json, Class<T> tClass) {
        CollectionType list = this.mapper.getTypeFactory().constructCollectionType(List.class, tClass);

        try {
            return (List) this.mapper.readValue(json, list);
        } catch (JsonProcessingException var5) {
            JsonProcessingException e = var5;
            throw new RuntimeException(e);
        }
    }
}
