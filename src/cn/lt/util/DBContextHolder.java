package cn.lt.util;

/**
 * 数据源上下文切换工具类
 * @author lt
 * @Date 2017年12月21日 下午4:50:35
 */
public class DBContextHolder {
	public static final String DATA_SOURCE_MASTER = "dataSourceMaster";  
    public static final String DATA_SOURCE_SLAVE = "dataSourceSlave";  
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static void setDBType(String dbType) {  
        contextHolder.set(dbType);  
    }  
      
    public static String getDBType() {  
        return contextHolder.get();  
    }  
      
    public static void clearDBType() {  
        contextHolder.remove();  
    }  

}
