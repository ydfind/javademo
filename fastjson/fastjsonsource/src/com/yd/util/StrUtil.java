package com.yd.util;

public class StrUtil {
	public static boolean equalStr(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int len = len1 < len2 ? len1 : len2;
		if(str1 == null || str2 == null) {
			return false;
//			throw new R("变量为null");
		}
		
//        if (len1 == len2) {
            char v1[] = new char[len1];
            char v2[] = new char[len2];
            str1.getChars(0, len1, v1, 0);
            str2.getChars(0, len2, v2, 0);
            int i = 0;
            while (len-- != 0) {
            	char ch1 = v1[i];
            	char ch2 = v2[i];
                if (ch1 != ch2) {
                	System.out.println(i + ": " + ch1 + " <> " + ch2);
                    return false;
                }
                i++;
            }
            if(len1 == len2)
            	return true;
//        }
        
        return false;
		
	}

}
