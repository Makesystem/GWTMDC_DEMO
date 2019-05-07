package gwt.material.design.components.demo.client.app.components.chart.bar;

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
import gwt.material.design.components.client.ui.MaterialBarChart;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.demo.client.app.base.DemoPanel;
import gwt.material.design.components.demo.client.app.components.chart.ChartBaseDemo;

public class ChartBarDemo extends ChartBaseDemo implements DemoPanel<MaterialBarChart>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ChartBarDemo> {
	}

	@UiField
	MaterialBarChart to_do_tests_element;
	@UiField
	MaterialContent bar_chart__axis_x_position__content;
	@UiField
	MaterialContent bar_chart__axis_y_position__content;

	public ChartBarDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {

		to_do_tests_element.setValueFormatter(valueFormatter);
		updateCharts(to_do_tests_element, 4, 4);

		super.onLoad();
		
		bar_chart__axis_x_position__content.clear();
		bar_chart__axis_y_position__content.clear();
		for (ChartAxisLabelPosition position : ChartAxisLabelPosition.values()) {
			bar_chart__axis_x_position__content.add(radioButton("bar_chart__axis_x_label_position", position.toString(),
					position.equals(ChartAxisLabelPosition.END), Flex.NONE,
					event -> to_do_tests_element.setAxisXPosition(position)));
			bar_chart__axis_y_position__content.add(radioButton("bar_chart__axis_y_label_position", position.toString(),
					position.equals(ChartAxisLabelPosition.START), Flex.NONE,
					event -> to_do_tests_element.setAxisYPosition(position)));
		}

	}

	// ////////////////////////////////////////////////////////////
	// All charts
	// ////////////////////////////////////////////////////////////

	@UiHandler("charts__number_of_series__slider")
	void onChangeNumberOfSeries(final ValueChangeEvent<Double> event) {
		updateCharts(to_do_tests_element, event.getValue().intValue(), 4);
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
		// Bar chart
		// ////////////////////////////////////////////////////////////

		@UiHandler("bar_chart__distribute_series__checkbox")
		void onChangeBarChartDistributeSeries(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setDistributeSeries(event.getValue());
		}
		
		@UiHandler("bar_chart__horizontal_bars__checkbox")
		void onChangeBarChartHorizontalBars(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setHorizontalBars(event.getValue());
		}
		
		@UiHandler("bar_chart__stack_bars__checkbox")
		void onChangeBarChartStackBars(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setStackBars(event.getValue());
		}
		
		@UiHandler("bar_chart__series_bar_distance__slider")
		void onChangeBarChartSeriesBarDistance(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setSeriesBarDistance(event.getValue().intValue());
		}
		
		@UiHandler("bar_chart__show_bar_label__checkbox")
		void onChangeBarChartShowBarLabel(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setShowBarLabel(event.getValue());
		}
		
		@UiHandler("bar_chart__show_grid_background__checkbox")
		void onChangeBarChartShowGridBackground(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setShowGridBackground(event.getValue());
		}

		@UiHandler("bar_chart__padding_top__slider")
		void onChangeBarChartPaddingTop(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setChartPaddingTop(event.getValue().intValue());
		}

		@UiHandler("bar_chart__padding_right__slider")
		void onChangeBarChartPaddingRight(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setChartPaddingRight(event.getValue().intValue());
		}

		@UiHandler("bar_chart__padding_bottom__slider")
		void onChangeBarChartPaddingBottom(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setChartPaddingBottom(event.getValue().intValue());
		}

		@UiHandler("bar_chart__padding_left__slider")
		void onChangeBarChartPaddingLeft(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setChartPaddingLeft(event.getValue().intValue());
		}

		@UiHandler("bar_chart__axis_x_grid__checkbox")
		void onChangeBarChartShowGridAxisX(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setAxisXShowGrid(event.getValue());
		}

		@UiHandler("bar_chart__axis_x_label__checkbox")
		void onChangeBarChartShowLabelAxisX(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setAxisXShowLabel(event.getValue());
		}

		@UiHandler("bar_chart__axis_x__scale_min_space__slider")
		void onChangeBarChartScaleMinSpaceAxisX(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setAxisXScaleMinSpace(event.getValue().intValue());
		}
		
		@UiHandler("bar_chart__axis_y__scale_min_space__slider")
		void onChangeBarChartScaleMinSpaceAxisY(final ValueChangeEvent<Double> event) {
			to_do_tests_element.setAxisYScaleMinSpace(event.getValue().intValue());
		}

		@UiHandler("bar_chart__axis_y_grid__checkbox")
		void onChangeBarChartShowGridAxisY(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setAxisYShowGrid(event.getValue());
		}

		@UiHandler("bar_chart__axis_y_label__checkbox")
		void onChangeBarChartShowLabelAxisY(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setAxisYShowLabel(event.getValue());
		}
		
		@UiHandler("bar_chart__axis_x_only_integer__checkbox")
		void onChangeBarChartOnlyIntegerAxisX(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setAxisXOnlyInteger(event.getValue());
		}
		
		@UiHandler("bar_chart__axis_y_only_integer__checkbox")
		void onChangeBarChartOnlyIntegerAxisY(final SelectionEvent<Boolean> event) {
			to_do_tests_element.setAxisYOnlyInteger(event.getValue());
		}

		@UiHandler("bar_chart__high__textfield")
		void onChangeBarChartHigh(final ValueChangeEvent<String> event) {
			if (event.getValue().isEmpty()) {
				to_do_tests_element.setHigh(null);
			} else {
				to_do_tests_element.setHigh(Double.valueOf(event.getValue()));
			}
		}

		@UiHandler("bar_chart__low__textfield")
		void onChangeBarChartLow(final ValueChangeEvent<String> event) {
			if (event.getValue().isEmpty()) {
				to_do_tests_element.setLow(null);
			} else {
				to_do_tests_element.setLow(Double.valueOf(event.getValue()));
			}
		}
		
		@UiHandler("bar_chart__reference__textfield")
		void onChangeBarChartReference(final ValueChangeEvent<String> event) {
			if (event.getValue().isEmpty()) {
				to_do_tests_element.setReferenceValue(0);
			} else {
				to_do_tests_element.setReferenceValue(Integer.valueOf(event.getValue()));
			}
		}

		@Override
		public Set<MaterialBarChart> getWidgetForTests() {
			return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
		}
}
