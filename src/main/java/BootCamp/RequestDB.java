package BootCamp;

import java.sql.*;
import java.util.ArrayList;

public class RequestDB {

    private final Connection connection;
    PreparedStatement pst;

    public RequestDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:", "sa", "sa");
        Statement st = connection.createStatement();
        Boolean rs = st.execute("CREATE TABLE requests (id IDENTITY AUTO_INCREMENT PRIMARY KEY, clientId varchar(6), requestId long, name VARCHAR(255), quantity int, price double)");
        pst = connection.prepareStatement("INSERT INTO requests(clientId, requestId, name, quantity, price) VALUES(?,?,?,?,?)");
    }

    public void addRequests(ArrayList<Request> reqList) throws SQLException {
        for (Request request : reqList) {
            pst.setString(1, request.getClientId());
            pst.setLong(2, request.getRequestId());
            pst.setString(3, request.getName());
            pst.setInt(4, request.getQuantity());
            pst.setDouble(5, request.getPrice());
            pst.executeUpdate();
        }

    }

    public ReportGenerator getReportGenerator()
    {
        return new ReportGenerator();
    }

    public class ReportGenerator {

        private Report fillReport (PreparedStatement prep) throws SQLException {

            ResultSet results = prep.executeQuery();

            int columnCount = results.getMetaData().getColumnCount();

            ArrayList<String> columnNames= new ArrayList<String>();
            for (int i = 0; i < columnCount; i++) {
                columnNames.add(results.getMetaData().getColumnName(i+1));
            }

            Report report = new Report(columnNames);

            while (results.next()) {
                ArrayList<String> resultRow = new ArrayList<String>();
                for (int i = 0; i < columnCount; i++) {
                    resultRow.add(String.valueOf(results.getObject(i+1)));
                }
                report.addReportRow(resultRow);
            }
            return report;
        }

        public Report totalRequests () throws SQLException {
            String requestString = "SELECT COUNT (1) AS Number_of_requests FROM requests";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            return fillReport(prep1);
        }

        public Report clientRequests (int clientId) throws SQLException {
            String requestString = "SELECT clientID AS Client_ID, COUNT (1) AS Number_of_requests FROM requests WHERE clientId = ?";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            prep1.setInt(1, clientId);
            return fillReport (prep1);
        }

        public Report sumOfPrices () throws SQLException {
            String requestString = "SELECT SUM (price) AS Sum_Of_Prices FROM requests";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            return fillReport (prep1);
        }

        public Report clientSumOfPrices (int clientId) throws SQLException {
            String requestString = "SELECT clientID AS Client_ID, SUM (price) AS Sum_Of_Prices FROM requests WHERE clientId = ?";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            prep1.setInt(1, clientId);
            return fillReport(prep1);
        }

        public Report listOfRequests () throws SQLException {
            String requestString = "SELECT clientID AS Client_ID, requestId AS Request_Id, name AS Name, quantity AS Quantity, price AS Price FROM requests";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            return fillReport(prep1);
        }

        public Report listOfClientRequests (int clientId) throws SQLException {
            String requestString = "SELECT clientID AS Client_ID, requestId AS Request_Id, name AS Name, quantity AS Quantity, price AS Price FROM requests WHERE clientId = ?";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            prep1.setInt(1, clientId);
            return fillReport(prep1);
        }

        public Report averageOfPrices () throws SQLException {
            String requestString = "SELECT AVG(price) AS Average_Of_Prices FROM requests";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            return fillReport(prep1);
        }

        public Report averageOfClientPrices (int clientId) throws SQLException {
            String requestString = "SELECT AVG(price) AS Average_Of_Prices FROM requests WHERE clientId = ?";
            PreparedStatement prep1 = connection.prepareStatement(requestString);
            prep1.setInt(1, clientId);
            return fillReport(prep1);
        }
    }
}
