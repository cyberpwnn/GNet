package org.cyberpwn.gnet.packet;

import java.io.IOException;

public class PacketException extends IOException
{
	private static final long serialVersionUID = 1L;

	public PacketException(String msg)
	{
		super(msg);
	}
}
