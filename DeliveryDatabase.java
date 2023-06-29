import java.sql.DriverManager;
import java.sql.SQLException;

public class DeliveryDatabase extends AbstractDatabase{

    public DeliveryDatabase() {
        try {

            // Σύνδεση με την νέα βάση δεδομένων
            String deliveryDbUrl = DB_URL + "MYDB?useSSL=false"; 
            connection = DriverManager.getConnection(deliveryDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            // Δημιουργία του πίνακα catalog
            String createTableQuery = "CREATE TABLE IF NOT EXISTS DELIVERY_NAMES (" +
                    "DELIVERY_ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "DELIVERY_NAME VARCHAR(50)," +
                    "DELIVERY_SURNAME VARCHAR(50)," +
                    "DELIVERY_ADT VARCHAR(50)," +
                    "DELIVERY_AFM VARCHAR(50)," +
                    "DELIVERY_AMKA VARCHAR(50)," +
                    "DELIVERY_PHONE1 VARCHAR(50)," +
                    "DELIVERY_PHONE2 VARCHAR(50)," +
                    "DELIVERY_TK VARCHAR(50)," +
                    "DELIVERY_ADDRESS VARCHAR(50)" +
                    ") AUTO_INCREMENT = 4001";
            statement.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Insert(String[] parameters) {
        try {

            // Σύνδεση με την νέα βάση δεδομένων
            String deliveryDbUrl = DB_URL + "MYDB?useSSL=false";
            connection = DriverManager.getConnection(deliveryDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

           


            // Δημιουργία του πίνακα catalog
            String insertNewRow = "INSERT INTO CATALOG (" +
                    "VALUES (" + parameters[0] + "," + parameters[1] + "," + parameters[2] + "," + parameters[3] + "," + parameters[4] + "," + parameters[5] 
                    + "," + parameters[6] + "," + parameters[7] + "," + parameters[8] +")"+
                    ")";
            statement.executeUpdate(insertNewRow);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Delete(String[] parameters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }



    
}
