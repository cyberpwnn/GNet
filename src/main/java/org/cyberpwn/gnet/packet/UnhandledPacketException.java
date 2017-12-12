package org.cyberpwn.gnet.packet;

public class UnhandledPacketException extends PacketException
{
	private static final long serialVersionUID = 112L;

	public UnhandledPacketException(String msg)
	{
		super(msg);
	}
}
