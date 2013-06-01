package link.packet;

public class MsgPacket extends Packet {
  private static final long serialVersionUID = 354360194351032753L;
  private final String msg;
  
  public MsgPacket(String msg) {
    super(0);
    this.msg = msg;
  }
  
  public String getMessage() {
    return msg;
  }
  
}
