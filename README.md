# What
release-builder-web depended on release-builder.provide a service to you to initialize your moudules.


#How
release-builder-web base on actframework,a easy use mvc framework,see [https://github.com/actframework/actframework](https://github.com/actframework/actframework "actframework").

#About ModuleDataSource
release-builder-web supported build module from static json file or a file folder contain all your module files.

from file:

    List<LocalStorageModule> modules = ModuleDataSource.fromFile("modules.json");
> modules.json example see: [https://github.com/magixsource/release-builder-web/blob/master/src/main/resources/modules.json](https://github.com/magixsource/release-builder-web/blob/master/src/main/resources/modules.json "modules.json")

from file folder:

	List<LocalStorageModule> modules = ModuleDataSource.fromRoot(new File(
				"E:\\JS控件\\dist"));
> root example see: [https://github.com/magixsource/frontend](https://github.com/magixsource/frontend "frontend")

#Shortcut
![release-builder-web-shortcut.png](https://github.com/magixsource/release-builder-web/blob/master/release-builder-web-shortcut.png)