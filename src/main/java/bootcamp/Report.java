package bootcamp;

import java.util.ArrayList;

public class Report {

    ArrayList<ArrayList<String>> reportList = new ArrayList<>();

    public interface ReportPrinter {

        void print(ArrayList<String> columns, ArrayList<ArrayList<String>> data);
    }

    ArrayList<String> columnNames;


    public Report(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
    }

    /**
     * Checks the size of the Adds a row of data to the report.
     * @param rowData - the current row of data
     */

    public void addReportRow(ArrayList<String> rowData) {
        if (rowData.size() != columnNames.size()) {
            throw new RuntimeException("Argument count does not match report header");
        }
        reportList.add(rowData);

    }

    public void print (ReportPrinter printer) {
        printer.print(columnNames , reportList);
    }

}
