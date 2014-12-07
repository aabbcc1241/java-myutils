package myutils.connection;
/**
 * @author beenotung
 */
@SuppressWarnings("UnusedDeclaration")
public class MySqlServerInfo {
    private final String protocol;
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String databaseName;
    public MySqlServerInfo(String protocol, String host, int port, String databaseName,
                           String username, String password) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }
    public String getUrlWithoutDB() {
        return protocol + "://" + host + ":" + port;
    }
    public String getUrlWithDB() {
        return getUrlWithoutDB() + "/" + databaseName;
    }
    public String getMysqlUsername() {
        return username;
    }
    public String getMysqlPassword() {
        return password;
    }
    public String getDatabaseName() {
        return databaseName;
    }
}
