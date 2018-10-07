package BootCamp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class ReportTest extends Report {


    public ReportTest(ArrayList<String> columnNames) {
        super(columnNames);
    }

//    @Test(expected = RuntimeException.class)
//    public void FactoryShouldThrowUnsupportedFiletypeOnTxt()
//    {
//        RequestBuilderFactory.getBuilder("txt");
//    }

    ArrayList<String> data;
    ArrayList<String> columnNames;

    @Before
    public void prepareData() {
        data.add(1, "a");
        data.add(2,"b");
        columnNames.add(1,"a");
    }

    @Test(expected = RuntimeException.class)
    public void addReportRowShouldThrowException() {
        Report report = new Report(columnNames);
        addReportRow(data);
    }

    @Test
    public void print() {
    }
}
