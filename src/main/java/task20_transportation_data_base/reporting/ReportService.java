package task20_transportation_data_base.reporting;

import task20_transportation_data_base.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
