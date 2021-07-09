package com.nhattan.ecommerce.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class VNCharacterUtilsTest {
	
	@Test
	void testRemoveAccentString() {
		String give = "đi nhậu hông bạn";
		String asser = "di nhau hong ban";
		String s = VNCharacterUtils.removeAccent(give);
		assertEquals(s, asser);
	}

}
