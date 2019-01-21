package cn.lt.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;

public class Test {
	
	public static void main(String[] args) throws Exception {
		String str = "EDU_CHANGE_JJJ";
		int p = str.indexOf("_");
		System.out.println(p+"::"+str.substring(p+1));
		
		/*//配置
		Configuration cfg = new Configuration();
		cfg.setSharedVariable("ding", new StringTransform());
		cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		
		//获取模板文件
		Template template = cfg.getTemplate("template/html.ftl");
		
		//生成模板
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "lt");
		map.put("date", new Date());
		
		File file = new File("C:\\Users\\lt\\Desktop\\spring_mybatis\\WebContent\\freemarker\\demo.html");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		
		template.process(map, out);
		out.flush();
		
		System.out.println("模板生成成功");*/
	}

}
