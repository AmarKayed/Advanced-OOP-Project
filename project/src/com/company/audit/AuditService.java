package com.company.audit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public interface AuditService {

    public void createLog(String filePath, String option);

    public void log(String option);
}
