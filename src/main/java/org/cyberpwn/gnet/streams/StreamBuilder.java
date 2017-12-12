package org.cyberpwn.gnet.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;

import org.cyberpwn.glang.GList;
import org.cyberpwn.gnet.streams.constructors.BufferedStreamConstructor;
import org.cyberpwn.gnet.streams.constructors.CipherStreamConstructor;
import org.cyberpwn.gnet.streams.constructors.GZipStreamConstructor;

public class StreamBuilder
{
	public GList<StreamConstructor<?, ?>> constructors;

	public StreamBuilder()
	{
		constructors = new GList<StreamConstructor<?, ?>>();
	}

	public InputStream constructInput(InputStream base) throws IOException
	{
		InputStream m = base;

		for(StreamConstructor<?, ?> i : constructors)
		{
			m = i.constructInput(m);
		}

		return m;
	}

	public OutputStream constructOutput(OutputStream base) throws IOException
	{
		OutputStream m = base;

		for(StreamConstructor<?, ?> i : constructors)
		{
			m = i.constructOutput(m);
		}

		return m;
	}

	public void bind(StreamConstructor<?, ?> constructor)
	{
		constructors.add(constructor);
	}

	public void bindGZIP(int compressionLevel)
	{
		bind(new GZipStreamConstructor(compressionLevel));
	}

	public void bindGZIP()
	{
		bind(new GZipStreamConstructor());
	}

	public void bindCipher(Cipher cipher)
	{
		bind(new CipherStreamConstructor(cipher));
	}

	public void bindBuffer(int bufferSize)
	{
		bind(new BufferedStreamConstructor(bufferSize));
	}

	public void bindBuffer()
	{
		bind(new BufferedStreamConstructor());
	}
}
