package com.company.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvServiceImpl<T>{

    public void write(T ob) {
        String filePath = "src\\com\\company\\resources\\" + ob.getClass().getSimpleName() + ".csv";

        try (FileWriter pw = new FileWriter(filePath, true)) {
            pw.append("\n" + convertToCsvFormat(ob));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String convertToCsvFormat(T ob) {

        // Getting rid of unwanted characters

        String[] separated = ob.toString()
                .replaceAll("[{}]", ",")
                .split(",");


        List<String> content = Arrays.asList(separated);

        content = content.stream()
                .filter(x -> x.split("=").length > 1)
                .filter(x -> !x.split("=")[1].equals("Customer"))
                .map(x->x.split("=")[1].replaceAll("'",""))
                .collect(Collectors.toList());

        String result = String.join(";", content);  // Final CSV Row Value

        System.out.println(result);

        return result;
    }

    public void read() {

    }
}
