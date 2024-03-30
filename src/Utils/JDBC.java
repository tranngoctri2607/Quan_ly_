package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String sqlServer = "localhost";
	private static String port = "1433";
	private static String user = "NewLoginName";
	private static String passWord = "YourPassword";
	private static String database = "Gear2";
	private static String url = "jdbc:sqlserver://" + sqlServer + ":" + port + ";user=" + user + ";password=" + passWord
			+ ";databaseName=" + database + ";encrypt=false";

	public static String url() {
		return url;
	}
}