package com.company.audit;

public interface AuditService {

    public void createLog(String filePath, String option);

    public void log(String option);
}
