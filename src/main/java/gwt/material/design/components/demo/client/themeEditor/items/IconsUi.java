package gwt.material.design.components.demo.client.themeEditor.items;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.MaterialBanner;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialIcon;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.ui.MaterialTopAppBarActionItem;
import gwt.material.design.components.client.utils.helper.TimerHelper;

public class IconsUi extends Composite {

	private static ChartsUiUiBinder uiBinder = GWT.create(ChartsUiUiBinder.class);

	interface ChartsUiUiBinder extends UiBinder<Widget, IconsUi> {
	}

	@UiField
	MaterialContent content;
	@UiField
	MaterialContent icons_content;
	@UiField
	MaterialBanner banner;
	@UiField
	MaterialTopAppBarActionItem banner__open;
	@UiField
	MaterialTextField filter_text_field;

	public IconsUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		// for (int i = 0; i < 5; i++)
		final Set<String> letters = new LinkedHashSet<>();
		TimerHelper.schedule(100,
				() -> Arrays.asList(IconType.values()).stream().sorted((a, b) -> a.toString().compareTo(b.toString()))
						.map(iconType -> getIcon(iconType)).forEach(icon -> {
							final String firstLetter = icon.getType().toString().substring(0, 1).toUpperCase();
							if (!letters.contains(firstLetter)) {
								final MaterialLabel label = new MaterialLabel(firstLetter);
								label.setWidth("100%");
								label.setOpacity(0.82);
								icons_content.add(label);
								letters.add(firstLetter);
							}
							icons_content.add(icon);
						}));
	}

	public void setVisible(final boolean visible) {
		if (visible)
			content.setDisplay(Display.FLEX);
		else
			content.setDisplay(Display.NONE);
	}

	@UiHandler("banner__open")
	void onOpenBanner(final ClickEvent event) {
		banner.open();
	}

	@UiHandler("banner__close")
	void onCloseBanner(final ClickEvent event) {
		// cutOut.open();
		banner.close();
	}

	@UiHandler("filter_text_field")
	void onFilter(final KeyUpEvent event) {
		filter(icons_content, HtmlElements.I.getTag(),  filter_text_field.getText());
	}

	public static void filter(UIObject uiObject, String text) {
		filter(uiObject.getElement(), null, text);
	}
	
	public static void filter(UIObject uiObject, String child, String text) {
		filter(uiObject.getElement(), child, text);
	}

	public static native void filter(Element element, String child, String text) /*-{
		text = text ? text.toLowerCase() : "";
		child = child ? child : "> *";
		var id = "#" + $wnd.jQuery(element).attr("id") + " " + child;

		$wnd.jQuery(id).filter(function() {
			var innerText = $wnd.jQuery(this).text().toLowerCase();
			$wnd.jQuery(this).toggle(innerText.indexOf(text) > -1)
		});

	}-*/;

	protected MaterialIcon getIcon(final IconType iconType) {
		final MaterialIcon icon = new MaterialIcon(iconType);
		icon.setMargin(8);
		icon.setTooltip(iconType.toString());
		return icon;
	}

}
