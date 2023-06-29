import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




public abstract class AbstractDatabase {
    

   protected static final String DB_URL = "jdbc:mysql://localhost:3306/";
   protected static final String DB_USER = "root";
   protected static final String DB_PASSWORD = "Maths!22993366";
   Connection connection = null;
   Statement statement = null;
   String databaseName;


   public abstract void Insert(String[] parameters);

   public abstract void Delete(String[] parameters);


   public void DropDatabase(){
      try {
         // Σύνδεση με την βασική βάση δεδομένων
         connection = DriverManager.getConnection(DB_URL + "MYDB?useSSL=false", DB_USER, DB_PASSWORD);
         statement = connection.createStatement();
         statement.executeUpdate("DROP TABLE " + databaseName);

      }
      catch(SQLException e){
         e.printStackTrace();
      }
        
   }
}
