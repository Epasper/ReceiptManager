package bootcamp;

import org.junit.Test;

import java.util.ArrayList;


public class ReportTest {

//    @Test(expected = RuntimeException.class)
//    public void FactoryShouldThrowUnsupportedFiletypeOnTxt()
//    {
//        RequestBuilderFactory.getBuilder("txt");
//    }

    ArrayList<String> data;
    ArrayList<String> columnNames;


    @Test(expected = RuntimeException.class)
    public void addReportRowShouldThrowException() {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("a");
        columnNames.add("b");
        Report report = new Report(columnNames);



        ArrayList<String> wrongData = new ArrayList<>();
        wrongData.add("Test");

        report.addReportRow(wrongData);
    }

    @Test
    public void print() {
    }
}
