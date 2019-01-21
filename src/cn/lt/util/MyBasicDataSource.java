package cn.lt.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * 改写BasicDataSource，对数据库配置文件中的密码进行解密
 * @author lt
 * @Date 2017年12月21日 下午3:17:56
 */
public class MyBasicDataSource extends BasicDataSource {

	@Override
	public synchronized void setPassword(String password) {
		byte[] pwdByte = Base64.decodeBase64(password);
		
		try {
			password = new String(pwdByte, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		super.setPassword(password);
	}

}
