package gwt.material.design.components.demo.client.app.components.checkBox;

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
import gwt.material.design.components.client.ui.MaterialCheckbox;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class CheckboxDemo extends Composite implements DemoPanel<MaterialCheckbox> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, CheckboxDemo> {
	}

	@UiField
	MaterialCheckbox to_do_tests_element;

	public CheckboxDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@UiHandler("indeterminate_check")
	void onSelectIndeterminate(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setIndeterminate(event.getValue());
	}

	@Override
	public Set<MaterialCheckbox> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
