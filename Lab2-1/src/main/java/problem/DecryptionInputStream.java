package problem;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptionInputStream extends FilterInputStream{

	IDecryption d;
	
	protected DecryptionInputStream(InputStream in) {
		super(in);
	}
	
	public void setDecryption(IDecryption d){
		this.d = d;
	}
	
	@Override
	public int read() throws IOException {
		int c = in.read();
		if(c == -1)
			return -1;
		
		return d.decrypt((char) c);
	}
}
