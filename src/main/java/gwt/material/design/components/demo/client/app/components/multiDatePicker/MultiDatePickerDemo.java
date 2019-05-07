package gwt.material.design.components.demo.client.app.components.multiDatePicker;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.ui.MaterialMultiDatePicker;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class MultiDatePickerDemo extends Composite implements DemoPanel<MaterialWidget>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, MultiDatePickerDemo> {
	}

	@UiField
	MaterialMultiDatePicker width_actions_datepicker;
	@UiField
	MaterialMultiDatePicker width_day_tooltips_datepicker;
	@UiField
	MaterialMultiDatePicker width_min_max_datepicker;
	
	
	public MultiDatePickerDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		final MdcDate date = new MdcDate();
		width_min_max_datepicker.setMinValue(date.previous());
		width_min_max_datepicker.setMaxValue(date.next());
		
		width_day_tooltips_datepicker.addTooltips(date.previous(), "Yesterday", "Ontem");
		width_day_tooltips_datepicker.addTooltips(date, "Today", "Hoje");
		width_day_tooltips_datepicker.addTooltips(date.next(), "Tomorrow", "Amanhã");
	}

	@Override
	public Set<MaterialWidget> getWidgetForTests() {
		
		final Set<MaterialWidget> forTest = new LinkedHashSet<>();		
		forTest.add(width_day_tooltips_datepicker);
		
		return forTest;
	}
	
	@UiHandler("yesterday_chip")
	void onClickYesterday(final ClickEvent event) {
		
		final MdcDate date = new MdcDate();
		final Collection<MdcDate> range = Arrays.asList(date.previous().previous(), date);
		
		width_actions_datepicker.setSelection(range);
		width_actions_datepicker.goTo();
	}

	@UiHandler("today_chip")
	void onClickToday(final ClickEvent event) {
		
		final MdcDate date = new MdcDate();
		final Collection<MdcDate> range = Arrays.asList(date.previous(), date.next());
		
		width_actions_datepicker.setSelection(range);
		width_actions_datepicker.goTo();
	}
	
	@UiHandler("tomorrow_chip")
	void onClickTomorrow(final ClickEvent event) {
		
		final MdcDate date = new MdcDate();
		final Collection<MdcDate> range = Arrays.asList(date, date.next().next());
		
		width_actions_datepicker.setSelection(range);
		width_actions_datepicker.goTo();
		//width_actions_datepicker.showYears();
	}
}
