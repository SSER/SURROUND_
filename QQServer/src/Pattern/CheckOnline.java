package Pattern;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dbop.SqliteJBDC;
import Common.*;

public class CheckOnline {
    public String checkOnline(Object u, SqliteJBDC jbdc){
    	String res = null;
    	User user = (User)u;
    	String Username = user.getId();
    	String QUERY = "SELECT USERNAME FROM User WHERE STATE=ON";
    	ResultSet rs = jbdc.query(QUERY); 
         try {
        	 while ( rs.next() ) {
              	  String id = rs.getString("username");
              	  String PassWord = rs.getString("password");
              	  if (res.isEmpty()){
              	      res += id;
              	  }
              	  else {
              		  res += (id + ",");
              	  }
                    System.out.println(id + " " + PassWord + "\n");
                }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return res;
    }
}
