package gl.linpeng;

import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.ResourceType;
import gl.linpeng.tools.builder.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.filefilter.IOFileFilter;

import com.alibaba.fastjson.JSON;

/**
 * 模块信息数据源
 * 
 * @author linpeng
 *
 */
public class ModuleDataSource {

	public static List<LocalStorageModule> fromFile(String jsonFile)
			throws IOException {
		String text = org.apache.commons.io.IOUtils.toString(ClassLoader
				.getSystemResourceAsStream(jsonFile));
		return JSON.parseArray(text, LocalStorageModule.class);
	}

	public static List<LocalStorageModule> fromRoot(File root) {
		// 遍历目录
		List<LocalStorageResource> resources = FileUtils.getModules(root,
				new IOFileFilter() {
					@Override
					public boolean accept(File file, String name) {
						return false;
					}

					@Override
					public boolean accept(File file) {
						return file.isDirectory()
								|| file.getName().endsWith(".js")
								|| file.getName().endsWith(".css")
								|| file.getName().endsWith(".png")
								|| file.getName().endsWith(".gif")
								|| file.getName().endsWith(".cur");
					}
				});
		// 构建模块
		List<LocalStorageModule> modules = resources.stream()
				.filter(r -> r.getType() == ResourceType.JAVASCRIPT)
				.filter(r -> r.getSource().getParent().endsWith("app"))
				.map(new Function<LocalStorageResource, LocalStorageModule>() {

					@Override
					public LocalStorageModule apply(LocalStorageResource t) {
						String resourceName = t.getSource().getName();
						// 模块名称与本地的构建源有关
						String moduleId = resourceName.replaceAll("-", ".")
								.replace("sinobest.", "").replace(".js", "");
						String moduleName = moduleId + "-module";
						LocalStorageModule module = new LocalStorageModule(
								moduleId, moduleName);
						List<LocalStorageResource> moduleResource = resources
								.stream()
								.filter(r -> r.getSource().getName()
										.contains("." + moduleId + "."))
								.collect(Collectors.toList());
						module.setResources(moduleResource);
						String moduleType = "tools";
						if (moduleId.contains("text")
								|| moduleId.contains("input")
								|| moduleId.contains("radio")
								|| moduleId.contains("checkbox")
								|| moduleId.contains("password")
								|| moduleId.contentEquals("select")
								|| moduleId.contains("date")
								|| moduleId.contains("daterange")
								|| moduleId.contains("dialog")
								|| moduleId.contains("button")
								|| moduleId.contains("hidden")
								|| moduleId.contains("image")
								|| moduleId.contains("tabs")
								|| moduleId.contains("textarea")
								|| moduleId.contentEquals("text")) {
							moduleType = "basic";
						} else if (moduleId.contains("select")
								|| moduleId.contains("fullcalendar")
								|| moduleId.contains("listbox")
								|| moduleId.contains("loadingstatus")
								|| moduleId.contains("text")
								|| moduleId.contains("popuplayer")
								|| moduleId.contains("editor")
								|| moduleId.contains("tree")
								|| moduleId.contains("wizard")
								|| moduleId.contains("bubbling")) {
							moduleType = "ext";
						}
						module.setType(moduleType);
						return module;
					}
				}).collect(Collectors.toList());
		return modules;
	}
}
