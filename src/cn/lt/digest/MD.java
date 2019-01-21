package cn.lt.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class MD {
	
	private static String src = "消息摘要算法测试";

	public static void main(String[] args) {
		JDKMd5();
		JDKMd2();
		
		bcMd4();
		bcMd5();
		bcMd2();
		
		ccMd5();
		ccMd2();
	}
	
	/**
	 * JDK提供的支持
	 */
	public static void JDKMd5(){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5byte = md.digest(src.getBytes());
			System.out.println("JDK MD5::"+Hex.encodeHexString(md5byte));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static void JDKMd2(){
		try {
			MessageDigest md = MessageDigest.getInstance("md2");
			byte[] md2byte = md.digest(src.getBytes());
			System.out.println("JDK MD2::"+Hex.encodeHexString(md2byte));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 提供对MD4的支持
	 */
	public static void bcMd4(){
		
	}
	
	public static void bcMd5(){
		
	}
	
	public static void bcMd2(){
		
	}
	
	/**
	 * 简化JDK操作
	 * 推荐使用
	 */
	public static void ccMd5(){
		String digest = DigestUtils.md5Hex(src);
		System.out.println("CC MD5::"+digest);
	}
	
	public static void ccMd2(){
		String digest = DigestUtils.md2Hex(src);
		System.out.println("CC MD2::"+digest);
	}
	
	
	/** 
     * 将给定的字节数组，转化为16进制数据 
     *  
     * @param resultBytes 
     * @return 
     */  
    private static String fromBytesToHex(byte[] resultBytes) {  
        StringBuilder builder = new StringBuilder();  
        for (int i = 0; i < resultBytes.length; i++) {  
        	System.out.println(Integer.toHexString(0xFF & resultBytes[i]));
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {  
                builder.append("0").append(  
                        Integer.toHexString(0xFF & resultBytes[i]));  
            } else {  
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));  
            }  
        }  
        return builder.toString();  
    }  
}
