package gwt.material.design.components.demo.client.app.components.imageList;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.AspectRatio;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.MaterialImageList;
import gwt.material.design.components.client.ui.MaterialImageListItem;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.ui.MaterialMenu;
import gwt.material.design.components.client.ui.MaterialMenuAnchor;
import gwt.material.design.components.client.ui.MaterialRadioButton;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.client.utils.helper.ObjectHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class ImageListDemo extends Composite implements DemoPanel<MaterialImageListItem> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ImageListDemo> {
	}


	@UiField
	MaterialImageList to_do_tests_element;
	@UiField
	MaterialTextField cols_phone_field;
	@UiField
	MaterialTextField cols_tablet_field;
	@UiField
	MaterialTextField cols_desktop_field;
	@UiField
	MaterialTextField gap_phone_field;
	@UiField
	MaterialTextField gap_tablet_field;
	@UiField
	MaterialTextField gap_desktop_field;
	
	public ImageListDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		for(int i = 1; i < 11; i++)
			to_do_tests_element.add(constructItem(i));
	}
	
	MaterialImageListItem constructItem(final int thisIndex) {
				
		final MaterialImageListItem item = new MaterialImageListItem();
		item.setUrl("https://material-components.github.io/material-components-web-catalog/static/media/photos/3x2/10.jpg");
		item.setLabel("Image " + thisIndex);
		item.setAspectRatio(AspectRatio.ASPECT_16x9);
		
		final MaterialIconButton icon = new MaterialIconButton(IconType.ASPECT_RATIO);
		
		final MaterialMenu menu = new MaterialMenu();
		menu.setToggler(icon.getId());
		for (AspectRatio type : AspectRatio.values()) {
		
			final MaterialListItem menuItem = new MaterialListItem();
			menuItem.setText(type.toString());
		
			final MaterialRadioButton radio = new MaterialRadioButton();
			radio.setName(item.getId());
			radio.setSelected(type.equals(AspectRatio.ASPECT_16x9));
			
			menuItem.addStartDetail(radio, false);
			menuItem.addSelectionHandler(event -> item.setAspectRatio(type));
			menu.add(menuItem);
		}
		
		final MaterialMenuAnchor anchor = new MaterialMenuAnchor();
		anchor.add(icon);
		anchor.add(menu);
		
		item.addSupporting(anchor);		
		
		return item;
	}
	
	@UiHandler("cols_phone_field")
	void onSelecPhoneCols(final KeyUpEvent event) {
		to_do_tests_element.setPhoneCols(ObjectHelper.toInteger(cols_phone_field.getValue(), 0));
	}
	
	@UiHandler("cols_tablet_field")
	void onSelecTabletCols(final KeyUpEvent event) {
		to_do_tests_element.setTabletCols(ObjectHelper.toInteger(cols_phone_field.getValue(), 0));
	}
	
	@UiHandler("cols_desktop_field")
	void onSelecDesktopCols(final KeyUpEvent event) {
		to_do_tests_element.setDesktopCols(ObjectHelper.toInteger(cols_phone_field.getValue(), 0));
	}
	
	@UiHandler("gap_phone_field")
	void onSelecPhoneGap(final KeyUpEvent event) {
		to_do_tests_element.setPhoneGap(ObjectHelper.toInteger(gap_phone_field.getValue(), 0));
	}
	
	@UiHandler("gap_tablet_field")
	void onSelecTabletGap(final KeyUpEvent event) {
		to_do_tests_element.setTabletGap(ObjectHelper.toInteger(gap_phone_field.getValue(), 0));
	}
	
	@UiHandler("gap_desktop_field")
	void onSelecDesktopGap(final KeyUpEvent event) {
		to_do_tests_element.setDesktopGap(ObjectHelper.toInteger(gap_phone_field.getValue(), 0));
	}
	
	@UiHandler("with_text_protection_check")
	void onSelectWithTextProtection(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setWithTextProtection(event.getValue());
	}
	
	@UiHandler("masonry_check")
	void onSelectMasonry(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setMasonry(event.getValue());
	}

	@Override
	public Set<MaterialImageListItem> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialImageListItem.class, to_do_tests_element);
	}
}
