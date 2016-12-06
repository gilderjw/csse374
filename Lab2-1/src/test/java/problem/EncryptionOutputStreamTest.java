package problem;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

public class EncryptionOutputStreamTest {

	@Test
	public void test() throws IOException{
		String ans = "This is a test!";
		String res = "";
		
		InputStream is= new FileInputStream(new File("./input_output/encryptedTest"));
		
		
		DecryptionInputStream ds = new DecryptionInputStream(is);
		ds.setDecryption(new SubstitutionCipher());
		
		int c = ds.read();
		while(c != -1){
			res += (char) c;
			 c = ds.read();
		}
		
		assertEquals(ans, res);
	}
}
