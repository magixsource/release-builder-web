package gl.linpeng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.resources.CssResource;
import gl.linpeng.tools.builder.resources.JavascriptResource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtilsTest {
	
	@Test
	public void fromJson() throws IOException{
		String text = org.apache.commons.io.IOUtils.toString(ClassLoader
				.getSystemResourceAsStream("modules.json"));
		List<LocalStorageModule> modules = JSON.parseArray(text,
				LocalStorageModule.class);
		
		System.out.println(modules.get(1).getDependencies().get(0));
	}

	@Test
	public void toJson() {
		String basePath = "E:\\林鹏\\f\\GitHub\\node_workspace\\frontend\\JS控件\\";
		List<LocalStorageModule> modules = new ArrayList<LocalStorageModule>();

		// base
		LocalStorageModule base = new LocalStorageModule("base", "base-module");
		CssResource baseCss = new CssResource(basePath
				+ "css\\sinobest.base.css");

		JavascriptResource baseJs = new JavascriptResource(basePath
				+ "js\\app\\sinobest.base.js");
		JavascriptResource baseValidate = new JavascriptResource(basePath
				+ "js\\app\\sinobest.validate.js");
		JavascriptResource baseError = new JavascriptResource(basePath
				+ "js\\app\\sinobest.error.js");
		JavascriptResource baseTools = new JavascriptResource(basePath
				+ "js\\app\\sinobest.tools.js");
		base.addResource(baseCss);
		base.addResource(baseJs);
		base.addResource(baseValidate);
		base.addResource(baseTools);
		base.addResource(baseError);

		modules.add(base);

		// text
		LocalStorageModule input = new LocalStorageModule("input",
				"input-module");
		input.addDependency(base);

		CssResource inputCss1 = new CssResource(basePath
				+ "css\\sinobest.text.css");
		CssResource inputCss2 = new CssResource(basePath
				+ "css\\sinobest.disabled.css");
		CssResource inputCss3 = new CssResource(basePath
				+ "css\\sinobest.readonly.css");

		JavascriptResource inputJs = new JavascriptResource(basePath
				+ "js\\app\\sinobest.text.js");

		input.addResource(inputCss1);
		input.addResource(inputCss2);
		input.addResource(inputCss3);
		input.addResource(inputJs);

		modules.add(input);
		// radio
		LocalStorageModule radio = new LocalStorageModule("radio",
				"radio-module");
		radio.addDependency(base);
		CssResource radioCss = new CssResource(basePath
				+ "css\\sinobest.radio.css");
		JavascriptResource radioJs = new JavascriptResource(basePath
				+ "js\\app\\sinobest.radio.js");
		radio.addResource(radioJs);
		radio.addResource(radioCss);
		modules.add(radio);
		// checkbox
		LocalStorageModule checkbox = new LocalStorageModule("checkbox",
				"checkbox-module");
		checkbox.addDependency(base);
		// CssResource checkboxCss = new CssResource(basePath
		// + "css\\sinobest.checkbox.css");
		JavascriptResource checkboxJs = new JavascriptResource(basePath
				+ "js\\app\\sinobest.checkbox.js");
		// checkbox.addResource(checkboxCss);
		checkbox.addResource(checkboxJs);
		modules.add(checkbox);

		// select
		LocalStorageModule select = new LocalStorageModule("select",
				"select-module");
		select.addDependency(base);
		CssResource selectCss = new CssResource(basePath
				+ "css\\sinobest.select.css");
		JavascriptResource selectJs = new JavascriptResource(basePath
				+ "js\\app\\sinobest.select.js");
		select.addResource(selectJs);
		select.addResource(selectCss);
		modules.add(select);
		
		// button
		// ...

		// Json simple print
		String x1 = JSON.toJSONString(modules, new PropertyFilter() {

			@Override
			public boolean apply(Object object, String name, Object value) {
//				if ("resources".equalsIgnoreCase(name)) {
//					System.out.println(object+" "+name+"  ["+value+"]");
//					return false;
//				}
				if ("content".equalsIgnoreCase(name)) {
					return false;
				}
				return true;
			}
		}, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(x1);
	}
}
