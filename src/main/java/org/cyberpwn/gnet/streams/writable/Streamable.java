package org.cyberpwn.gnet.streams.writable;

import java.io.IOException;

import org.cyberpwn.gnet.streams.ISS;
import org.cyberpwn.gnet.streams.OSS;

public interface Streamable
{
	public void toBytes(OSS out) throws IOException;

	public void fromBytes(ISS in) throws IOException;
}
