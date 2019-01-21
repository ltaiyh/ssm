package cn.lt.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.TemplateModelException;
import freemarker.template.TemplateTransformModel;

public class StringTransform implements TemplateTransformModel {

	@Override
	public Writer getWriter(Writer writer, Map map)
			throws TemplateModelException, IOException {
		return new StringWriter(writer);
	}

}
