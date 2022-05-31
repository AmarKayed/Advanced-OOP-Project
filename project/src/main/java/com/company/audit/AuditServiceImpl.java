package com.company.audit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class AuditServiceImpl implements AuditService{

    private static AuditServiceImpl instance;

    private AuditServiceImpl() {
    }

    public static AuditServiceImpl getInstance() {
        if(instance == null)
            instance = new AuditServiceImpl();
        return instance;
    }

    @Override
    public void createLog(String filePath, String option){
        try (FileWriter pw = new FileWriter(filePath, true)){
            pw.append("\n" + Calendar.getInstance().getTime() +";" + option);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String option) {
        String filePath = "src\\main\\java\\com\\company\\resources\\audit.csv";
        try{
            File file = new File(filePath);
            if(file.createNewFile()){
                try (FileWriter pw = new FileWriter(filePath, true)){
                    pw.append("time;action");
                } catch(IOException e){
                    throw new RuntimeException(e);
                }
            }
            createLog(filePath, option);

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
