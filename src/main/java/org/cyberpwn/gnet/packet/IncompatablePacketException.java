package org.cyberpwn.gnet.packet;

public class IncompatablePacketException extends PacketException
{
	private static final long serialVersionUID = 1122L;

	public IncompatablePacketException(String msg)
	{
		super(msg);
	}
}
