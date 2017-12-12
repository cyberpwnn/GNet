package org.cyberpwn.gnet.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamConstructor<I extends InputStream, O extends OutputStream>
{
	public I constructInput(InputStream base) throws IOException;

	public O constructOutput(OutputStream base) throws IOException;
}
