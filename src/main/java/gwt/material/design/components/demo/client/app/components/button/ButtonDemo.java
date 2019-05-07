package gwt.material.design.components.demo.client.app.components.button;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialButton;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class ButtonDemo extends Composite implements DemoPanel<MaterialButton> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ButtonDemo> {
	}

	@UiField
	MaterialButton to_do_tests_element;	
	@UiField
	MaterialSelect<ButtonType> type_select;

	public ButtonDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
	
		for (ButtonType type : ButtonType.values()) {
			final MaterialSelect.Option<ButtonType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);
	}
	
	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<ButtonType> event) {
		to_do_tests_element.setType(event.getValue());
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}
	
	@UiHandler("dense_check")
	void onSelectDense(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setDense(event.getValue());
	}

	@Override
	public Set<MaterialButton> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
