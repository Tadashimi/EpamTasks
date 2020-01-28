package task17_transportation_optional.reporting;

import task17_transportation_optional.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
