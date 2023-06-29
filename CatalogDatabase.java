import java.sql.DriverManager;
import java.sql.SQLException;


public class CatalogDatabase extends AbstractDatabase{


    public CatalogDatabase() {
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
        try {

            // Σύνδεση με την νέα βάση δεδομένων
            String deliveryDbUrl =DB_URL + "MYDB?useSSL=false";
            connection = DriverManager.getConnection(deliveryDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            //Κατηγοριοποίηση παραμέτρων
            String product_name = parameters[1];
            float product_price = Float.parseFloat(parameters[2]);


            // Δημιουργία του πίνακα catalog
            String insertNewRow = "INSERT INTO CATALOG (" +
                    "VALUES (" + product_name + "," + product_price+ ")"+
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
       try {

            // Σύνδεση με την νέα βάση δεδομένων
            String deliveryDbUrl = DB_URL;
            connection = DriverManager.getConnection(deliveryDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();


            // Δημιουργία του πίνακα catalog
            String deleteRow = "DELETE FROM CATALOG WHERE PRODUCT_ID = " ;
            statement.executeUpdate(deleteRow);

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

    public void AddColumn(String columnName, String datatype){
        try {

            // Σύνδεση με την νέα βάση δεδομένων
            String deliveryDbUrl = DB_URL;
            connection = DriverManager.getConnection(deliveryDbUrl, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();


            // Δημιουργία του πίνακα catalog
            String addColumn = "ALTER TABLE CATALOG ADD COLUMN " + columnName + " " + datatype +";";
            statement.executeUpdate(addColumn);

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

    
}
