package gl.linpeng;

import org.osgl.mvc.annotation.GetAction;

import act.boot.app.RunApp;

/**
 * Hello world!
 *
 */
public class App {

	@GetAction
	public void index() {
	}

	public static void main(String[] args) throws Exception {
		RunApp.start(App.class);
	}
}
