package org.cyberpwn.gnet.packet;

import org.cyberpwn.gnet.streams.writable.Streamable;

public interface IPacket extends Streamable
{
	public int getId();

	public PacketBinding getBinding();

	public String getPacketName();
}
