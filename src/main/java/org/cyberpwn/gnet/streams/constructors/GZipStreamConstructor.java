package org.cyberpwn.gnet.streams.constructors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.cyberpwn.gnet.streams.StreamConstructor;
import org.cyberpwn.gnet.streams.overlays.CGZIPInputStream;
import org.cyberpwn.gnet.streams.overlays.CGZIPOutputStream;

public class GZipStreamConstructor implements StreamConstructor<CGZIPInputStream, CGZIPOutputStream>
{
	private final int compressionLevel;

	public GZipStreamConstructor(int compressionLevel)
	{
		this.compressionLevel = compressionLevel;
	}

	public GZipStreamConstructor()
	{
		this(1);
	}

	@Override
	public CGZIPInputStream constructInput(InputStream base) throws IOException
	{
		return new CGZIPInputStream(base);
	}

	@Override
	public CGZIPOutputStream constructOutput(OutputStream base) throws IOException
	{
		return new CGZIPOutputStream(base, compressionLevel);
	}

}
