package org.cyberpwn.gnet.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.cyberpwn.gnet.packet.IPacket;
import org.cyberpwn.gnet.packet.PacketBinding;
import org.cyberpwn.gnet.packet.PacketHandler;
import org.cyberpwn.gnet.streams.ISS;
import org.cyberpwn.gnet.streams.OSS;

public class Client implements IClient
{
	private int port;
	private String address;
	private PacketHandler handler;
	private Socket socket;

	public Client(String address, int port)
	{
		this.port = port;
		this.address = address;
		this.handler = new PacketHandler(PacketBinding.CLIENT_BOUND, null, null);
	}

	private void connect() throws IOException
	{
		socket = new Socket();
		socket.setTrafficClass(0x10);
		socket.setTcpNoDelay(true);
		socket.setSoTimeout(1000);
		socket.setPerformancePreferences(1, 2, 0);
		socket.connect(new InetSocketAddress(address, port), 1000);
	}

	private void disconnect() throws IOException
	{
		socket.close();
	}

	@Override
	public int getPort()
	{
		return port;
	}

	@Override
	public String getAddress()
	{
		return address;
	}

	@Override
	public IPacket sendPacket(IPacket send) throws IOException
	{
		connect();
		ISS in = new ISS(socket.getInputStream()).gzip();
		OSS out = new OSS(socket.getOutputStream()).gzip(1);
		handler.redirect(out, in);
		handler.write(send);
		out.flush();
		IPacket response = handler.read();
		disconnect();

		return response;
	}

	@Override
	public PacketHandler getHandler()
	{
		return handler;
	}
}
