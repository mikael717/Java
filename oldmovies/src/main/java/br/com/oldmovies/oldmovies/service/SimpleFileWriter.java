package br.com.oldmovies.oldmovies.service;

import br.com.oldmovies.oldmovies.model.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SimpleFileWriter {
    public static void exportToTxt(List<Data> dataList, String filePath){
        try (Writer writer = new FileWriter(filePath)){
            for (Data data : dataList){
                writer.write(data.toString() + "\n");

            }
            System.out.println("arquivo salvo com sucesso em " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("erro ao salvar o arquivo", e);
        }
    }
}
