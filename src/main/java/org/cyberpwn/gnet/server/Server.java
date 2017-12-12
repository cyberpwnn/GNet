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

public class Server extends Thread implements IServer
{
	private int port;
	private ServerSocket socket;
	private PacketHandler handler;

	public Server(int port)
	{
		this.handler = new PacketHandler(PacketBinding.SERVER_BOUND, null, null);
		this.port = port;
	}

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
				OSS out = new OSS(client.getOutputStream()).gzip(1);
				ISS in = new ISS(client.getInputStream()).gzip();
				handler.redirect(out, in);
				handler.write(onPacketReceived(handler.read()));
				out.flush();
				client.close();
			}

			catch(SocketTimeoutException e)
			{
				continue;
			}

			catch(IOException e)
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
	public IPacket onPacketReceived(IPacket p)
	{
		return null;
	}
}
