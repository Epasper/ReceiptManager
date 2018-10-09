package BootCamp;

import java.util.ArrayList;

class ReportScreenPrinter implements Report.ReportPrinter {

    @Override
    public void print(ArrayList<String> columns, ArrayList<ArrayList<String>> data) {
        int numberOfChars = 0;
        for (int i = 0; i < columns.size(); i++) {
            numberOfChars += columns.get(i).length();
            System.out.print(columns.get(i));
            if (i != columns.size() - 1) {
                System.out.print(", \t");
                numberOfChars += 5;
            } else {
                System.out.println();
            }
        }
        for (int i = 0; i < numberOfChars; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (ArrayList<String> a : data) {
            for (int i = 0; i < a.size(); i++) {
                System.out.print(a.get(i));
                if (i != a.size() - 1) {
                    System.out.print(", \t");
                } else {
                    System.out.println();
                }
            }
        }
    }
}