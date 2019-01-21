package cn.lt.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class SHA {
	
	private static String src = "消息摘要算法测试";
	
	public static void main(String[] args) {
		JDKSha1();
		
		bcSha1();
		
		ccSha1();
	}
	
	public static void JDKSha1(){
		try {
			MessageDigest md = MessageDigest.getInstance("sha");
			byte[] sha1 = md.digest(src.getBytes());
			
			System.out.println("JDK SHA1::"+Hex.encodeHexString(sha1));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static void bcSha1(){
		
	}
	
	public static void ccSha1(){
		String digest = DigestUtils.sha1Hex(src);
		System.out.println("cc SHA1::"+digest);
	}

}
