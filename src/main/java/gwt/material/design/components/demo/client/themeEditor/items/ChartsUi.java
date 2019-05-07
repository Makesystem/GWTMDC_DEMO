package gwt.material.design.components.demo.client.themeEditor.items;

import java.math.BigDecimal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.ChartAxisLabelPosition;
import gwt.material.design.components.client.constants.ChartLabelDirection;
import gwt.material.design.components.client.constants.ChartLabelPosition;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.Flex;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialBarChart;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialLineChart;
import gwt.material.design.components.client.ui.MaterialPieChart;
import gwt.material.design.components.client.ui.MaterialRadioButton;
import gwt.material.design.components.client.ui.misc.chart.base.ChartValueFormatter;
import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.misc.chart.helper.ChartHelper;

public class ChartsUi extends Composite {

	private static ChartsUiUiBinder uiBinder = GWT.create(ChartsUiUiBinder.class);

	interface ChartsUiUiBinder extends UiBinder<Widget, ChartsUi> {
	}

	// /////////////////////////////////////////////////////
	// ALL CHARTS
	// /////////////////////////////////////////////////////
	@UiField
	MaterialContent content;
	
	ChartValueFormatter valueFormatter = value -> {
		final BigDecimal bigDecimal = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
		final String[] values = bigDecimal.toString().split("\\.");

		final StringBuilder builder = new StringBuilder(values[0]);

		final int countDot = (builder.toString().length() / 3) - (builder.toString().length() % 3 == 0 ? 1 : 0);

		for (int i = 0, p = 1; i < countDot; i++, p++) {
			builder.insert(builder.toString().length() - ((3 * p) + i), ".");
		}

		builder.append(",");
		builder.append(values[1]);

		return builder.toString().replace("-.", "-");
	};

	// /////////////////////////////////////////////////////
	// PIE CHART
	// /////////////////////////////////////////////////////
	@UiField
	MaterialPieChart pie_chart;
	@UiField
	MaterialContent pie_chart__type__content;
	@UiField
	MaterialContent pie_chart__label_position__content;
	@UiField
	MaterialContent pie_chart__label_direction__content;

	// /////////////////////////////////////////////////////
	// LINE CHART
	// /////////////////////////////////////////////////////
	@UiField
	MaterialLineChart line_chart;
	@UiField
	MaterialContent line_chart__axis_x_position__content;
	@UiField
	MaterialContent line_chart__axis_y_position__content;

	// /////////////////////////////////////////////////////
	// BAR CHART
	// /////////////////////////////////////////////////////
	@UiField
	MaterialBarChart bar_chart;
	@UiField
	MaterialContent bar_chart__axis_x_position__content;
	@UiField
	MaterialContent bar_chart__axis_y_position__content;

	public ChartsUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {		
		
		pie_chart.setValueFormatter(valueFormatter);
		line_chart.setValueFormatter(valueFormatter);
		bar_chart.setValueFormatter(valueFormatter);
		updateCharts(4);
		
		super.onLoad();

		pie_chart__type__content.clear();
		for (PieChartType type : PieChartType.values()) {
			pie_chart__type__content.add(radioButton(PieChartType.class.getName(), type.toString(),
					type.equals(PieChartType.DONUT), Flex.ONE, event -> pie_chart.setType(type)));
		}

		pie_chart__label_position__content.clear();
		for (ChartLabelPosition labelPosition : ChartLabelPosition.values()) {
			pie_chart__label_position__content.add(radioButton(ChartLabelPosition.class.getName(),
					labelPosition.toString(), labelPosition.equals(ChartLabelPosition.INSIDE), Flex.ONE,
					event -> pie_chart.setLabelPosition(labelPosition)));
		}

		pie_chart__label_direction__content.clear();
		for (ChartLabelDirection labelDirection : ChartLabelDirection.values()) {
			pie_chart__label_direction__content.add(radioButton(ChartLabelDirection.class.getName(),
					labelDirection.toString(), labelDirection.equals(ChartLabelDirection.NEUTRAL), Flex.ONE,
					event -> pie_chart.setLabelDirection(labelDirection)));
		}

		line_chart__axis_x_position__content.clear();
		line_chart__axis_y_position__content.clear();
		bar_chart__axis_x_position__content.clear();
		bar_chart__axis_y_position__content.clear();
		for (ChartAxisLabelPosition position : ChartAxisLabelPosition.values()) {
			line_chart__axis_x_position__content.add(radioButton("line_chart__axis_x_label_position", position.toString(),
					position.equals(ChartAxisLabelPosition.END), Flex.NONE,
					event -> line_chart.setAxisXPosition(position)));
			line_chart__axis_y_position__content.add(radioButton("line_chart__axis_y_label_position", position.toString(),
					position.equals(ChartAxisLabelPosition.START), Flex.NONE,
					event -> line_chart.setAxisYPosition(position)));
			bar_chart__axis_x_position__content.add(radioButton("bar_chart__axis_x_label_position", position.toString(),
					position.equals(ChartAxisLabelPosition.END), Flex.NONE,
					event -> bar_chart.setAxisXPosition(position)));
			bar_chart__axis_y_position__content.add(radioButton("bar_chart__axis_y_label_position", position.toString(),
					position.equals(ChartAxisLabelPosition.START), Flex.NONE,
					event -> bar_chart.setAxisYPosition(position)));
		}
	}

