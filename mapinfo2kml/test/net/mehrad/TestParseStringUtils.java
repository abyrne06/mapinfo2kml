package net.mehrad;
import org.junit.Test;

import net.mehrad.mapinfo2kml.util.*;
import static org.junit.Assert.*;

public class TestParseStringUtils {

	/**
	 * Test method for {@link net.mehrad.mapinfo2kml.util.ParseStringUtils#getStringPart()}.
	 */
	@Test
	public void testParseStringUtils()
	{
		ParseStringUtils psu = new ParseStringUtils();
		assertEquals(psu.getStringPart("word1 word2", 0), "word1");
		
		// test for expected NoSuchElementException when index is out of range
		try
		{
			assertNull(psu.getStringPart("word1", 2));
			fail();
		}
		catch(java.util.NoSuchElementException e)
		{
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	/**
	 * Test method for {@link net.mehrad.mapinfo2kml.util.ParseStringUtils#startsWithIgnoreCase(String, String)
	 */
	@Test
	public void testStartsWithIgnoreCase()
	{
		ParseStringUtils psu = new ParseStringUtils();
		assertFalse(psu.startsWithIgnoreCase("test", "no"));
		assertTrue(psu.startsWithIgnoreCase("test something", "test"));
		assertTrue(psu.startsWithIgnoreCase("test something longer", "te"));
		assertFalse(psu.startsWithIgnoreCase("test something", ""));
		assertFalse(psu.startsWithIgnoreCase("te", "test"));
	}
}
