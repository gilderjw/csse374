package problem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectoryEventTest {

	private DirectoryEvent event;

	@Before
	public void setUp() throws Exception {
		this.event = new DirectoryEvent("remove", Paths.get("./input_output/test.txt"), null);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public final void testGetEventType() throws Exception {
		assertEquals("remove", this.event.getEventType());
	}

	@Test
	public final void testGetFile(){
		assertEquals(Paths.get("./input_output/test.txt"), this.event.getFile());
	}

	@Test
	public final void testGetSource(){
		assertEquals(null, this.event.getSource());
	}

}
