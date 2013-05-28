package link;

import java.util.Scanner;

import link.packet.MsgPacket;
import link.packet.Packet;

public class Client implements PacketHandler, Runnable {
  
  protected final PacketListener packetListener;
  
  public Client(String ip, int port) {
    packetListener = new PacketListener(this);
    packetListener.handleConnection(new Connection(ip, port));
  }
  
  @Override
  public void handlePacket(Packet packet) {
    System.out.println(((MsgPacket) packet).getMessage());
  }
  
  @Override
  public void removePacketListener(PacketListener packetListener) {
    
  }
  
  @Override
  public void run() {
    Scanner s = new Scanner(System.in);
    while (true) {
      try {
        System.out.print("send?> ");
        packetListener.send(new MsgPacket(s.nextLine()));
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void start() {
    packetListener.start();
    new Thread(this).start();
  }
  
  public static void main(String[] args) {
    new Client("127.0.0.1", 5555).start();
  }
}
