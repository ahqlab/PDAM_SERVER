package net.octacomm.sample.utils;

import org.jboss.netty.buffer.ChannelBuffer;

public class Utill {
	
	public static boolean stringNullCheck(String str) {
		if(str != null) {
			return !str.isEmpty();
		}
		return false;
	}

}
