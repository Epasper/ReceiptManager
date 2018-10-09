package BootCamp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportCsvPrinter implements Report.ReportPrinter {

    File file;

    public ReportCsvPrinter(File fileName) {
        this.file = fileName;
    }

    @Override
    public void print(ArrayList<String> columns, ArrayList<ArrayList<String>> data) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            int numberOfChars = 0;
            for (int i = 0; i < columns.size(); i++) {
                numberOfChars += columns.get(i).length();
                fileWriter.write(columns.get(i));
                if (i != columns.size() - 1) {
                    fileWriter.write(",");
                    numberOfChars += 5;
                } else {
                    fileWriter.newLine();
                }
            }
            for (ArrayList<String> a : data) {
                for (int i = 0; i < a.size(); i++) {
                    fileWriter.write(a.get(i));
                    if (i != a.size() - 1) {
                        fileWriter.write(",");
                    } else {
                        fileWriter.newLine();
                    }
                }
            }

//            fileWriter.write("test");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
