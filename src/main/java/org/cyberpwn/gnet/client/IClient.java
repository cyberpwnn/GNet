package org.cyberpwn.gnet.client;

import java.io.IOException;

import org.cyberpwn.gnet.packet.IPacket;
import org.cyberpwn.gnet.packet.PacketHandler;

public interface IClient
{
	public int getPort();

	public String getAddress();

	public IPacket sendPacket(IPacket send) throws IOException, Exception;

	public PacketHandler getHandler();
}
