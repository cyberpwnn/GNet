package org.cyberpwn.gnet.streams.constructors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.cyberpwn.gnet.streams.StreamConstructor;

public class BufferedStreamConstructor implements StreamConstructor<BufferedInputStream, BufferedOutputStream>
{
	private final int bufferSize;

	public BufferedStreamConstructor(int bufferSize)
	{
		this.bufferSize = bufferSize;
	}

	public BufferedStreamConstructor()
	{
		this(8192);
	}

	@Override
	public BufferedInputStream constructInput(InputStream base) throws IOException
	{
		return new BufferedInputStream(base, bufferSize);
	}

	@Override
	public BufferedOutputStream constructOutput(OutputStream base) throws IOException
	{
		return new BufferedOutputStream(base, bufferSize);
	}

}
