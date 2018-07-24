package com.yd.demo.fastjson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.JSONToken;
import com.yd.util.FileUtil;
import com.yd.util.StrUtil;

public class FastJsonDemo {
	public static void main(String[] args) {
//		Map<String, String> map = new HashMap();
//		map.put("a0", "b0");
//		map.put("a1", "b1");
//		map.put("a2", "b2");
//		map.put("a3", "b3");
//		System.out.println(map.toString());
		
//		testJsonParse();
		myOwnerJsonParse();
	}
	
	private static void myOwnerJsonParse() {
		String res = "";
//		String res = FileUtil.getContent("D:/source.json");
//	    res = "\"{\\\"respCd\\\":\\\"0000\\\",\\\"msg\\\":\\\"查询成功\\\",\\\"result\\\":[{\\\"data1\\\":\\\"value1\\\"}]}\"";
//		res = "{\"respCd\":\"0000\",\"msg\":\"查询成功\",\"result\":[{\"data1\":\"value1\"}]}";
	    res = "\"{\\\"respCd\\\":\\\"0000\\\",\\\"msg\\\":\\\"查询成功\\\",\\\"result\\\":[{\\\"data1\\\":\\\"value1\\\"}]}\"";
		System.out.println("接受到的是：" + res);
		
		FastJsonParse myjson = new FastJsonParse();
		res = myjson.scanString(res);
		
//		res = (String)JSON.parse(res);
		System.out.println("处理结果为：" + res);
	}
	
	public static void testJsonParse() {
		String res = "";
//		res = FileUtil.getContent("D:/source.json");
	    res = "\"{\\\"respCd\\\":\\\"0000\\\",\\\"msg\\\":\\\"查询成功\\\",\\\"result\\\":[{\\\"data1\\\":\\\"value1\\\"}]}\"";
//		res = "{\"respCd\":\"0000\",\"msg\":\"查询成功\",\"result\":[{\"data1\":\"value1\"}]}";
		System.out.println("接受到的是：" + res);
		res = (String)JSON.parse(res);
		System.out.println("处理结果为：" + res);
	}
	
	public static void testStrSame() {
		String res = FileUtil.getContent("D:/source.json");
		String resowner = "\"{\\\"respCd\\\":\\\"0000\\\",\\\"msg\\\":\\\"查询成功\\\",\\\"result\\\":[{\\\"data1\\\":\\\"value1\\\"}]}\"";
		System.out.println("接受到的是：" + res);
//		String res1 = (String)JSON.parse(res);
		System.out.println("接受到的是：" + resowner);
		if(StrUtil.equalStr(res, resowner)) {
			System.out.println("两个变量一样");
		}else {
			System.out.println("两个变量不一样");
		}	
	}
}
