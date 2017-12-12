package org.cyberpwn.gnet.streams.constructors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import org.cyberpwn.gnet.streams.StreamConstructor;

public class CipherStreamConstructor implements StreamConstructor<CipherInputStream, CipherOutputStream>
{
	private final Cipher cipher;

	public CipherStreamConstructor(Cipher cipher)
	{
		this.cipher = cipher;
	}

	@Override
	public CipherInputStream constructInput(InputStream base) throws IOException
	{
		return new CipherInputStream(base, cipher);
	}

	@Override
	public CipherOutputStream constructOutput(OutputStream base) throws IOException
	{
		return new CipherOutputStream(base, cipher);
	}

}