	public void setVisible(final boolean visible) {
		if (visible) {
			content.setDisplay(Display.FLEX);
		} else {
			content.setDisplay(Display.NONE);
		}
	}

	// ////////////////////////////////////////////////////////////
	// All charts
	// ////////////////////////////////////////////////////////////

	@UiHandler("charts__number_of_series__slider")
	void onChangeNumberOfSeries(final ValueChangeEvent<Double> event) {
		updateCharts(event.getValue().intValue());
	}

	@UiHandler("charts__reverse_data__checkbox")
	void onChangeReverseData(final SelectionEvent<Boolean> event) {
		pie_chart.setReverseData(event.getValue());
		line_chart.setReverseData(event.getValue());
		bar_chart.setReverseData(event.getValue());
	}
	
	@UiHandler("charts__show_tooltip__checkbox")
	void onChangeLineChartShowTooltip(final SelectionEvent<Boolean> event) {
		pie_chart.setShowTooltip(event.getValue());
		line_chart.setShowTooltip(event.getValue());
		bar_chart.setShowTooltip(event.getValue());
	}

	@UiHandler("charts__animated__checkbox")
	void onChangeLineChartAnimated(final SelectionEvent<Boolean> event) {
		pie_chart.setAnimated(event.getValue());
		line_chart.setAnimated(event.getValue());
		bar_chart.setAnimated(event.getValue());
	}
	
	@UiHandler("charts__animation_duration__slider")
	void onChangeAnimationDuration(final ValueChangeEvent<Double> event) {
		pie_chart.setAnimationDuration(event.getValue().intValue());
		line_chart.setAnimationDuration(event.getValue().intValue());
		bar_chart.setAnimationDuration(event.getValue().intValue());
	}
	
	// ////////////////////////////////////////////////////////////
	// Pie chart
	// ////////////////////////////////////////////////////////////

	@UiHandler("pie_chart__label_offset__slider")
	void onChangePieChartLabelOffset(final ValueChangeEvent<Double> event) {

		GWT.log("aaaa");
		pie_chart.setLabelOffset(event.getValue().intValue());
	}

	@UiHandler("pie_chart__start_angle__slider")
	void onChangePieChartStartAngle(final ValueChangeEvent<Double> event) {
		pie_chart.setStartAngle(event.getValue().intValue());
	}

	@UiHandler("pie_chart__donut_width__slider")
	void onChangePieChartDonutWidth(final ValueChangeEvent<Double> event) {
		pie_chart.setDonutWidth(event.getValue().intValue() + "px");
	}

	@UiHandler("pie_chart__chart_padding__slider")
	void onChangePieChartChartPadding(final ValueChangeEvent<Double> event) {
		pie_chart.setChartPadding(event.getValue().intValue());
	}

