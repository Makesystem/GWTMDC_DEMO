package gwt.material.design.components.demo.client.app.components.menu;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialMenu;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class MenuDemo extends Composite implements DemoPanel<MaterialMenu> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, MenuDemo> {
	}
	
	public MenuDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Set<MaterialMenu> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialMenu.class, getWidget());
	}
}
