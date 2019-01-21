package cn.lt.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.CollectionUtils;

/**
 * 改写PropertyPlaceholderConfigurer，对数据库配置文件中的密码进行解密
 * @author lt
 * @Date 2017年12月21日 下午3:30:49
 */
public class MyPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	/**
	 * 加密的配置文件属性
	 */
	private String[] encryptProperty =  {"jdbc.password"};
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(isEncrypt(propertyName)){
			byte[] pwdByte = Base64.decodeBase64(propertyValue);
			try {
				propertyValue = new String(pwdByte, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return propertyValue;
	}
	
	/**
	 * 判断配置文件属性是否加密
	 * @param propertyName
	 * @return
	 */
	public boolean isEncrypt(String propertyName){
		List<String> encryptList = CollectionUtils.arrayToList(encryptProperty);
		if(encryptList.contains(propertyName)){
			return true;
		}
		
		return false;
	}

}
