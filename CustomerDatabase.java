import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerDatabase extends AbstractDatabase {

    public CustomerDatabase() {
         try {

            // Σύνδεση με την νέα βάση δεδομένων
            String customerDbUrl = DB_URL + "MYDB?useSSL=false";
            connection = DriverManager.getConnection(customerDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            // Δημιουργία του πίνακα catalog
            String createTableQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                    "CUSTOMER_ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "CUSTOMER_NAME VARCHAR(50)," +
                    "CUSTOMER_SURNAME VARCHAR(50)," +
                    "CUSTOMER_PHONE1 INT," +
                    "CUSTOMER_PHONE2 INT," +
                    "CUSTOMER_ADDRESS1 VARCHAR(100),"+
                    "CUSTOMER_ADDRESS2 VARCHAR(100),"+
                    "CUSTOMER_RECENT_ORDER VARCHAR(100)," +
                    "CUSTOMER_MOST_ORDERED_PRODUCT VARCHAR(100)," +
                    "CUSTOMER_EMAIL VARCHAR(100)" +
                    ") AUTO_INCREMENT = 3001";
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Insert'");
    }

    @Override
    public void Delete(String[] parameters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }



}


    

