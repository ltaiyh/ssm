package cn.lt.test;

import org.apache.commons.codec.binary.Base64;

public class Test {

	public static void main(String[] args) {
		String str = "lt";
		String encodeStr = Base64.encodeBase64String(str.getBytes());
		System.out.println(encodeStr);
	}

}
