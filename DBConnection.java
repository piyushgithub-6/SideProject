
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {
	static Connection con = null;
	static PreparedStatement prepareStat = null;

	public static void start(long chat_id,String data,String field){
 
		try{
			makeJDBCConnection();
			addData(chat_id,data,field);
			prepareStat.close();
			con.close(); 
		} catch (SQLException e) {
 
			e.printStackTrace();}
		}

	private static void makeJDBCConnection() {
			 
			try {
				Class.forName("com.mysql.jdbc.Driver");
				log("MySQL JDBC Driver Registered!");
			} catch (ClassNotFoundException e) {
				log("couldn't found JDBC driver.");
				e.printStackTrace();
				return;
			}
	 
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/telegram_bot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
				if (con!= null) {
					log("Connection Successful!");
					
				} else {
					log("Failed to make connection!");
				}
			} catch (SQLException e) {
				log("MySQL Connection Failed!");
				e.printStackTrace();
			}
		}
	
	private static void log(String string) {
				System.out.println(string);
		 
			}
	
	private static void addData(long chat_id,String data,String field) {
		try {
		    

		    String sql = "SELECT user_id FROM user where user_id='"+chat_id+"'";
		    prepareStat = con.prepareStatement(sql);
		    ResultSet rs = prepareStat.executeQuery();
		    //STEP 5: Extract data from result set
		    if(rs.next()) {
		    	if(field.equals("language"))
		    	{
		    		String updateQuery="update language set lang1='"+data+"' where user_id="+chat_id+"";
			    	  prepareStat = con.prepareStatement(updateQuery);
			    	  prepareStat.executeUpdate();
		    	}
		    	else if(field.equals("framework"))
		    	{
		    		String updateQuery="update framework set fw1='"+data+"' where user_id="+chat_id+"";
			    	  prepareStat = con.prepareStatement(updateQuery);
			    	  prepareStat.executeUpdate();
		    	}
		    	else {
		    	  String updateQuery="update user set "+field+"='"+data+"' where user_id="+chat_id+"";
		    	  prepareStat = con.prepareStatement(updateQuery);
		    	  prepareStat.executeUpdate();
		    	}
		    }
		    else {
		    	 
			String insertQueryStatement = "INSERT  INTO  user (`user_id`, `name`, `college`, `side_project_reference`, `previous_project`, `confidence`, `github_link`, `level`)  VALUES  (?,?,?,?,?,?,?,?)";
			String insertLanguage="insert into language(`user_id`) value('"+chat_id+"')";
			String insertFrameWork="insert into framework(`user_id`) value('"+chat_id+"')";
			prepareStat = con.prepareStatement(insertQueryStatement);
			prepareStat.setLong(1,chat_id);
			prepareStat.setString(2, data);
			prepareStat.setString(3, "");
			prepareStat.setString(4, "");
			prepareStat.setString(5, "");
			prepareStat.setString(6, "");
			prepareStat.setString(7, "");
			prepareStat.setString(8, "");
			
 
			// execute insert SQL statement
			prepareStat.executeUpdate();
			prepareStat = con.prepareStatement(insertLanguage);
			prepareStat.executeUpdate();
			prepareStat = con.prepareStatement(insertFrameWork);
			prepareStat.executeUpdate();
			log(" added successfully");
		    }
		    rs.close();
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
	}

}
	
