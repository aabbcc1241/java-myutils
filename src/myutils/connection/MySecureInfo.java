package myutils.connection;

import java.sql.SQLException;

/**
 * @author beenotung
 */
public class MySecureInfo {

    // digit ocean server
    public static boolean needPortForwarding = true;
    private static String sshHost;
    private static String sshUsername;
    private static String sshPassword;
    private static int portforwardLocalPort;
    private static String portforwardRemoteHost;
    private static int portforwardRemotePort;
    private static String mysqlProtocol;
    private static String mysqlHost;
    private static int mysqlPort = portforwardLocalPort;
    private static String mysqlUsername;
    private static String mysqlPassword;
    private static String mysqlDatabasename;


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
        mysqlHost = "localhost";
        // mysqlUsername = "beeno";
        // mysqlPassword = "Asd770cc8";
        mysqlUsername = "root";
        mysqlPassword = "mysqlB(10v2TC";
        mysqlDatabasename = "beeno"; // wholesaler
    }

    public static void switchToSSH() throws SQLException {
        needPortForwarding = true;
        MyDatabaseConnector.disconnect();
        sshHost = "128.199.172.14";
        sshUsername = "beeno";
        sshPassword = "wpc1415";
        portforwardLocalPort = 1234;
        portforwardRemoteHost = "localhost";
        portforwardRemotePort = 3306;
        mysqlProtocol = "jdbc:mariadb";
        mysqlPort = portforwardLocalPort;
        mysqlHost = "localhost";
        mysqlUsername = "beeno";
        mysqlPassword = "wpc1415";
        mysqlDatabasename = "beeno";
    }

}
