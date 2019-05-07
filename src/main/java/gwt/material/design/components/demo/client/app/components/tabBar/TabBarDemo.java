package gwt.material.design.components.demo.client.app.components.tabBar;

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

import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.TabScrollerAlign;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialTabBar;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class TabBarDemo extends Composite implements DemoPanel<MaterialTabBar> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, TabBarDemo> {
	}

	@UiField
	MaterialTabBar to_do_tests_element;
	@UiField
	MaterialSelect<TabScrollerAlign> align_select;

	public TabBarDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();		
		for (TabScrollerAlign align : TabScrollerAlign.values()) {
			final MaterialSelect.Option<TabScrollerAlign> option = new MaterialSelect.Option<>();
			option.setText(align.toString());
			option.setValue(align);
			align_select.add(option);
		}
		align_select.setValue(TabScrollerAlign.NONE);
	}

	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@UiHandler("icon_check")
	void onSelectIcon(final SelectionEvent<Boolean> event) {
		to_do_tests_element.getTabs().forEach(tab -> tab.setIcon(event.getValue() ? IconType.FACE : null));
	}
	
	@UiHandler("stacked_check")
	void onSelectStacked(final SelectionEvent<Boolean> event) {
		to_do_tests_element.getTabs().forEach(tab -> tab.setStacked(event.getValue()));
	}

	@UiHandler("align_select")
	void onAlign(final ValueChangeEvent<TabScrollerAlign> event) {
		to_do_tests_element.setAlign(event.getValue());
	}

	@Override
	public Set<MaterialTabBar> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}

}
