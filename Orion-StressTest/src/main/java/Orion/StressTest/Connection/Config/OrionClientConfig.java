package Orion.StressTest.Connection.Config;

public class OrionClientConfig {
    private String hostName;
    private int port;
    private String ssoTicket;

    public OrionClientConfig(String hostName, int port, String ssoTicket) {
        this.hostName = hostName;
        this.port = port;
        this.ssoTicket = ssoTicket;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSsoTicket() {
        return ssoTicket;
    }

    public void setSsoTicket(String ssoTicket) {
        this.ssoTicket = ssoTicket;
    }
}
