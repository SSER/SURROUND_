package Pattern;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dbop.SqliteJBDC;
import Common.*;

public class CheckOnline {
	/*
	 * 从数据库查询，这个用于以后阶段的添加好友功能实现时需要使用到
	 */
    public String checkOnlines(Object u, SqliteJBDC jbdc){
    	String res = null;
    	User user = (User)u;
    	String Username = user.getId();
    	String QUERY = "SELECT USERNAME FROM User WHERE STATE=ON";
    	ResultSet rs = jbdc.query(QUERY); 
         try {
        	 while ( rs.next() ) {
              	  String id = rs.getString("username");
              	  String PassWord = rs.getString("password");
              	  if (res == null){
              	      res = id;
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
         if (res == null){
        	 res = "-1";
         }
         return res;
    }
    public String checkOnlines(){
    	String res = "";
    	ThreadFlag Onlinelist = new ThreadFlag();
    	res = Onlinelist.getOnlineUser();
    	System.out.println("in CheckOnline 41 : " + res);
    	return res;
    }
}
