package com.skss.iframe.util.string;

public class StringUtil {
	public static String format(String str) {
		int fromIndex = str.indexOf("_");
		if (fromIndex != -1) {
			String firstStr = str.substring(0, fromIndex);
			str = str.substring(fromIndex + 1);
			return firstStr
					+ format(str.replaceFirst(str.substring(0, 1), str
							.substring(0, 1).toUpperCase()));
		}
		return str;
	}
}
