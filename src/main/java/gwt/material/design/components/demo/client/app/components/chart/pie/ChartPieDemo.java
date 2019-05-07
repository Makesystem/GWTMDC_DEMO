package gwt.material.design.components.demo.client.app.components.chart.pie;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.ChartLabelDirection;
import gwt.material.design.components.client.constants.ChartLabelPosition;
import gwt.material.design.components.client.constants.Flex;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialPieChart;
import gwt.material.design.components.client.ui.MaterialSlider;
import gwt.material.design.components.demo.client.app.base.DemoPanel;
import gwt.material.design.components.demo.client.app.components.chart.ChartBaseDemo;

public class ChartPieDemo extends ChartBaseDemo implements DemoPanel<MaterialPieChart> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ChartPieDemo> {
	}

	@UiField
	MaterialSlider charts__number_of_series__slider;
	
	@UiField
	MaterialPieChart to_do_tests_element;

	@UiField
	MaterialContent pie_chart__type__content;
	@UiField
	MaterialContent pie_chart__label_position__content;
	@UiField
	MaterialContent pie_chart__label_direction__content;

	public ChartPieDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {

		to_do_tests_element.setValueFormatter(valueFormatter);
		updateCharts(to_do_tests_element, 4);

		super.onLoad();

		pie_chart__type__content.clear();
		for (PieChartType type : PieChartType.values()) {
			pie_chart__type__content.add(radioButton(PieChartType.class.getName(), type.toString(),
					type.equals(PieChartType.DONUT), Flex.ONE, event -> to_do_tests_element.setType(type)));
		}

		pie_chart__label_position__content.clear();
		for (ChartLabelPosition labelPosition : ChartLabelPosition.values()) {
			pie_chart__label_position__content.add(radioButton(ChartLabelPosition.class.getName(),
					labelPosition.toString(), labelPosition.equals(ChartLabelPosition.INSIDE), Flex.ONE,
					event -> to_do_tests_element.setLabelPosition(labelPosition)));
		}

		pie_chart__label_direction__content.clear();
		for (ChartLabelDirection labelDirection : ChartLabelDirection.values()) {
			pie_chart__label_direction__content.add(radioButton(ChartLabelDirection.class.getName(),
					labelDirection.toString(), labelDirection.equals(ChartLabelDirection.NEUTRAL), Flex.ONE,
					event -> to_do_tests_element.setLabelDirection(labelDirection)));
		}
	}

	// ////////////////////////////////////////////////////////////
	// All charts
	// ////////////////////////////////////////////////////////////
	@UiHandler("random_values__button")
	void onRandomValues(final ClickEvent event) {
		updateCharts(to_do_tests_element, charts__number_of_series__slider.getValue().intValue());
	}
	
	@UiHandler("charts__number_of_series__slider")
	void onChangeNumberOfSeries(final ValueChangeEvent<Double> event) {
		updateCharts(to_do_tests_element, event.getValue().intValue());
	}

	@UiHandler("charts__reverse_data__checkbox")
	void onChangeReverseData(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setReverseData(event.getValue());
	}

	@UiHandler("charts__show_tooltip__checkbox")
	void onChangeLineChartShowTooltip(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowTooltip(event.getValue());
	}

	@UiHandler("charts__animated__checkbox")
	void onChangeLineChartAnimated(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAnimated(event.getValue());
	}

	@UiHandler("charts__animation_duration__slider")
	void onChangeAnimationDuration(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setAnimationDuration(event.getValue().intValue());
	}

	// ////////////////////////////////////////////////////////////
	// Pie chart
	// ////////////////////////////////////////////////////////////

	@UiHandler("pie_chart__label_offset__slider")
	void onChangePieChartLabelOffset(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setLabelOffset(event.getValue().intValue());
	}

	@UiHandler("pie_chart__start_angle__slider")
	void onChangePieChartStartAngle(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setStartAngle(event.getValue().intValue());
	}

	@UiHandler("pie_chart__donut_width__slider")
	void onChangePieChartDonutWidth(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setDonutWidth(event.getValue().intValue() + "px");
	}

	@UiHandler("pie_chart__chart_padding__slider")
	void onChangePieChartChartPadding(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setChartPadding(event.getValue().intValue());
	}

	@UiHandler("pie_chart__show_label__checkbox")
	void onChangePieChartShowLabel(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowLabel(event.getValue());
	}

	@UiHandler("pie_chart__ignore_empty_values__checkbox")
	void onChangePieChartIgnoreEmptyValues(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setIgnoreEmptyValues(event.getValue());
	}

	@Override
	public Set<MaterialPieChart> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
