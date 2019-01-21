package cn.lt.digest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HMAC {
	
	private static String src = "消息摘要算法测试";
	
	public static void main(String[] args) {
		jdkHmacMd5();
	}

	public static void jdkHmacMd5(){
		try {
			//初始化KeyGenerator
			KeyGenerator kg = KeyGenerator.getInstance("hmacmd5");
			//产生密钥
			SecretKey sk = kg.generateKey();
			//获得密钥
			byte[] key = sk.getEncoded();
			//还原密钥
			SecretKey restoresk = new SecretKeySpec(key, "hmacmd5");
			//实例化MAC
			Mac mac = Mac.getInstance(restoresk.getAlgorithm());
			//初始化MAC
			mac.init(restoresk);
			//执行摘要
			byte[] digest = mac.doFinal(src.getBytes());
			
			System.out.println("HMAC MD5::"+Hex.encodeHexString(digest));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
