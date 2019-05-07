package gwt.material.design.components.demo.client.app.base;

import java.util.Set;

import gwt.material.design.components.client.base.widget.MaterialUIObject;

public interface DemoPanel<W extends MaterialUIObject> {

	public Set<W> getWidgetForTests();
	
}
