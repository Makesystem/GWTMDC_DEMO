package gwt.material.design.components.demo.client.app.components.colorChooser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.theme.MaterialTheme;
import gwt.material.design.components.client.theme.ThemeManager;
import gwt.material.design.components.client.ui.MaterialColorPalette;
import gwt.material.design.components.client.ui.MaterialDialog;
import gwt.material.design.components.client.ui.MaterialIconButton;

public class ColorChooser extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ColorChooser> {
	}

	enum ColorType {
		PRIMARY, TEXT_ON_PRIMARY, SECONDARY, TEXT_ON_SECONDARY, BACKGROUND, TEXT_ON_BACKGROUND, SURFACE, TEXT_ON_SURFACE
	};

	@UiField
	MaterialDialog dialog_color_palette;
	@UiField
	MaterialColorPalette color_palette;
	@UiField
	MaterialIconButton close_action;

	private Color primary__color;
	private Color primary__text_color;

	private Color secondary__color;
	private Color secondary__text_color;

	private Color background__color;
	private Color background__text_color;

	private Color surface__color;
	private Color surface__text_color;

	private ColorType setting;

	private MaterialTheme materialTheme = new MaterialTheme();

	public ColorChooser() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		RootPanel.get().add(dialog_color_palette);
	}

	public void download() {
		download("theme.css", materialTheme.getAsText());
	}
	
	public HandlerRegistration addCloseHandler(final ClickHandler handler) {
		return close_action.addClickHandler(handler);
	}

	native void download(String fileName, String text) /*-{

		var element = document.createElement('a');
		element.setAttribute('href', 'data:text/css;charset=utf-8,'
				+ encodeURIComponent(text));
		element.setAttribute('download', fileName);

		element.style.display = 'none';
		document.body.appendChild(element);

		element.click();

		document.body.removeChild(element);

	}-*/;

	void openDialog(final ColorType colorType, final Color color) {
		setting = colorType;
		color_palette.setValue(color);
		dialog_color_palette.setTitle(colorType.toString().replaceAll("_", " "));
		dialog_color_palette.open();
	}

	@UiHandler("primary__color")
	void chosePrimaryColor(final ClickEvent event) {
		openDialog(ColorType.PRIMARY, primary__color);
	}

	@UiHandler("primary__text_color")
	void chosePrimaryTextColorColor(final ClickEvent event) {
		openDialog(ColorType.TEXT_ON_PRIMARY, primary__text_color);
	}

	@UiHandler("secondary__color")
	void choseSecondaryColor(final ClickEvent event) {
		openDialog(ColorType.SECONDARY, secondary__color);
	}

	@UiHandler("secondary__text_color")
	void choseSecondaryTextColorColor(final ClickEvent event) {
		openDialog(ColorType.TEXT_ON_SECONDARY, secondary__text_color);
	}

	@UiHandler("background__color")
	void choseBackgroundColor(final ClickEvent event) {
		openDialog(ColorType.BACKGROUND, background__color);
	}

	@UiHandler("background__text_color")
	void choseBackgroundTextColorColor(final ClickEvent event) {
		openDialog(ColorType.TEXT_ON_BACKGROUND, background__text_color);
	}

	@UiHandler("surface__color")
	void choseSurfaceColor(final ClickEvent event) {
		openDialog(ColorType.SURFACE, surface__color);
	}

	@UiHandler("surface__text_color")
	void choseSurfaceTextColorColor(final ClickEvent event) {
		openDialog(ColorType.TEXT_ON_SURFACE, surface__text_color);
	}

	@UiHandler("color_palette")
	void onChoseColor(ValueChangeEvent<Color> event) {
		final Color value = event.getValue();

		if (value == null) {
			return;
		}

		switch (setting) {
		case PRIMARY:
			primary__color = value;
			materialTheme.setPrimaryColor(value);
			break;
		case TEXT_ON_PRIMARY:
			primary__text_color = value;
			materialTheme.setPrimaryTextColor(value);
			break;
		case SECONDARY:
			secondary__color = value;
			materialTheme.setSecondaryColor(value);
			break;
		case TEXT_ON_SECONDARY:
			secondary__text_color = value;
			materialTheme.setSecondaryTextColor(value);
			break;
		case BACKGROUND:
			background__color = value;
			materialTheme.setBackgroundColor(value);
			break;
		case TEXT_ON_BACKGROUND:
			background__text_color = value;
			materialTheme.setBackgroundTextColor(value);
			break;
		case SURFACE:
			surface__color = value;
			materialTheme.setSurfaceColor(value);
			break;
		case TEXT_ON_SURFACE:
			surface__text_color = value;
			materialTheme.setSurfaceTextColor(value);
			break;
		default:
			break;
		}

		ThemeManager.applyTheme(materialTheme.getAsText());
	}
}
