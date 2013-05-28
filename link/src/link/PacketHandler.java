package link;

import link.packet.Packet;

public interface PacketHandler {
  
  public void handlePacket(Packet packet);
  
  public void removePacketListener(PacketListener packetListener);
}
