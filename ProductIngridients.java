import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductIngridients extends AbstractDatabase {

    public ProductIngridients(){
        try {

            // Σύνδεση με την νέα βάση δεδομένων
            String catalogDbUrl = DB_URL + "MYDB?useSSL=false";
            connection = DriverManager.getConnection(catalogDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            // Δημιουργία του πίνακα catalog
            String createTableQuery = "CREATE TABLE IF NOT EXISTS CATALOG (" +
                    "PRODUCT_ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "PRODUCT_NAME VARCHAR(50)," +
                    "PRODUCT_PRICE DECIMAL(5, 2)" +
                    ") AUTO_INCREMENT = 5001";
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
