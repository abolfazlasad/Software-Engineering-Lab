/*
 * $Id: Test.java,v 1.1 2006/04/15 14:40:06 platform Exp $
 * Created on 2006-4-15
 */
package org.json.simple;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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

}
