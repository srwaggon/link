package link.packet;

import java.io.Serializable;

/* Packet Class
 * 
 * Represents a packet of data sent between Server and Client.
 * 
 * Abstract class so that there are no generic packets -- every packet will have a purpose.
 */
public class Packet implements Serializable {
  
  private static final long serialVersionUID = 2347350252515231743L;
  public final PacketCode code;
  
  /*
   * Require the use of this constructor; Require that every packet has a type
   */
  public Packet(PacketCode code) {
    this.code = code;
  }
  
  public PacketCode getCode() {
    return code;
  }
}
