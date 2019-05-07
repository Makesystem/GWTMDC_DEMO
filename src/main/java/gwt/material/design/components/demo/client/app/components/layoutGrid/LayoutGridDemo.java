package gwt.material.design.components.demo.client.app.components.layoutGrid;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.LayoutGridAlign;
import gwt.material.design.components.client.ui.MaterialLayoutCell;
import gwt.material.design.components.client.ui.MaterialLayoutGrid;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.client.utils.helper.ObjectHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class LayoutGridDemo extends Composite implements DemoPanel<MaterialLayoutCell> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, LayoutGridDemo> {
	}

	@UiField
	MaterialLayoutGrid to_do_tests_element;
	@UiField
	MaterialTextField cell_margin_phone_field;
	@UiField
	MaterialTextField cell_margin_tablet_field;
	@UiField
	MaterialTextField cell_margin_desktop_field;
	@UiField
	MaterialTextField cell_gutter_phone_field;
	@UiField
	MaterialTextField cell_gutter_tablet_field;
	@UiField
	MaterialTextField cell_gutter_desktop_field;
	@UiField
	MaterialTextField gap_phone_field;
	@UiField
	MaterialTextField gap_tablet_field;
	@UiField
	MaterialTextField gap_desktop_field;
	@UiField
	MaterialSelect<LayoutGridAlign> align_select;
	
	public LayoutGridDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();		
		for (LayoutGridAlign type : LayoutGridAlign.values()) {
			final MaterialSelect.Option<LayoutGridAlign> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			align_select.add(option);
		}
		align_select.setValue(LayoutGridAlign.CENTER);
	}
	
	@UiHandler("cell_margin_phone_field")
	void onSelecPhoneCellMargin(final KeyUpEvent event) {
		to_do_tests_element.setPhoneCellMargin(ObjectHelper.toInteger(cell_margin_phone_field.getValue(), 0));
	}
	
	@UiHandler("cell_margin_tablet_field")
	void onSelecTabletCellMargin(final KeyUpEvent event) {
		to_do_tests_element.setTabletCellMargin(ObjectHelper.toInteger(cell_margin_tablet_field.getValue(), 0));
	}
	
	@UiHandler("cell_margin_desktop_field")
	void onSelecDesktopCellMargin(final KeyUpEvent event) {
		to_do_tests_element.setDesktopCellMargin(ObjectHelper.toInteger(cell_margin_phone_field.getValue(), 0));
	}

	@UiHandler("cell_gutter_phone_field")
	void onSelecPhoneCellGutter(final KeyUpEvent event) {
		to_do_tests_element.setPhoneCellGutter(ObjectHelper.toInteger(cell_gutter_phone_field.getValue(), 0));
	}
	
	@UiHandler("cell_gutter_tablet_field")
	void onSelecTabletCellGutter(final KeyUpEvent event) {
		to_do_tests_element.setTabletCellGutter(ObjectHelper.toInteger(cell_gutter_tablet_field.getValue(), 0));
	}
	
	@UiHandler("cell_gutter_desktop_field")
	void onSelecDesktopCellGutter(final KeyUpEvent event) {
		to_do_tests_element.setDesktopCellGutter(ObjectHelper.toInteger(cell_gutter_desktop_field.getValue(), 0));
	}
	
	@UiHandler("gap_phone_field")
	void onSelecPhoneGap(final KeyUpEvent event) {
		to_do_tests_element.setPhoneGap(ObjectHelper.toInteger(gap_phone_field.getValue(), 0));
	}
	
	@UiHandler("gap_tablet_field")
	void onSelecTabletGap(final KeyUpEvent event) {
		to_do_tests_element.setTabletGap(ObjectHelper.toInteger(gap_tablet_field.getValue(), 0));
	}
	
	@UiHandler("gap_desktop_field")
	void onSelecDesktopGap(final KeyUpEvent event) {
		to_do_tests_element.setDesktopGap(ObjectHelper.toInteger(gap_desktop_field.getValue(), 0));
	}
	
	@UiHandler("align_select")
	void onSelectLayoutGridAlign(final ValueChangeEvent<LayoutGridAlign> event) {
		to_do_tests_element.setAlign(event.getValue());
	}

	@Override
	public Set<MaterialLayoutCell> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialLayoutCell.class, to_do_tests_element);
	}

}
