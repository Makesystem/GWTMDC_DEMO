package gwt.material.design.components.demo.client.app.components.select;

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

import gwt.material.design.components.client.constants.SelectMenuType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class SelectDemo extends Composite implements DemoPanel<MaterialSelect<String>> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, SelectDemo> {
	}

	@UiField
	MaterialSelect<String> to_do_tests_element;
	@UiField
	MaterialSelect<SelectMenuType> type_select;

	public SelectDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		for (SelectMenuType type : SelectMenuType.values()) {
			final MaterialSelect.Option<SelectMenuType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);
	}

	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<SelectMenuType> event) {
		to_do_tests_element.setType(event.getValue());
	}

	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@Override
	public Set<MaterialSelect<String>> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
