import java.sql.*;

public class DockerConnectMySQL {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/baza";

   static final String USER = "pkalasa";
   static final String PASS = "root";
   
   public static void main(String[] args) throws InterruptedException{
   Connection conn = null;
   Statement stmt = null;
   Boolean baseExist = false;
   Boolean login = true;
   String sql;
   
    while(login) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");            
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            login = false; 
        }catch(SQLException se){
    Thread.sleep(10000);
        }catch(Exception e){
    Thread.sleep(10000);
        }finally{
    System.out.println("Connecting to database...");
        }
    }
    
    try{        
      System.out.println("Check if table in base exist");
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "Persons", null);
        while (rs.next()) {
            System.out.println("Table Exist");
            baseExist = true;
        }
        rs = null;  
        if(!baseExist){
          System.out.println("Creating Table");
          stmt = conn.createStatement();
          sql = "CREATE TABLE Persons (PersonID int, LastName varchar(50), FirstName varchar(50), Address varchar(50), City varchar(50) )";
          stmt.executeUpdate(sql);
          stmt = null;
        }     
        stmt = conn.createStatement();
        System.out.println("Inserting Data to Table");
        sql = "INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (1, 'Kowalski', 'Jan', 'DLuga','Lublin'), (2, 'Nowak', 'Edyta', 'Jasna','Warszawa'), (3, 'Kozak', 'Mariusz', 'Debowa','Gdansk')";
        stmt.executeUpdate(sql);   
        stmt = null;
     
        stmt = conn.createStatement();
        sql = "SELECT PersonID, FirstName, LastName, Address, City FROM Persons";
        rs = stmt.executeQuery(sql);

      while(rs.next()){
        int id  = rs.getInt("PersonID");
          String first = rs.getString("FirstName");
          String last = rs.getString("LastName");
      String address = rs.getString("Address");
      String city = rs.getString("City");
    
          System.out.println("ID: " + id);
          System.out.println(", First: " + first);
          System.out.println(", Last: " + last);
      System.out.println(", Address: " + address);
      System.out.println(", City: " + city);
        }
        
        rs.close();
        stmt.close();
        conn.close();
      
    }catch(SQLException se){
        se.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }finally{
    try{
          if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        
        }
        try{
        if(conn!=null) conn.close();
        }catch(SQLException se){
        se.printStackTrace();
        }
    }
  }
}
