package gwt.material.design.components.demo.client.app.components.theme;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.Converter;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialSlider;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.utils.helper.ObjectHelper;
import gwt.material.design.components.client.utils.helper.StyleHelper;

public class ThemeDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ThemeDemo> {
	}

	@UiField
	MaterialLabel font_size_label;
	@UiField
	MaterialSlider font_size_slider;
	@UiField
	MaterialLabel zoom_label;
	@UiField
	MaterialSlider zoom_slider;
	@UiField
	MaterialLabel rounded_border_radius_label;
	@UiField
	MaterialSlider rounded_border_radius_slider;

	final Converter<MaterialSlider, Double, String> converterPercent = new Converter<MaterialSlider, Double, String>() {

		@Override
		public String undo(MaterialSlider source, Double value) {
			return value.intValue() + "%";
		}

		@Override
		public Double convert(MaterialSlider source, String value) {
			return Double.valueOf(value.replace("%", "").replace(".", "").replace(",", "."));
		}
	};
	
	final Converter<MaterialSlider, Double, String> converterPixel = new Converter<MaterialSlider, Double, String>() {

		@Override
		public String undo(MaterialSlider source, Double value) {
			return value.intValue() + "px";
		}

		@Override
		public Double convert(MaterialSlider source, String value) {
			return Double.valueOf(value.replace("px", "").replace(".", "").replace(",", "."));
		}
	};

	public ThemeDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		font_size_slider.setConverter(converterPercent);
		font_size_slider.setValue(getFontSize(), true);
		
		zoom_slider.setConverter(converterPercent);
		zoom_slider.setValue(getZoom(), true);
		
		rounded_border_radius_slider.setConverter(converterPixel);
		rounded_border_radius_slider.setValue(getRoundedBorderRadius(), true);
	}

	double getFontSize() {
		final String fontSize = StyleHelper.getCssProperty(CssMixin.MDC_ROOT_FONT_SIZE);
		final double value;		
		if(ObjectHelper.isNullOrEmpty(fontSize)) {
			value = 100d;
		} else if (fontSize.toLowerCase().contains("em")) {
			final String em  = JsHelper.onlyNumbersAndDots(fontSize);
			value = Double.valueOf(em) * 100d;
		} else if (fontSize.toLowerCase().contains("px")) {
			final String px  = JsHelper.onlyNumbersAndDots(fontSize);
			value = (Double.valueOf(px) * 100d) / 16d;
		} else if (fontSize.toLowerCase().contains("%")){
			final String percent  = JsHelper.onlyNumbersAndDots(fontSize);
			value = Double.valueOf(percent);
		} else {
			value = 100d;
		}
		return value;
	}
	
	double getZoom() {
		final String zoom = StyleHelper.getCssProperty(CssMixin.MDC_ZOOM);
		final double value;		
		if(ObjectHelper.isNullOrEmpty(zoom)) {
			value = 100d;
		} else if (zoom.toLowerCase().contains("%")){
			final String percent  = JsHelper.onlyNumbersAndDots(zoom);
			value = Double.valueOf(percent);
		} else {
			final String percent  = JsHelper.onlyNumbersAndDots(zoom);
			value = Double.valueOf(percent) * 100d;
		}
		return value;
	}
	
	double getRoundedBorderRadius() {
		final String borderRadius = StyleHelper.getCssProperty(CssMixin.MDC_ROUNDED_BORDER_RADIUS);
		final double value;		
		if(ObjectHelper.isNullOrEmpty(borderRadius)) {
			value = 4;
		} else if (borderRadius.toLowerCase().contains("px")){
			final String pixel  = JsHelper.onlyNumbersAndDots(borderRadius);
			value = Double.valueOf(pixel);
		} else {
			value = 4;
		}
		return value;
	}
	
	@UiHandler("font_size_slider")
	void onFontSize(final ValueChangeEvent<Double> event) {
		final double rem = (event.getValue() / 100d);
		final String fontSize = rem + "rem";
		StyleHelper.setCssProperty(CssMixin.MDC_ROOT_FONT_SIZE, fontSize);		
		font_size_label.setText("Font size (100% = 1rem = 16px): " + event.getValue().intValue() + "% = " + fontSize + " = " + (rem * 16d) + "px");		
	}
	
	@UiHandler("zoom_slider")
	void onZoom(final ValueChangeEvent<Double> event) {
		final String zoom = String.valueOf((event.getValue() / 100d));
		StyleHelper.setCssProperty(CssMixin.MDC_ZOOM, zoom);		
		zoom_label.setText("Zoom (100% = 1): " + event.getValue().intValue() + "% = " + zoom);		
	}
	
	@UiHandler("rounded_border_radius_slider")
	void onRoundedBorderRadius(final ValueChangeEvent<Double> event) {
		final String borderRadius = String.valueOf(event.getValue()) + "px";
		StyleHelper.setCssProperty(CssMixin.MDC_ROUNDED_BORDER_RADIUS, borderRadius);		
		rounded_border_radius_label.setText("Rounded border radius: " + event.getValue().intValue() + "px");		
	}
}
