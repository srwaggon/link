package link;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import link.packet.Packet;

public class Server extends Thread implements PacketHandler {
  
  private final List<PacketListener> packetListeners = new ArrayList<PacketListener>();
  private ServerSocket socket = null;
  
  public Server(int port) {
    try {
      socket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public Server(ServerSocket socket) {
    this.socket = socket;
  }
  
  @Override
  public void run() {
    while (true) {
      try {
        System.out.print("Awaiting connection...");
        acceptConnection(socket.accept());
        Thread.sleep(100);
      } catch (IOException ioe) {
        System.out.println("Accept failed");
        ioe.printStackTrace();
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
  
  public void acceptConnection(Socket socket) {
    System.out.println(socket.getInetAddress().toString() + " connecting.");
    Connection clientConnection = new Connection(socket);
    
    // accept connection
    PacketListener receiver = requestLink();
    receiver.handleConnection(clientConnection);
    packetListeners.add(receiver);
    receiver.start();
  }
  
  public PacketListener requestLink() {
    return new PacketListener(this);
  }
  
  @Override
  public void removePacketListener(PacketListener packetListener) {
    packetListeners.remove(packetListener);
  }
  
  @Override
  public void handlePacket(Packet packet) {
    System.out.println(packet);
    for (PacketListener pr : packetListeners) {
      pr.send(packet);
    }
  }
  
  public static void main(String[] args) {
    new Server(5555).start();
  }
}
