package gwt.material.design.components.demo.client.app.components.icon;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialIcon;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.utils.helper.StyleHelper;
import gwt.material.design.components.client.utils.helper.TimerHelper;

public class IconDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, IconDemo> {
	}

	@UiField
	MaterialContent content;
	@UiField
	MaterialTextField filter_text_field;

	public IconDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		final Set<String> letters = new LinkedHashSet<>();
		TimerHelper.schedule(100,
				() -> Arrays.asList(IconType.values()).stream().sorted((a, b) -> a.toString().compareTo(b.toString()))
						.map(iconType -> getIcon(iconType)).forEach(icon -> {
							final String firstLetter = icon.getType().toString().substring(0, 1).toUpperCase();
							if (!letters.contains(firstLetter)) {
								final MaterialLabel label = new MaterialLabel(firstLetter);
								label.setWidth("100%");
								label.setOpacity(0.82);
								content.add(label);
								letters.add(firstLetter);
							}
							content.add(icon);
						}));
	}

	@UiHandler("filter_text_field")
	void onFilter(final KeyUpEvent event) {
		filter(content, HtmlElements.I.getTag(), filter_text_field.getText());
	}

	void filter(UIObject uiObject, String child, String text) {
		filter(uiObject.getElement(), child, text);
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		StyleHelper.setAttribute(content,  HtmlElements.I.getTag(), CssAttribute.DISABLED, !event.getValue() ? Boolean.TRUE.toString() : null);
	};

	native void filter(Element element, String child, String text) /*-{
		text = text ? text.toLowerCase() : "";
		child = child ? child : "> *";
		var id = "#" + $wnd.jQuery(element).attr("id") + " " + child;

		$wnd.jQuery(id).filter(function() {
			var innerText = $wnd.jQuery(this).text().toLowerCase();
			$wnd.jQuery(this).toggle(innerText.indexOf(text) > -1)
		});

	}-*/;

	MaterialIcon getIcon(final IconType iconType) {
		final MaterialIcon icon = new MaterialIcon(iconType);
		icon.setMargin(8);
		icon.setTooltip(iconType.toString());
		return icon;
	}

}
