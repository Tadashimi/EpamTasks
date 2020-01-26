package task16_transportation_lambda.reporting;

import task16_transportation_lambda.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
