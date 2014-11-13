package myutils.connection;

import java.sql.SQLException;

/**
 * @author beenotung
 */
public class MySecureInfo {

	// digit ocean server
	public static boolean needPortForwarding = true;
	private static String sshHost = "128.199.172.14";
	private static String sshUsername = "beeno";
	private static String sshPassword = "wpc1415";
	private static int portforwardLocalPort = 1234;
	private static String portforwardRemoteHost = "localhost";
	private static int portforwardRemotePort = 3306;
	private static String mysqlProtocol = "jdbc:mariadb";// jdbc:mysql
	private static String mysqlHost = "localhost";
	private static int mysqlPort = portforwardLocalPort;
	private static String mysqlUsername = "beeno";
	private static String mysqlPassword = "wpc1415";
	private static String mysqlDatabasename = "beeno";

	// local server
	// private static String mysqlProtocol = "jdbc:mysql";// jdbc:mariadb
	// private static int mysqlPort = 3306;
	// private static String mysqlUsername = "root";
	// private static String mysqlPassword = "mysqlB(10v2TC";
	// private static String mysqlDatabasename = "beeno"; // wholesaler

	/**
	 * @return my secure info @
	 */
	public static MySSHInfo getMySSHInfo() {
		return new MySSHInfo(sshHost, sshUsername, sshPassword);
	}

	public static MyPortforwardInfo getMyPortforwardInfo() {
		return new MyPortforwardInfo(portforwardLocalPort, portforwardRemoteHost, portforwardRemotePort);
	}

	public static MySqlServerInfo getMySqlServerInfo() {
		return new MySqlServerInfo(mysqlProtocol, mysqlHost, mysqlPort, mysqlDatabasename, mysqlUsername,
				mysqlPassword);
	}

	public static void switchToLocal() throws SQLException {
		needPortForwarding = false;
		MyDatabaseConnector.disconnect();
		mysqlProtocol = "jdbc:mysql";
		mysqlPort = 3306;
		// mysqlUsername = "beeno";
		// mysqlPassword = "Asd770cc8";
		mysqlUsername = "root";
		mysqlPassword = "mysqlB(10v2TC";
		mysqlDatabasename = "beeno"; // wholesaler
	}

	public static void switchToSSH() throws SQLException {
		needPortForwarding = true;
		MyDatabaseConnector.disconnect();
		mysqlProtocol = "jdbc:mariadb";
		mysqlPort = portforwardLocalPort;
		mysqlUsername = "beeno";
		mysqlPassword = "wpc1415";
		mysqlDatabasename = "beeno";
	}
}
