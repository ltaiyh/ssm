package cn.lt.freemarker;

import java.io.IOException;
import java.io.Writer;

public class StringWriter extends Writer {
	
	private Writer writer;
	
	public StringWriter(Writer writer){
		this.writer = writer;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		String str = new String(cbuf);
		System.out.println("自定义标签："+off+"::"+len+"::"+new String(cbuf));
		writer.write("我是自定义标签:"+str.substring(0,1).toUpperCase()+str.substring(1));
	}

	@Override
	public void flush() throws IOException {
		writer.flush();
	}

	@Override
	public void close() throws IOException {
		//writer.close();
	}

}
