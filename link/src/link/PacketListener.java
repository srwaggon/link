package link;

import java.io.IOException;

import link.packet.Packet;

public class PacketListener extends Thread {
  
  private final PacketHandler packetHandler;
  private Connection connection;
  
  public PacketListener(PacketHandler packetHandler) {
    this.packetHandler = packetHandler;
  }
  
  public void handleConnection(Connection connection) {
    this.connection = connection;
  }
  
  @Override
  public void run() {
    while (connection.isConnected()) {
      try {
        packetHandler.handlePacket(connection.readPacket());
        Thread.sleep(10);
      } catch (IOException ioe) {
        disconnect();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void disconnect() {
    connection.disconnect();
    packetHandler.removePacketListener(this);
  }
  
  public void send(Packet packet) {
    connection.send(packet);
  }
}
