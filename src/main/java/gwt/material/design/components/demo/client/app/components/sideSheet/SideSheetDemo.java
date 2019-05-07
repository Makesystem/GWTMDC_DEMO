package gwt.material.design.components.demo.client.app.components.sideSheet;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialSideSheet;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.demo.client.app.DemoApp;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class SideSheetDemo extends Composite implements DemoPanel<MaterialSideSheet> {

	private static DemoAppUiBinder uiBinder = GWT.create(DemoAppUiBinder.class);

	interface DemoAppUiBinder extends UiBinder<Widget, SideSheetDemo> {
	}

	public SideSheetDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("back_act")
	void onBack(final ClickEvent event) {
		RootPanel.get().clear();
		RootPanel.get().add(new DemoApp());
	}

	@Override
	public Set<MaterialSideSheet> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialSideSheet.class, getWidget());
	}

}
