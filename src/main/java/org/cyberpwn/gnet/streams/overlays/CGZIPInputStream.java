package org.cyberpwn.gnet.streams.overlays;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class CGZIPInputStream extends GZIPInputStream
{
	public CGZIPInputStream(InputStream in) throws IOException
	{
		super(in);
		this.inf = new Inflater(false);
	}
}
