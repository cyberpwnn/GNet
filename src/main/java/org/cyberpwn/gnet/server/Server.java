package org.cyberpwn.gnet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.cyberpwn.gnet.packet.IPacket;
import org.cyberpwn.gnet.packet.PacketBinding;
import org.cyberpwn.gnet.packet.PacketHandler;
import org.cyberpwn.gnet.streams.ISS;
import org.cyberpwn.gnet.streams.OSS;

public abstract class Server extends Thread implements IServer
{
	private int port;
	private ServerSocket socket;
	private PacketHandler handler;

	public Server(int port)
	{
		this.handler = new PacketHandler(PacketBinding.SERVER_BOUND, null, null);
		this.port = port;
	}

	@Override
	public PacketHandler getHandler()
	{
		return handler;
	}

	@Override
	public void run()
	{
		try
		{
			pstart();
		}

		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}

		while(!interrupted())
		{
			try
			{
				Socket client = socket.accept();
				System.out.println("Connection Received");
				OSS out = new OSS(client.getOutputStream());
				ISS in = new ISS(client.getInputStream());
				handler.redirect(out, in);
				IPacket from = handler.read();
				System.out.println("<- " + from.getPacketName());
				IPacket to = onPacketReceived(from);
				handler.write(to);
				System.out.println("-> " + to.getPacketName());
				out.flush();
				client.close();
			}

			catch(SocketTimeoutException e)
			{
				continue;
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		try
		{
			pstop();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void pstart() throws IOException
	{
		socket = new ServerSocket(port);
		socket.setSoTimeout(1000);
		socket.setPerformancePreferences(1, 2, 0);
	}

	private void pstop() throws IOException
	{
		socket.close();
	}

	@Override
	public int getPort()
	{
		return port;
	}

	@Override
	public abstract IPacket onPacketReceived(IPacket p);
}
