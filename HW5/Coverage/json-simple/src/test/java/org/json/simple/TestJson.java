/*
 * $Id: Test.java,v 1.1 2006/04/15 14:40:06 platform Exp $
 * Created on 2006-4-15
 */
package org.json.simple;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.json.simple.parser.ParseException;

public class TestJson {

	@Test
	public void testDecode() throws Exception{
		System.out.println("=======decode=======");

		String s="[0,10]";
		Object obj=JSONValue.parse(s);
		JSONArray array=(JSONArray)obj;
		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println();
		assertEquals("10",array.get(1).toString());
	}
	
	@Test
	public void testJSONArrayCollection() {
		final ArrayList<String> testList = new ArrayList<String>();
		testList.add("First item");
		testList.add("Second item");
		final JSONArray jsonArray = new JSONArray(testList);
		
		assertEquals("[\"First item\",\"Second item\"]", jsonArray.toJSONString());
	}

	@Test
	public void testDefaultJSONValueStringForObjAndList() {
		Map defaultValuesMap = new HashMap();
		defaultValuesMap.put("object", new HashMap<>());
		defaultValuesMap.put("list", new ArrayList<>());

		Map obj = new HashMap();
		obj.put("defaultValues", defaultValuesMap);
		String actual = JSONValue.toJSONString(obj);
		String expected = "{\"defaultValues\":" +
				"{" +
				"\"list\":[]," +
				"\"object\":{}" +
				"}" +
				"}";
		assertEquals(expected, actual);
	}

	@Test
	public void testJSONObjectCreation() {
		// Arrange
		JSONObject j = new JSONObject();
		// Assert
		assertEquals("{}", j.toString());
	}

	@Test
	public void testJSONObjectCreationWithMap() {
		// Arrange
		Map m = new HashMap<>();
		m.put("myKey", "myValue");
		// Act
		JSONObject j = new JSONObject(m);
		// Assert
		assertEquals("{\"myKey\":\"myValue\"}", j.toString());
	}

	@Test
	public void testDifferenceInJSONStringOfIntFloatAndChar() {
		// Arrange
		int[] ints = {101, 102, 103};
		char[] chars = {101, 102, 103};
		float[] floats = {101, 102, 103};
		// Act
		String intsStr = JSONArray.toJSONString(ints);
		String charsStr = JSONArray.toJSONString(chars);
		String floatsStr = JSONArray.toJSONString(floats);
		// Assert
		assertEquals(intsStr, "[101,102,103]");
		assertEquals(charsStr,
				String.format("[\"%c\",\"%c\",\"%c\"]", 101, 102, 103));
		assertEquals(floatsStr, "[101.0,102.0,103.0]");
	}

	@Test
	public void testParseFloatAndInt() {
		// Arrange
		String s = "{\"float\":[0.0],\"int\":[0]}";
		JSONObject obj= (JSONObject) JSONValue.parse(s);
		// Act
		JSONArray intArray = (JSONArray) obj.get("int");
		JSONArray floatArray = (JSONArray) obj.get("float");
		// Assert
		assertTrue(intArray.get(0) instanceof Long);
		assertTrue(floatArray.get(0) instanceof Double);
	}


	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void TestErrorInParseCloseBrace() throws ParseException {
		// Arrange, Assert
		String s = "{\"key\":\"value\"";
		thrown.expect(ParseException.class);
		thrown.expectMessage(
				String.format("Unexpected token END OF FILE at position %d.", s.length()));
		// Act
		JSONValue.parseWithException(s);
	}

	@Test()
	public void testMissingColon() {
		// Arrange
		String s = "{\"key\";\"value\"}";
		try {
			// Act
			JSONValue.parseWithException(s);
		} catch (ParseException e) {
			// Assert
			assertEquals(e.getPosition(), s.indexOf(';'));
			assertEquals(e.getUnexpectedObject().toString(), ";");
			assertEquals(e.getErrorType(), ParseException.ERROR_UNEXPECTED_CHAR);
		}
	}
}
