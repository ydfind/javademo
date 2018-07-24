package com.yd.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtil {
	public static String getContent(String filepath) {
		StringBuilder sb = new StringBuilder();
		String result = sb.toString();
		// TODO Auto-generated method stub
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					new FileInputStream(filepath),"UTF-8"));
			String line;
			if((line = bfr.readLine()) != null) {
				if(!line.startsWith("#")) {
					sb.append(line);
				}	
			}
			while((line = bfr.readLine()) != null) {
				sb.append("\n");
				if(!line.startsWith("#")) {
					sb.append(line);
				}
			}
			bfr.close();
			result = sb.toString();
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
