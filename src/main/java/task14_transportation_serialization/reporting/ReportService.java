package task14_transportation_serialization.reporting;

import task14_transportation_serialization.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
