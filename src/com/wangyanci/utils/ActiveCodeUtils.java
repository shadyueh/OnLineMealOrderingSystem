package com.wangyanci.utils;

import java.util.UUID;

public class ActiveCodeUtils {

	public static String getActiveCode() {

		return UUID.randomUUID().toString();
	}
}
