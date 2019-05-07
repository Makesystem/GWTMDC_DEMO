package gwt.material.design.components.demo.client.app.components.card;

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

import gwt.material.design.components.client.constants.AspectRatio;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialCard;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class CardDemo extends Composite implements DemoPanel<MaterialCard> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, CardDemo> {
	}

	@UiField
	MaterialCard to_do_tests_element;	
	@UiField
	MaterialCard to_do_tests_element_2;
	@UiField
	MaterialSelect<AspectRatio> aspect_ratio_select;

	public CardDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();	
		for (AspectRatio type : AspectRatio.values()) {
			final MaterialSelect.Option<AspectRatio> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			aspect_ratio_select.add(option);
		}
		aspect_ratio_select.setSelectedIndex(0, false);
	}
	
	@UiHandler("aspect_ratio_select")
	void onSelectAspectRatio(final ValueChangeEvent<AspectRatio> event) {
		to_do_tests_element.setAspectRatio(event.getValue());
		to_do_tests_element_2.setAspectRatio(event.getValue());
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
		to_do_tests_element_2.setEnabled(event.getValue());
	}
	
	@UiHandler("outline_check")
	void onSelectDense(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setOutline(event.getValue());
		to_do_tests_element_2.setOutline(event.getValue());
	}

	@Override
	public Set<MaterialCard> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element, to_do_tests_element_2).stream().collect(Collectors.toSet());
	}
}
