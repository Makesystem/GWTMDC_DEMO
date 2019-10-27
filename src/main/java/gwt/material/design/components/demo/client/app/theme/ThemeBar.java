package gwt.material.design.components.demo.client.app.theme;

import java.util.stream.IntStream;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.ThemeProperty;
import gwt.material.design.components.client.constants.TooltipPosition;
import gwt.material.design.components.client.theme.Theme;
import gwt.material.design.components.client.ui.MaterialColorPalette;
import gwt.material.design.components.client.ui.MaterialRadioButton;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.StyleHelper;

public class ThemeBar extends Composite {

	private static ThemeBarUiBinder uiBinder = GWT.create(ThemeBarUiBinder.class);

	interface ThemeBarUiBinder extends UiBinder<Widget, ThemeBar> {
	}

	@UiField
	Div colors_bar;
	@UiField
	Div sub_bar;
	@UiField
	Div bar;

	@UiField
	MaterialColorPalette palette;

	ThemeProperty colorProperty;
	
	final Theme theme = new Theme((property, value) -> StyleHelper.setCssProperty(property.getCssName(), value));

	public ThemeBar() {
		initWidget(uiBinder.createAndBindUi(this));
		sub_bar.toggle();
		hide(colors_bar.getElement());
	}

	@UiHandler("colors__act")
	void onColorsAct(final ClickEvent event) {
		sub_bar.toggle();

		setColorsActs();
	}

	@UiHandler("close_colors_bars__act")
	void onCloseColorsAct(final ClickEvent event) {
		hide(colors_bar.getElement());
	}

	void setColorsActs() {

		sub_bar.clear();

		final MaterialRadioButton primaryAct = getAction(Color.MDC_THEME_ON_PRIMARY, Color.MDC_THEME_PRIMARY,
				"Primary");
		primaryAct.setSelected(true);

		final MaterialRadioButton secondaryAct = getAction(Color.MDC_THEME_ON_SECONDARY, Color.MDC_THEME_SECONDARY,
				"Secondary");
		final MaterialRadioButton surfaceAct = getAction(Color.MDC_THEME_ON_SURFACE, Color.MDC_THEME_SURFACE,
				"Surface");
		final MaterialRadioButton backgroundAct = getAction(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND,
				Color.MDC_THEME_BACKGROUND, "Background");

		final MaterialRadioButton successAct = getAction(Color.MDC_THEME_ON_SUCCESS, Color.MDC_THEME_SUCCESS,
				"Success");
		final MaterialRadioButton warningAct = getAction(Color.MDC_THEME_ON_WARNING, Color.MDC_THEME_WARNING,
				"Warning");
		final MaterialRadioButton errorAct = getAction(Color.MDC_THEME_ON_ERROR, Color.MDC_THEME_ERROR, "Error");

		sub_bar.add(primaryAct);
		sub_bar.add(secondaryAct);
		sub_bar.add(surfaceAct);
		sub_bar.add(backgroundAct);

		sub_bar.add(space());

		sub_bar.add(successAct);
		sub_bar.add(warningAct);
		sub_bar.add(errorAct);

		sub_bar.add(space());

		final ThemeProperty[] chartist_series = ThemeProperty.chartistSeries();
		final ThemeProperty[] chartist_labels = ThemeProperty.chartistLabels();
		IntStream.range(0, chartist_series.length)
				.forEach(index -> sub_bar.add(getAction(var(chartist_labels[index].getCssName()),
						var(chartist_series[index].getCssName()),
						"Serie " + chartist_series[index].getCssName().replace("--mdc-chartist--series_", ""))));

	}

	MaterialRadioButton getAction(final Color color, final Color background, final String tooltip) {
		return getAction(color.getCssName(), background.getCssName(), tooltip);
	}

	MaterialRadioButton getAction(final String color, final String background, final String tooltip) {
		final MaterialRadioButton material_icon = new MaterialRadioButton();
		material_icon.setName("colors");
		material_icon.setCssProperty(CssMixin.MDC_RADIO_BUTTON__CHECKED_COLOR, color);
		material_icon.setCssProperty(CssMixin.MDC_RADIO_BUTTON__UNCHECKED_COLOR, color);
		material_icon.setCssProperty("background-color", background);
		material_icon.setTooltip(tooltip);
		material_icon.setTooltipPosition(TooltipPosition.LEFT);

		material_icon.addClickHandler(event -> show(colors_bar.getElement()));

		return material_icon;
	}

	Div space() {
		final Div space = new Div();
		space.setWidth("100%");
		space.setHeight("26px");
		space.setMinHeight("26px");
		space.setMaxHeight("26px");
		return space;
	}

	String var(final String property) {
		return "var(" + property + ")";
	}

	public native void show(final Element element) /*-{
		$wnd.jQuery(element).show();
	}-*/;

	public native void hide(final Element element) /*-{
		$wnd.jQuery(element).hide();
	}-*/;
}
