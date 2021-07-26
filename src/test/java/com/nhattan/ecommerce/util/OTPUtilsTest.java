package com.nhattan.ecommerce.util;

import com.nhattan.ecommerce.util.OTPUtils;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

import static org.junit.Assert.assertEquals;

class OTPUtilsTest {

	@Test
	void testMakeOTP() {
		int assertLength = 8;
		int length = OTPUtils.makeOTP().length();
		assertEquals(assertLength, length);
	}
}
