package com.warven22.sodapop.utils;

public class TimeUtil {
	private static int TICKS_PER_SECOND = 20;
	
	public static int getTicksFromSeconds(int seconds) {
		return TICKS_PER_SECOND * seconds;
	}
}