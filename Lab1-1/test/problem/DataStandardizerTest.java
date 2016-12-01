package problem;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataStandardizerTest {
	@Test
	public final void testParseGoogle() {
		// DO NOT change this test.
		// This code must be 100% backwards-compatible.
		DataStandardizer unifier = new DataStandardizer();
		unifier.parse("./input_output/io.gogl");
		
		String expected = "google";
		String actual = unifier.getCompany();
		assertEquals(expected, actual);

		StringBuffer buffer = new StringBuffer();
		buffer.append("geo1 : 100\n");
		buffer.append("geo2 : 450\n");
		buffer.append("geo3 : 90\n");
		buffer.append("geo4 : 750");

		expected = buffer.toString().trim();
		actual = unifier.getData().trim();
		assertEquals(expected, actual);
	}

	@Test
	public final void testParseMicrosoft() {
		// DO NOT change this test.
		// This code must be 100% backwards-compatible.
		DataStandardizer unifier = new DataStandardizer();
		unifier.parse("./input_output/io.ms");
		
		String expected = "microsoft";
		String actual = unifier.getCompany();
		assertEquals(expected, actual);

		StringBuffer buffer = new StringBuffer();
		buffer.append("ms1 : 100\n");
		buffer.append("ms2 : 450\n");
		buffer.append("ms3 : 90\n");
		buffer.append("ms4 : 750");

		expected = buffer.toString().trim();
		actual = unifier.getData().trim();
		assertEquals(expected, actual);
	}


	@Test
	public final void testParseAmazon() {
		DataStandardizer unifier = new DataStandardizer();

		// FIXME: add a method call here that tells the unifier how to parse a new file format


		unifier.parse("./input_output/io.amzn");
		
		String expected = "amazon";
		String actual = unifier.getCompany();
		assertEquals(expected, actual);

		StringBuffer buffer = new StringBuffer();
		buffer.append("aws1 : 100\n");
		buffer.append("aws2 : 450\n");
		buffer.append("aws3 : 90\n");
		buffer.append("aws4 : 750\n");
		buffer.append("aws5 : 900\n");
		buffer.append("aws6 : 200\n");

		expected = buffer.toString().trim();
		actual = unifier.getData().trim();
		// FIXME: this test fails because the code has POOR DESIGN.
		// Use GOOD DESIGN to fix this code.
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testParseGroupon() {
		// FIXME: for an A, add this test case.
	}

}
