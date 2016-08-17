package gl.linpeng;

import gl.linpeng.tools.builder.model.LocalStorageBuildModel;
import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.result.BuildResult;
import gl.linpeng.tools.builder.service.Builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.RenderBinary;
import org.osgl.mvc.result.Result;

import act.boot.app.RunApp;
import act.view.RenderTemplate;

/**
 * 模块定制服务的入口
 * 
 * @author linpeng
 *
 */
public class App {

	@Inject
	private Builder buildService;

	/**
	 * 这里应该构建一个页面，给到所有的MODULES，让用户定义勾选
	 * 
	 * @throws IOException
	 */
	@GetAction
	public Result index() throws IOException {
		// 从静态JSON文件构建模型,适合已构建过并相对比较固定的场景
		// List<LocalStorageModule> moduels = ModuleDataSource
		// .fromFile("modules.json");

		// 从本地文件自动构建模型(后面需要把绝对路径改为相对路径)
		List<LocalStorageModule> modules = ModuleDataSource.fromRoot(new File(
				"E:\\JS控件\\dist"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modules", modules);
		return new RenderTemplate(map);
	}

	/**
	 * 这里接收到用户定制的MODULE数据，并调用buildService，响应结果给到前端
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	@PostAction("/build")
	public Result takeStringArray(String[] checkbox) throws IOException {
		List<LocalStorageModule> buildModules = new ArrayList<LocalStorageModule>();
		// 需要把首次构建成功的模型缓存起来
		List<LocalStorageModule> modules = ModuleDataSource.fromRoot(new File(
				"E:\\JS控件\\dist"));

		Map<String, LocalStorageModule> map = modules.stream().collect(
				Collectors.toMap(LocalStorageModule::getId, (p) -> p));

		for (String modelName : checkbox) {
			LocalStorageModule module = map.get(modelName);
			if (null != module) {
				buildModules.add(module);
			}
		}
		LocalStorageBuildModel model = new LocalStorageBuildModel();
		model.setModules(buildModules);
		BuildResult<LocalStorageModule> result = buildService.build(model);

		return new RenderBinary(result.getFile());
	}

	public static void main(String[] args) throws Exception {
		RunApp.start(App.class);
	}
}
