package Dbop;

import java.sql.*;

public class SqliteJBDC {
    private Connection c = null;
    private Statement stmt = null;
    
    public SqliteJBDC()
    {
    	 try {
    	      Class.forName("org.sqlite.JDBC");
    	      c = DriverManager.getConnection("jdbc:sqlite:User.db");
    	      System.out.println("Opened database successfully");

    	      stmt = c.createStatement();
    	      String sql = "CREATE TABLE IF NOT EXISTS User " +
    	                   "( USERNAME           TEXT    NOT NULL, " +  
    	                   " PASSWORD         	TEXT	NOT NULL)"; 
    	      stmt.executeUpdate(sql);
    	      stmt.close();
    	      c.close();
    	      c = null;
    	    } catch ( Exception e ) {
    	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	      //System.exit(0);
    	    }
    	    System.out.println("Table created successfully");
    }
    
    public boolean insert(String UserName, String passWord)
    {
    	boolean Success = true; 
    	try {
			 Class.forName("org.sqlite.JDBC");
			 c = DriverManager.getConnection("jdbc:sqlite:User.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
             
	         
	         
	         stmt = c.createStatement();
	         String QUERY = "SELECT * from User where USERNAME=\"%s\"";
	         QUERY = String.format(QUERY, UserName);
	         System.out.println(QUERY);
	        //stmt.sta =stmt.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	         ResultSet rs =  stmt.executeQuery(QUERY);
	         
	         if (rs.isAfterLast() == false){
	        	 System.out.println("I'm out");
	        	 return false;
	         }
	         
	         rs.close();
	         
	         String INSERT = "INSERT INTO  User (USERNAME,PASSWORD) " +
	                      "VALUES (%s, %s);";
	         INSERT = String.format(INSERT, UserName, passWord);
	         System.out.println( " I 'M INSERT STMT : " + INSERT);
	         stmt.executeUpdate(INSERT);
	         c.commit();
	         
	         c.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 Success = false;
		}
        
    	return Success;
    }
    /*
     * 支持外部通过sqlite语句访问
     */
    public boolean insert(String stmt_)
    {
    	boolean Success = true; 
    	try {
			 Class.forName("org.sqlite.JDBC");
			 c = DriverManager.getConnection("jdbc:sqlite:User.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         stmt = c.createStatement();
	         System.out.println( " I 'M INSERT STMT : " + stmt_);
	         stmt.executeUpdate(stmt_);
	         c.commit();
	         c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 Success = false;
		}

    	return Success;
    }
    /*
     * 查询账号，密码是否正确
     */
    public boolean query(String UserName, String password)
    {
    	boolean isIn = false;
    	 //String QUERY = "SELECT PASSWORD from User where USERNAME=%s";
    	String QUERY = "SELECT * from User";
 	    QUERY = String.format(QUERY, UserName);
 	    System.out.println("Query is : " + QUERY);
    	try {
    		Class.forName("org.sqlite.JDBC");
    	    c = DriverManager.getConnection("jdbc:sqlite:User.db");
    	    c.setAutoCommit(false);
    	    System.out.println("Opened database successfully");
    	    c.commit();
    	    stmt = c.createStatement();
    	   
    	
    	    ResultSet rs = stmt.executeQuery(QUERY);
    	    System.out.println("size is :" + rs.getFetchSize());
    	    while ( rs.next() ) {
    	        String  PASSWORD = rs.getString("password");
    	        System.out.println(PASSWORD);
    	        System.out.println("password is : " + PASSWORD);
    	        if (PASSWORD.equals(password)){
    	        	isIn = true;
    	        }
    	      }
    	      rs.close();
    	      stmt.close();
    	      c.close();
    	    } catch ( Exception e ) {
    	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	      
    	      //System.exit(0);
    	    }
    	return isIn;
    }
    public ResultSet query(String QUERY)
{
    	ResultSet rs = null;
    	try {
    		Class.forName("org.sqlite.JDBC");
    	    c = DriverManager.getConnection("jdbc:sqlite:User.db");
    	    c.setAutoCommit(false);
    	    System.out.println("Opened database successfully");

    	    stmt = c.createStatement();
    	    //String QUERY = "SELECT PASSWORD FROM User WHERE USERNAME USERNAME=%s";
    	    //QUERY = String.format(QUERY, UserName);
    	     rs = stmt.executeQuery(QUERY);
    	      rs.close();
    	      stmt.close();
    	      c.close();
    	    } catch ( Exception e ) {
    	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	      //System.exit(0);
    	    }
    	return rs;
    }
    /*
     * 更改密码用
     */
    public boolean UPDATE(String UserName, String Password){
    	Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:User.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "UPDATE User set PASSWORD=%s where USERNAME=%s;";
          System.out.println("in line 146 password : " + sql);
          sql = String.format(sql, Password);
          stmt.executeUpdate(sql);
          c.commit();
          

          ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
          while ( rs.next() ) {
        	  String id = rs.getString("username");
              String PassWord = rs.getString("password");
              System.out.println(id + " " + PassWord + "\n");
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return false;
        }
        System.out.println("Operation done successfully");
    	return true;
    }
    /*
     * 通用接口
     */
    public boolean UPDATE(String sql){
    	Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:User.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          //String sql = "UPDATE User set PASSWORD=%s where USERNAME=%s;";
          //System.out.println("in line 146 password : " + sql);
          sql = String.format(sql);
          stmt.executeUpdate(sql);
          c.commit();
          

          ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
          while ( rs.next() ) {
        	  String id = rs.getString("username");
              String PassWord = rs.getString("password");
              System.out.println(id + " " + PassWord + "\n");
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return false;
        }
        System.out.println("Operation done successfully");
    	return true;
    }
}
