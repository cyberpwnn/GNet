package org.cyberpwn.gnet.streams.writable;

import org.cyberpwn.gnet.streams.ISS;
import org.cyberpwn.gnet.streams.OSS;

public interface Streamable
{
	public void toBytes(OSS out) throws Exception;

	public void fromBytes(ISS in) throws Exception;
}
