package task18_transportation_local_date_and_streams.reporting;

import task18_transportation_local_date_and_streams.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
