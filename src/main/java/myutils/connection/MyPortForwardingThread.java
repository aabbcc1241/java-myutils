package myutils.connection;

import com.jcraft.jsch.*;

import javax.swing.*;

/**
 * @author beenotung
 */
@SuppressWarnings("UnusedDeclaration")
class MyPortForwardingThread implements Runnable {
  private final String host;
  private final String user;
  private final String password;
  private final int localPort;
  private final String remoteHost;
  private final int remotePort;
  @SuppressWarnings({"FieldCanBeLocal"})
  private int assignedPort;
  private Thread thread;
  private boolean alive = false;

  public MyPortForwardingThread(String host, String user, String password, int localPort,
                                String remoteHost, int remotePort) {
    this.host = host;
    this.user = user;
    this.password = password;
    this.localPort = localPort;
    this.remoteHost = remoteHost;
    this.remotePort = remotePort;
  }

  public MyPortForwardingThread(MySSHInfo mySSHInfoForm,
                                MyPortForwardInfo myPortForwardInfoForm) {
    this.host = mySSHInfoForm.getHost();
    this.user = mySSHInfoForm.getUsername();
    this.password = mySSHInfoForm.getPassword();
    this.localPort = myPortForwardInfoForm.getLocalPort();
    this.remoteHost = myPortForwardInfoForm.getRemoteHost();
    this.remotePort = myPortForwardInfoForm.getRemotePort();
  }

  private void connect() throws JSchException {
    JSch jsch = new JSch();
    Session session = jsch.getSession(user, host, 22);
    // username and password will be given via UserInfo interface.
    UserInfo ui = new MyUserInfo(password);
    session.setUserInfo(ui);
    session.connect();
    // Channel channel=session.openChannel("shell");
    // channel.connect();
    assignedPort = session.setPortForwardingL(localPort, remoteHost, remotePort);
  }

  /**
   * thread staff *
   */
  @SuppressWarnings("static-access")
  @Override
  public void run() {
    try {
      connect();
    } catch (JSchException e) {
      System.out.println("failed to tunnel ssh");
      System.out.println(e.toString());
      e.printStackTrace();
    }
    while (alive) {
      //noinspection EmptyCatchBlock
      try {
        thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }

  public void start() {
    if (thread == null)
      thread = new Thread(this);
    alive = true;
    thread.start();
  }

  public void stop() {
    alive = false;
  }

  private static class MyUserInfo implements UserInfo, UIKeyboardInteractive {
    private final String password;

    public MyUserInfo(String password) {
      this.password = password;
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public boolean promptYesNo(String str) {
      return true;
    }

    @Override
    public String getPassphrase() {
      return null;
    }

    @Override
    public boolean promptPassphrase(String message) {
      return true;
    }

    @Override
    public boolean promptPassword(String message) {
      return true;
    }

    @Override
    public void showMessage(String message) {
      JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public String[] promptKeyboardInteractive(String destination, String name,
                                              String instruction, String[] prompt, boolean[] echo) {
      return null;
    }
  }
}
