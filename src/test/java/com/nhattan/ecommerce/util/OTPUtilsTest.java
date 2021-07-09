package com.nhattan.ecommerce.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class OTPUtilsTest {

	@Test
	void testMakeOTP() {
		int assertLength = 8;
		int length = OTPUtils.makeOTP().length();
		assertEquals(assertLength, length);
	}

}
