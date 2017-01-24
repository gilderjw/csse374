package edu.rosehulman.csse374.wired.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultFactoryTest {
	DefaultFactory factory;
	@Before
	public void setUp() throws Exception {
		factory = new DefaultFactory();
	}

	@After
	public void tearDown() throws Exception {
		WiredConfiguration.reset();
		
	}

	@Test
	public void testCreateSpecialWithExclusion() throws Exception {
		WiredConfiguration.getInstance().addExclusion("java");
		String text = factory.create(String.class, null);
		assertNotNull(text);
	}

	@Test
	public void testCreateSpecialWithoutExclusion() throws Exception {
		String text = factory.create(String.class, null);
		assertNotNull(text);
	}
}
