package org.cyberpwn.gnet.server;

import org.cyberpwn.gnet.packet.IPacket;
import org.cyberpwn.gnet.packet.PacketHandler;

public interface IServer
{
	public int getPort();

	public PacketHandler getHandler();

	public IPacket onPacketReceived(IPacket p);
}
