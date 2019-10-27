package gwt.material.design.components.demo.client.app.theme;

import java.util.stream.IntStream;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
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
import gwt.material.design.components.client.utils.helper.JsHelper;
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

	ThemeProperty colorSetting;
	ThemeProperty onColorSetting;

	private final Theme theme;

	public ThemeBar() {
		initWidget(uiBinder.createAndBindUi(this));
		this.theme = new Theme((property, value) -> StyleHelper.setCssProperty(property.getCssName(), value));
		this.sub_bar.jQuery().hide();
		this.colors_bar.jQuery().hide();
	}
	
	@UiHandler("download__act")
	void onDownload(final ClickEvent event) {
		JsHelper.cssDownload("mdc_theme.css", theme.toString());
	}

	@UiHandler("palette")
	void onSelectColor(final ValueChangeEvent<Color> event) {

		final Color color = event.getValue();

		switch (colorSetting) {
		case MDC_THEME_PRIMARY:
		case MDC_THEME_SECONDARY:
		case MDC_THEME_SURFACE:
		case MDC_THEME_BACKGROUND:
		case MDC_THEME_SUCCESS:
		case MDC_THEME_WARNING:
		case MDC_THEME_ERROR:
			theme.setGroup(colorSetting, color);
			break;
		default:
			theme.set(colorSetting, color);
			theme.set(onColorSetting, color.onColor());
			break;
		}
	}

	@UiHandler("colors__act")
	void onColorsAct(final ClickEvent event) {
		this.sub_bar.jQuery().toggle();
		this.setColorsActs();
	}

	@UiHandler("close_colors_bars__act")
	void onCloseColorsAct(final ClickEvent event) {
		this.colors_bar.jQuery().hide();
	}

	void setColorsActs() {

		sub_bar.clear();

		final MaterialRadioButton primaryAct = getAction(ThemeProperty.MDC_THEME_ON_PRIMARY,
				ThemeProperty.MDC_THEME_PRIMARY, "Primary");
		primaryAct.setSelected(true);

		final MaterialRadioButton secondaryAct = getAction(ThemeProperty.MDC_THEME_ON_SECONDARY,
				ThemeProperty.MDC_THEME_SECONDARY, "Secondary");
		final MaterialRadioButton surfaceAct = getAction(ThemeProperty.MDC_THEME_ON_SURFACE,
				ThemeProperty.MDC_THEME_SURFACE, "Surface");
		final MaterialRadioButton backgroundAct = getAction(ThemeProperty.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND,
				ThemeProperty.MDC_THEME_BACKGROUND, "Background");

		final MaterialRadioButton successAct = getAction(ThemeProperty.MDC_THEME_ON_SUCCESS,
				ThemeProperty.MDC_THEME_SUCCESS, "Success");
		final MaterialRadioButton warningAct = getAction(ThemeProperty.MDC_THEME_ON_WARNING,
				ThemeProperty.MDC_THEME_WARNING, "Warning");
		final MaterialRadioButton errorAct = getAction(ThemeProperty.MDC_THEME_ON_ERROR, ThemeProperty.MDC_THEME_ERROR,
				"Error");

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
				.forEach(index -> sub_bar.add(getAction(chartist_labels[index], chartist_series[index],
						"Serie " + chartist_series[index].getCssName().replace("--mdc-chartist--series_", ""))));

	}

	MaterialRadioButton getAction(final ThemeProperty onColor, final ThemeProperty color, final String tooltip) {
		final MaterialRadioButton radio = getAction(var(onColor.getCssName()), var(color.getCssName()), tooltip);
		radio.addClickHandler(event -> {
			colorSetting = color;
			onColorSetting = onColor;
		});
		return radio;
	}

	MaterialRadioButton getAction(final String color, final String background, final String tooltip) {
		final MaterialRadioButton material_icon = new MaterialRadioButton();
		material_icon.setName("colors");
		material_icon.setCssProperty(CssMixin.MDC_RADIO_BUTTON__CHECKED_COLOR, color);
		material_icon.setCssProperty(CssMixin.MDC_RADIO_BUTTON__UNCHECKED_COLOR, color);
		material_icon.setCssProperty("background-color", background);
		material_icon.setTooltip(tooltip);
		material_icon.setTooltipPosition(TooltipPosition.LEFT);
		material_icon.addClickHandler(event -> colors_bar.jQuery().show());

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

}
