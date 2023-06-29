import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class OrderDatabase extends AbstractDatabase {



    public OrderDatabase() {
        //Μετατροπή LocalDate σε String
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateString = currentDate.format(formatter);

        try {

            // Σύνδεση με την νέα βάση δεδομένων
            String orderDbUrl = DB_URL + "MYDB?useSSL=false";
            connection = DriverManager.getConnection(orderDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            // Δημιουργία του πίνακα για το συγκεκριμένο delivery name (παράδειγμα)
            String createTableQuery = "CREATE TABLE IF NOT EXISTS ORDERS_" + dateString + "(" +
                    "ORDER_ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "number INT," +
                    "delivery_name VARCHAR(100)," +
                    "way_of_payment VARCHAR(50)," +
                    "amount DECIMAL(5, 2)" +
                    ")"; 
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