	@UiHandler("pie_chart__show_label__checkbox")
	void onChangePieChartShowLabel(final SelectionEvent<Boolean> event) {
		pie_chart.setShowLabel(event.getValue());
	}
	
	@UiHandler("pie_chart__ignore_empty_values__checkbox")
	void onChangePieChartIgnoreEmptyValues(final SelectionEvent<Boolean> event) {
		pie_chart.setIgnoreEmptyValues(event.getValue());
	}

	// ////////////////////////////////////////////////////////////
	// Line chart
	// ////////////////////////////////////////////////////////////

	@UiHandler("line_chart__full_width__checkbox")
	void onChangeLineChartFullWidth(final SelectionEvent<Boolean> event) {
		line_chart.setFullWidth(event.getValue());
	}

	@UiHandler("line_chart__line_smooth__checkbox")
	void onChangeLineChartLineSmooth(final SelectionEvent<Boolean> event) {
		line_chart.setLineSmooth(event.getValue());
	}
	
	@UiHandler("line_chart__enable_zoom__checkbox")
	void onChangeLineChartEnableZoom(final SelectionEvent<Boolean> event) {
		line_chart.setEnableZoom(event.getValue());
	}

	@UiHandler("line_chart__show_line__checkbox")
	void onChangeLineChartShowLine(final SelectionEvent<Boolean> event) {
		line_chart.setShowLine(event.getValue());
	}

	@UiHandler("line_chart__show_point__checkbox")
	void onChangeLineChartShowPoint(final SelectionEvent<Boolean> event) {
		line_chart.setShowPoint(event.getValue());
	}

	@UiHandler("line_chart__show_point_label__checkbox")
	void onChangeLineChartShowPointLabel(final SelectionEvent<Boolean> event) {
		line_chart.setShowPointLabel(event.getValue());
	}

	@UiHandler("line_chart__show_area__checkbox")
	void onChangeLineChartShowArea(final SelectionEvent<Boolean> event) {
		line_chart.setShowArea(event.getValue());
	}
	
	@UiHandler("line_chart__show_grid_background__checkbox")
	void onChangeLineChartShowGridBackground(final SelectionEvent<Boolean> event) {
		line_chart.setShowGridBackground(event.getValue());
	}

	@UiHandler("line_chart__padding_top__slider")
	void onChangeLineChartPaddingTop(final ValueChangeEvent<Double> event) {
		line_chart.setChartPaddingTop(event.getValue().intValue());
	}

	@UiHandler("line_chart__padding_right__slider")
	void onChangeLineChartPaddingRight(final ValueChangeEvent<Double> event) {
		line_chart.setChartPaddingRight(event.getValue().intValue());
	}

	@UiHandler("line_chart__padding_bottom__slider")
	void onChangeLineChartPaddingBottom(final ValueChangeEvent<Double> event) {
		line_chart.setChartPaddingBottom(event.getValue().intValue());
	}

	@UiHandler("line_chart__padding_left__slider")
	void onChangeLineChartPaddingLeft(final ValueChangeEvent<Double> event) {
		line_chart.setChartPaddingLeft(event.getValue().intValue());
	}

	@UiHandler("line_chart__axis_x_grid__checkbox")
	void onChangeLineChartShowGridAxisX(final SelectionEvent<Boolean> event) {
		line_chart.setAxisXShowGrid(event.getValue());
	}

	@UiHandler("line_chart__axis_x_label__checkbox")
	void onChangeLineChartShowLabelAxisX(final SelectionEvent<Boolean> event) {
		line_chart.setAxisXShowLabel(event.getValue());
	}

	@UiHandler("line_chart__axis_y__scale_min_space__slider")
	void onChangeLineChartScaleMinSpace(final ValueChangeEvent<Double> event) {
		line_chart.setAxisYScaleMinSpace(event.getValue().intValue());
	}

	@UiHandler("line_chart__axis_y_grid__checkbox")
	void onChangeLineChartShowGridAxisY(final SelectionEvent<Boolean> event) {
		line_chart.setAxisYShowGrid(event.getValue());
	}

