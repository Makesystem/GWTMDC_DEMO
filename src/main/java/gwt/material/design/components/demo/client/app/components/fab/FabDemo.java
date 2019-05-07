package gwt.material.design.components.demo.client.app.components.fab;

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

import gwt.material.design.components.client.constants.FabType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialFab;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class FabDemo extends Composite implements DemoPanel<MaterialFab> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, FabDemo> {
	}

	@UiField
	MaterialFab to_do_tests_element;
	@UiField
	MaterialSelect<FabType> type_select;

	public FabDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		for (FabType type : FabType.values()) {
			final MaterialSelect.Option<FabType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);
	}

	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<FabType> event) {
		to_do_tests_element.setType(event.getValue());
	}

	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@UiHandler("set_text_check")
	void onSelectSetText(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setText(event.getValue() ? "For test" : null);
	}

	@UiHandler("exited_check")
	void onSelectDense(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setExited(event.getValue());
	}

	@Override
	public Set<MaterialFab> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
