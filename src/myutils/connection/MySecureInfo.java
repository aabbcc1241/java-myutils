package myutils.connection;

/**
 * @author beenotung
 */
public class MySecureInfo {

	// digit ocean server
	private static final String sshHost = "128.199.172.14";
	private static final String sshUsername = "beeno";
	private static final String sshPassword = "wpc1415";
	private static final int portforwardLocalPort = 1234;
	private static final String portforwardRemoteHost = "localhost";
	private static final int portforwardRemotePort = 3306;
	private static final String mysqlProtocol = "jdbc:mariadb";// jdbc:mysql
	private static final String mysqlHost = "localhost";
	private static int mysqlPort = portforwardLocalPort;
	private static String mysqlUsername = "beeno";
	private static String mysqlPassword = "wpc1415";
	private static String mysqlDatabasename = "beeno";

	// local server
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
		return new MyPortforwardInfo(portforwardLocalPort, portforwardRemoteHost,
				portforwardRemotePort);
	}

	public static MySqlServerInfo getMySqlServerInfo() {
		return new MySqlServerInfo(mysqlProtocol, mysqlHost, mysqlPort,
				mysqlDatabasename, mysqlUsername, mysqlPassword);
	}

	public static void switchToLocal() {
		mysqlPort = 3306;
		mysqlUsername = "root";
		mysqlPassword = "mysqlB(10v2TC";
		mysqlDatabasename = "beeno"; // wholesaler
	}

	public static void switchToSSH() {
		mysqlPort = portforwardLocalPort;
		mysqlUsername = "beeno";
		mysqlPassword = "wpc1415";
		mysqlDatabasename = "beeno";
	}
}
