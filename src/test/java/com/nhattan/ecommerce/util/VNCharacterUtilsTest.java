package com.nhattan.ecommerce.util;

import com.nhattan.ecommerce.util.VNCharacterUtils;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

import static org.junit.Assert.assertEquals;

class VNCharacterUtilsTest {
	
	@Test
	void testRemoveAccentString() {
		String give = "đi nhậu hông bạn";
		String asser = "di nhau hong ban";
		String s = VNCharacterUtils.removeAccent(give);
		assertEquals(s, asser);
	}

}
