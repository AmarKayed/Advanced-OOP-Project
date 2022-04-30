package com.company.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvServiceImpl<T>{

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

    public void write(T ob) {
        String filePath = "src\\com\\company\\resources\\" + ob.getClass().getSimpleName() + ".csv";

        try (FileWriter pw = new FileWriter(filePath, true)) {
            File file = new File(filePath);

            String insert = convertToCsvFormat(ob);

            if(contains(ob, insert))
                pw.append("\n" + insert);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<List<String>> read(T ob) {
        String filePath = "src\\com\\company\\resources\\" + ob.getClass().getSimpleName() + ".csv";

        List<List<String>> content = new ArrayList<>();

        try (FileInputStream in = new FileInputStream(filePath)) {
            Scanner sc = new Scanner(in);
            if(sc.hasNextLine())    // Ignoring Header Line
                sc.nextLine();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                content.add(Arrays.asList(line.split(";")));
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong in readUsingFileInputStream method", e);
        }
    }

    public boolean contains(T ob,String log){
        String filePath = "src\\com\\company\\resources\\" + ob.getClass().getSimpleName() + ".csv";

        try (FileInputStream in = new FileInputStream(filePath)) {
            Scanner sc = new Scanner(in);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains(log))
                    return false;
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong in readUsingFileInputStream method", e);
        }
        return true;
    }

}
