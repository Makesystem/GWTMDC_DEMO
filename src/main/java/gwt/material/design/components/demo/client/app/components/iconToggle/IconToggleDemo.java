package gwt.material.design.components.demo.client.app.components.iconToggle;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialIconToggle;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class IconToggleDemo extends Composite implements DemoPanel<MaterialIconToggle>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, IconToggleDemo> {
	}

	@UiField
	MaterialIconToggle to_do_tests_element;	

	public IconToggleDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@Override
	public Set<MaterialIconToggle> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
