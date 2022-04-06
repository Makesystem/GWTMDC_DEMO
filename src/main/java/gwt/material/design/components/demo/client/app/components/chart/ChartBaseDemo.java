package gwt.material.design.components.demo.client.app.components.chart;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Composite;

import gwt.material.design.components.client.constants.Flex;
import gwt.material.design.components.client.ui.MaterialRadioButton;
import gwt.material.design.components.client.ui.misc.chart.base.ChartValueFormatter;
import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.misc.chart.helper.ChartHelper;

public abstract class ChartBaseDemo extends Composite {

	protected final ChartValueFormatter valueFormatter = value -> {
		final BigDecimal bigDecimal = new BigDecimal(String.valueOf(value)).setScale(2, RoundingMode.FLOOR);
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

	// ////////////////////////////////////////////////////////////
	// Utils
	// ////////////////////////////////////////////////////////////

	protected void updateCharts(final MaterialChartBase<?, ?, ?> chart, final int numberOfSeries) {
		chart.setValue(randomSeries(numberOfSeries));
	}
	
	protected void updateCharts(final MaterialChartBase<?, ?, ?> chart, final int numberOfSeries, int values) {
		chart.setValue(randomSeries(numberOfSeries, values));
	}

	protected <V, L> MaterialChartSerie<V, L>[] randomSeries(int numOfSeries) {
		return randomSeries(true, numOfSeries, 1);
	}

	protected <V, L> MaterialChartSerie<V, L>[] randomSeries(int numOfSeries, int values) {
		return randomSeries(true, numOfSeries, values);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <V, L> MaterialChartSerie<V, L>[] randomSeries(final boolean withName, int numOfSeries, int values) {

		final MaterialChartSerie<V, L>[] series = new MaterialChartSerie[numOfSeries];

		for (int i = 0; i < series.length; i++) {

			final V value;
			final L label;

			if (values < 2) {
				value = (V) Double.valueOf(((Random.nextInt(90) + 10) * 100));
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

	protected MaterialRadioButton radioButton(final String name, final String text, final boolean selected, final Flex flex,
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
