package cn.lt.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.lt.annotation.DataSource;
import cn.lt.util.DBContextHolder;

@Component
@Aspect
@Order(1)//先于事务开启前执行
public class DataSourceAspect{ 

	//@Before("execution(* cn.lt.service.*.*(..)))")
	public void doBefore(JoinPoint joinPoint) { 
    	try{
            String methodName = joinPoint.getSignature().getName();
            System.out.println("方法名："+methodName);
            Class<?> targetClass = joinPoint.getTarget().getClass();
            for(Method method : targetClass.getMethods()){
                if(methodName.equals(method.getName())){
                    Class<?>[] args = method.getParameterTypes();
                    if(args.length == joinPoint.getArgs().length){
                    	//方法名和参数都相同
                        boolean isExist = method.isAnnotationPresent(DataSource.class);
            			if(isExist){
            				DataSource ds = method.getAnnotation(DataSource.class);
            				DBContextHolder.setDBType(ds.value());
            			} else {
            				DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_MASTER);
            			}
                        
                        System.out.println("切换后数据源："+DBContextHolder.getDBType());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
      
    public void doAfterReturning(JoinPoint joinPoint) {  
    	System.out.println("清空数据源");
    	DBContextHolder.clearDBType();
    }  
      
}