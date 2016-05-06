package gl.linpeng;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import act.boot.app.RunApp;

/**
 * 模块定制服务的入口
 * 
 * @author linpeng
 *
 */
public class App {

	/**
	 * 这里应该构建一个页面，给到所有的MODULES，让用户定义勾选
	 */
	@GetAction
	public void index() {
	}

	/**
	 * 这里接收到用户定制的MODULE数据，并调用buildService，响应结果给到前端
	 */
	@PostAction
	public void build() {

	}

	public static void main(String[] args) throws Exception {
		RunApp.start(App.class);
	}
}