	@UiHandler("line_chart__axis_y_label__checkbox")
	void onChangeLineChartShowLabelAxisY(final SelectionEvent<Boolean> event) {
		line_chart.setAxisYShowLabel(event.getValue());
	}

	@UiHandler("line_chart__axis_y_only_integer__checkbox")
	void onChangeLineChartOnlyIntegerAxisY(final SelectionEvent<Boolean> event) {
		line_chart.setAxisYOnlyInteger(event.getValue());
	}

	@UiHandler("line_chart__high__textfield")
	void onChangeLineChartHigh(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			line_chart.setHigh(null);
		} else {
			line_chart.setHigh(Double.valueOf(event.getValue()));
		}
	}

	@UiHandler("line_chart__low__textfield")
	void onChangeLineChartLow(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			line_chart.setLow(null);
		} else {
			line_chart.setLow(Double.valueOf(event.getValue()));
		}
	}

	// ////////////////////////////////////////////////////////////
	// Bar chart
	// ////////////////////////////////////////////////////////////

	@UiHandler("bar_chart__distribute_series__checkbox")
	void onChangeBarChartDistributeSeries(final SelectionEvent<Boolean> event) {
		bar_chart.setDistributeSeries(event.getValue());
	}
	
	@UiHandler("bar_chart__horizontal_bars__checkbox")
	void onChangeBarChartHorizontalBars(final SelectionEvent<Boolean> event) {
		bar_chart.setHorizontalBars(event.getValue());
	}
	
	@UiHandler("bar_chart__stack_bars__checkbox")
	void onChangeBarChartStackBars(final SelectionEvent<Boolean> event) {
		bar_chart.setStackBars(event.getValue());
	}
	
	@UiHandler("bar_chart__series_bar_distance__slider")
	void onChangeBarChartSeriesBarDistance(final ValueChangeEvent<Double> event) {
		bar_chart.setSeriesBarDistance(event.getValue().intValue());
	}
	
	@UiHandler("bar_chart__show_bar_label__checkbox")
	void onChangeBarChartShowBarLabel(final SelectionEvent<Boolean> event) {
		bar_chart.setShowBarLabel(event.getValue());
	}
	
	@UiHandler("bar_chart__show_grid_background__checkbox")
	void onChangeBarChartShowGridBackground(final SelectionEvent<Boolean> event) {
		bar_chart.setShowGridBackground(event.getValue());
	}

	@UiHandler("bar_chart__padding_top__slider")
	void onChangeBarChartPaddingTop(final ValueChangeEvent<Double> event) {
		bar_chart.setChartPaddingTop(event.getValue().intValue());
	}

	@UiHandler("bar_chart__padding_right__slider")
	void onChangeBarChartPaddingRight(final ValueChangeEvent<Double> event) {
		bar_chart.setChartPaddingRight(event.getValue().intValue());
	}

	@UiHandler("bar_chart__padding_bottom__slider")
	void onChangeBarChartPaddingBottom(final ValueChangeEvent<Double> event) {
		bar_chart.setChartPaddingBottom(event.getValue().intValue());
	}

	@UiHandler("bar_chart__padding_left__slider")
	void onChangeBarChartPaddingLeft(final ValueChangeEvent<Double> event) {
		bar_chart.setChartPaddingLeft(event.getValue().intValue());
	}

	@UiHandler("bar_chart__axis_x_grid__checkbox")
	void onChangeBarChartShowGridAxisX(final SelectionEvent<Boolean> event) {
		bar_chart.setAxisXShowGrid(event.getValue());
	}

	@UiHandler("bar_chart__axis_x_label__checkbox")
	void onChangeBarChartShowLabelAxisX(final SelectionEvent<Boolean> event) {
		bar_chart.setAxisXShowLabel(event.getValue());
	}

	@UiHandler("bar_chart__axis_x__scale_min_space__slider")
	void onChangeBarChartScaleMinSpaceAxisX(final ValueChangeEvent<Double> event) {
		bar_chart.setAxisXScaleMinSpace(event.getValue().intValue());
	}
	
	@UiHandler("bar_chart__axis_y__scale_min_space__slider")
	void onChangeBarChartScaleMinSpaceAxisY(final ValueChangeEvent<Double> event) {
		bar_chart.setAxisYScaleMinSpace(event.getValue().intValue());
	}

	@UiHandler("bar_chart__axis_y_grid__checkbox")
	void onChangeBarChartShowGridAxisY(final SelectionEvent<Boolean> event) {
		bar_chart.setAxisYShowGrid(event.getValue());
	}

	@UiHandler("bar_chart__axis_y_label__checkbox")
	void onChangeBarChartShowLabelAxisY(final SelectionEvent<Boolean> event) {
		bar_chart.setAxisYShowLabel(event.getValue());
	}
	
	@UiHandler("bar_chart__axis_x_only_integer__checkbox")
	void onChangeBarChartOnlyIntegerAxisX(final SelectionEvent<Boolean> event) {
		bar_chart.setAxisXOnlyInteger(event.getValue());
	}
	
	@UiHandler("bar_chart__axis_y_only_integer__checkbox")
	void onChangeBarChartOnlyIntegerAxisY(final SelectionEvent<Boolean> event) {
		bar_chart.setAxisYOnlyInteger(event.getValue());
	}

	@UiHandler("bar_chart__high__textfield")
	void onChangeBarChartHigh(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			bar_chart.setHigh(null);
		} else {
			bar_chart.setHigh(Double.valueOf(event.getValue()));
		}
	}

	@UiHandler("bar_chart__low__textfield")
	void onChangeBarChartLow(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			bar_chart.setLow(null);
		} else {
			bar_chart.setLow(Double.valueOf(event.getValue()));
		}
	}
	
	@UiHandler("bar_chart__reference__textfield")
	void onChangeBarChartReference(final ValueChangeEvent<String> event) {
		if (event.getValue().isEmpty()) {
			bar_chart.setReferenceValue(0);
		} else {
			bar_chart.setReferenceValue(Integer.valueOf(event.getValue()));
		}
	}

	// ////////////////////////////////////////////////////////////
	// Utils
	// ////////////////////////////////////////////////////////////

	void updateCharts(final int numberOfSeries) {
		pie_chart.setValue(randomSeries(numberOfSeries));
		line_chart.setValue(randomSeries(numberOfSeries, 10));
		bar_chart.setValue(randomSeries(numberOfSeries, 4));
	}

	<V, L> MaterialChartSerie<V, L>[] randomSeries(int numOfSeries) {
		return randomSeries(true, numOfSeries, 1);
	}

	<V, L> MaterialChartSerie<V, L>[] randomSeries(int numOfSeries, int values) {
		return randomSeries(true, numOfSeries, values);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	<V, L> MaterialChartSerie<V, L>[] randomSeries(final boolean withName, int numOfSeries, int values) {

		final MaterialChartSerie<V, L>[] series = new MaterialChartSerie[numOfSeries];

		for (int i = 0; i < series.length; i++) {

			final V value;
			final L label;

			if (values < 2) {
				value = (V) new Double(((Random.nextInt(90) + 10) * 100));
				label = (L) ChartHelper.alphaNumerate(i);
			} else {
				final Double[] vars = new Double[values];
				final String[] labels = new String[values];
				for (int x = 0; x < values; x++) {
					vars[x] = Random.nextDouble();
					labels[x] = String.valueOf(x + 0.1d);
				}
				value = (V) vars;
				label = (L) labels;
			}

			final MaterialChartSerie<V, L> chartSerie = new MaterialChartSerie(value, label, ChartHelper.alphaNumerate(i), ChartHelper.alphaNumerate(i));
			series[i] = chartSerie;

		}

		return series;
	}

	MaterialRadioButton radioButton(final String name, final String text, final boolean selected, final Flex flex,
			final ClickHandler handler) {
		final MaterialRadioButton radioButton = new MaterialRadioButton();
		radioButton.setFlex(flex);
		radioButton.setName(name);
		radioButton.setText(text);
		radioButton.setSelected(selected);
		radioButton.addClickHandler(handler);
		return radioButton;
	}

}
