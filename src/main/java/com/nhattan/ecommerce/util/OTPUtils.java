package com.nhattan.ecommerce.util;

public class OTPUtils {
	private OTPUtils() {
	}

	public static String makeOTP() {
		int min = 10000000;
		int max = 99999999;
		return (int) (Math.random() * (max - min + 1) + min) + "";
	}
}
