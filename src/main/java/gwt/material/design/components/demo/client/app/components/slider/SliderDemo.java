package gwt.material.design.components.demo.client.app.components.slider;

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

import gwt.material.design.components.client.constants.SliderType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialSlider;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class SliderDemo extends Composite implements DemoPanel<MaterialSlider> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, SliderDemo> {
	}

	@UiField
	MaterialSlider to_do_tests_element;	
	@UiField
	MaterialSelect<SliderType> type_select;

	public SliderDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
		for (SliderType type : SliderType.values()) {
			final MaterialSelect.Option<SliderType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setValue(SliderType.DEFAULT);
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}
	
	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<SliderType> event) {
		to_do_tests_element.setType(event.getValue());
	}
	
	@Override
	public Set<MaterialSlider> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
