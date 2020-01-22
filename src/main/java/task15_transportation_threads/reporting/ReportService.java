package task15_transportation_threads.reporting;

import task15_transportation_threads.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
