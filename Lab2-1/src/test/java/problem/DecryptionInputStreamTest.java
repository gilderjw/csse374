package problem;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class DecryptionInputStreamTest {

	@Test
	public void test() throws IOException{
		String encrypt = "Gsrh rh z gvhg!";
		String plain = "This is a test!";
		String res = "";
		File testFile = new File("./input_output/DecryptedTest");
		
		OutputStream is= new FileOutputStream(testFile);
		
		EncryptOutputStream es = new EncryptOutputStream(is);
		es.setEncryption(new SubstitutionCipher());
		
		for(char c : plain.toCharArray()){
			es.write(c);
		}
		
		FileInputStream fis = new FileInputStream(testFile);
		
		System.out.println(encrypt);
//		System.out.println();
		for(char c : encrypt.toCharArray()){
			assertEquals(c, fis.read());
			System.out.print(c);
		}
	
	}
}
