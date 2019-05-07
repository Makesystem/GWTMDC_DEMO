package gwt.material.design.components.demo.client.app.components.fabProgress;

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

import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialFabProgress;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class FabProgressDemo extends Composite implements DemoPanel<MaterialFabProgress>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, FabProgressDemo> {
	}

	@UiField
	MaterialFabProgress to_do_tests_element;	

	public FabProgressDemo() {
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
	
	@UiHandler("reversed_check")
	void onSelectReversed(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setReverse(event.getValue());
	}
	
	@UiHandler("progress_slider")
	void onProgressSlider(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setProgress(event.getValue() / 100.d);
	}
	
	@UiHandler("buffer_slider")
	void onBufferSlider(final ValueChangeEvent<Double> event) {
		//to_do_tests_element.setBuffer(event.getValue() / 100.d);
	}

	@Override
	public Set<MaterialFabProgress> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}

}
