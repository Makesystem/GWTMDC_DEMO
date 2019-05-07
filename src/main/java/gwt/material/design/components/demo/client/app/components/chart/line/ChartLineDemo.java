package gwt.material.design.components.demo.client.app.components.chart.line;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.ChartAxisLabelPosition;
import gwt.material.design.components.client.constants.Flex;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialLineChart;
import gwt.material.design.components.demo.client.app.base.DemoPanel;
import gwt.material.design.components.demo.client.app.components.chart.ChartBaseDemo;

public class ChartLineDemo extends ChartBaseDemo implements DemoPanel<MaterialLineChart> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ChartLineDemo> {
	}

	@UiField
	MaterialLineChart to_do_tests_element;
	@UiField
	MaterialContent line_chart__axis_x_position__content;
	@UiField
	MaterialContent line_chart__axis_y_position__content;

	public ChartLineDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {

		to_do_tests_element.setValueFormatter(valueFormatter);
		updateCharts(to_do_tests_element, 4, 10);

		super.onLoad();

		line_chart__axis_x_position__content.clear();
		line_chart__axis_y_position__content.clear();
		for (ChartAxisLabelPosition position : ChartAxisLabelPosition.values()) {
			line_chart__axis_x_position__content.add(radioButton("line_chart__axis_x_label_position",
					position.toString(), position.equals(ChartAxisLabelPosition.END), Flex.NONE,
					event -> to_do_tests_element.setAxisXPosition(position)));
			line_chart__axis_y_position__content.add(radioButton("line_chart__axis_y_label_position",
					position.toString(), position.equals(ChartAxisLabelPosition.START), Flex.NONE,
					event -> to_do_tests_element.setAxisYPosition(position)));
		}
	}

	// ////////////////////////////////////////////////////////////
	// All charts
	// ////////////////////////////////////////////////////////////

	@UiHandler("charts__number_of_series__slider")
	void onChangeNumberOfSeries(final ValueChangeEvent<Double> event) {
		updateCharts(to_do_tests_element, event.getValue().intValue(), 10);
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
	// Line chart
	// ////////////////////////////////////////////////////////////

	@UiHandler("line_chart__full_width__checkbox")
	void onChangeLineChartFullWidth(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setFullWidth(event.getValue());
	}

	@UiHandler("line_chart__line_smooth__checkbox")
	void onChangeLineChartLineSmooth(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setLineSmooth(event.getValue());
	}

	@UiHandler("line_chart__enable_zoom__checkbox")
	void onChangeLineChartEnableZoom(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnableZoom(event.getValue());
	}

	@UiHandler("line_chart__show_line__checkbox")
	void onChangeLineChartShowLine(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowLine(event.getValue());
	}

	@UiHandler("line_chart__show_point__checkbox")
	void onChangeLineChartShowPoint(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowPoint(event.getValue());
	}

	@UiHandler("line_chart__show_point_label__checkbox")
	void onChangeLineChartShowPointLabel(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowPointLabel(event.getValue());
	}

	@UiHandler("line_chart__show_area__checkbox")
	void onChangeLineChartShowArea(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowArea(event.getValue());
	}

	@UiHandler("line_chart__show_grid_background__checkbox")
	void onChangeLineChartShowGridBackground(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setShowGridBackground(event.getValue());
	}

	@UiHandler("line_chart__padding_top__slider")
	void onChangeLineChartPaddingTop(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setChartPaddingTop(event.getValue().intValue());
	}

	@UiHandler("line_chart__padding_right__slider")
	void onChangeLineChartPaddingRight(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setChartPaddingRight(event.getValue().intValue());
	}

	@UiHandler("line_chart__padding_bottom__slider")
	void onChangeLineChartPaddingBottom(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setChartPaddingBottom(event.getValue().intValue());
	}

	@UiHandler("line_chart__padding_left__slider")
	void onChangeLineChartPaddingLeft(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setChartPaddingLeft(event.getValue().intValue());
	}

	@UiHandler("line_chart__axis_x_grid__checkbox")
	void onChangeLineChartShowGridAxisX(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAxisXShowGrid(event.getValue());
	}

	@UiHandler("line_chart__axis_x_label__checkbox")
	void onChangeLineChartShowLabelAxisX(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAxisXShowLabel(event.getValue());
	}

	@UiHandler("line_chart__axis_y__scale_min_space__slider")
	void onChangeLineChartScaleMinSpace(final ValueChangeEvent<Double> event) {
		to_do_tests_element.setAxisYScaleMinSpace(event.getValue().intValue());
	}

	@UiHandler("line_chart__axis_y_grid__checkbox")
	void onChangeLineChartShowGridAxisY(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAxisYShowGrid(event.getValue());
	}

	@UiHandler("line_chart__axis_y_label__checkbox")
	void onChangeLineChartShowLabelAxisY(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAxisYShowLabel(event.getValue());
	}

	@UiHandler("line_chart__axis_y_only_integer__checkbox")
	void onChangeLineChartOnlyIntegerAxisY(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAxisYOnlyInteger(event.getValue());
	}

	@UiHandler("line_chart__high__textfield")
	void onChangeLineChartHigh(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			to_do_tests_element.setHigh(null);
		} else {
			to_do_tests_element.setHigh(Double.valueOf(event.getValue()));
		}
	}

	@UiHandler("line_chart__low__textfield")
	void onChangeLineChartLow(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			to_do_tests_element.setLow(null);
		} else {
			to_do_tests_element.setLow(Double.valueOf(event.getValue()));
		}
	}

	@Override
	public Set<MaterialLineChart> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}

}
