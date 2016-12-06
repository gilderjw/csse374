package problem;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream{

	IEncryption e;
	
	protected EncryptOutputStream(OutputStream in) {
		super(in);
	}
	
	public void setEncryption(IEncryption e){
		this.e = e;
	}
	
	@Override
	public void write(int b) throws IOException {
		out.write(e.encrypt((char) b));
	}
}