package myutils.connection;

/**
 * @author beenotung
 */
public class MyPortforwardInfo {
    private final int localPort;
    private final String remoteHost;
    private final int remotePort;

    public MyPortforwardInfo(int localPort, String remoteHost, int remotePort) {
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

}
