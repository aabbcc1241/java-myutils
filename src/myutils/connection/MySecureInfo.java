package myutils.connection;

import java.sql.SQLException;

/**
 * @author beenotung
 */
@SuppressWarnings({"FieldCanBeLocal", "CanBeFinal", "UnusedDeclaration"})
class MySecureInfo {
    // digit ocean server
    public static boolean needPortForwarding = true;
    private static String sshHost = "128.199.172.14";
    private static String sshUsername = "beeno";
    private static String sshPassword = "wpc1415";
    private static int portForwardLocalPort = 1234;
    private static String portForwardRemoteHost = "localhost";
    private static int portForwardRemotePort = 3306;
    private static String mysqlProtocol = "jdbc:mariadb";// jdbc:mysql
    private static String mysqlHost = "localhost";
    private static int mysqlPort = portForwardLocalPort;
    private static String mysqlUsername = "beeno";
    private static String mysqlPassword = "wpc1415";
    private static String mysqlDatabaseName = "beeno";
    // local server
    // private static String mysqlProtocol = "jdbc:mysql";// jdbc:mariadb
    // private static int mysqlPort = 3306;
    // private static String mysqlUsername = "root";
    // private static String mysqlPassword = "mysqlB(10v2TC";
    // private static String mysqlDatabaseName = "beeno"; // wholesaler

    /**
     * @return my secure info @
     */
    public static MySSHInfo getMySSHInfo() {
        return new MySSHInfo(sshHost, sshUsername, sshPassword);
    }

    public static MyPortForwardInfo getMyPortForwardInfo() {
        return new MyPortForwardInfo(portForwardLocalPort, portForwardRemoteHost, portForwardRemotePort);
    }

    public static MySqlServerInfo getMySqlServerInfo() {
        return new MySqlServerInfo(mysqlProtocol, mysqlHost, mysqlPort, mysqlDatabaseName, mysqlUsername,
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
        mysqlDatabaseName = "beeno"; // wholesaler
    }

    public static void switchToSSH() throws SQLException {
        needPortForwarding = true;
        MyDatabaseConnector.disconnect();
        sshHost = "128.199.172.14";
        sshUsername = "beeno";
        sshPassword = "wpc1415";
        portForwardLocalPort = 1234;
        portForwardRemoteHost = "localhost";
        portForwardRemotePort = 3306;
        mysqlProtocol = "jdbc:mariadb";
        mysqlPort = portForwardLocalPort;
        mysqlUsername = "beeno";
        mysqlPassword = "wpc1415";
        mysqlDatabaseName = "beeno";
    }
}
