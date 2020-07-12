package com.warven22.sodapop.utils;

public class TimeUtil {
	private static int TICKS_PER_SECOND = 20;
	
	public static int getTicksFromSeconds(float seconds) {
		return (int) (TICKS_PER_SECOND * seconds);
	}
}