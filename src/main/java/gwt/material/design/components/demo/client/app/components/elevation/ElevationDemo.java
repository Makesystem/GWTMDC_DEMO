package gwt.material.design.components.demo.client.app.components.elevation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.Elevation;
import gwt.material.design.components.client.constants.FlexAlignContent;
import gwt.material.design.components.client.constants.FlexAlignItems;
import gwt.material.design.components.client.constants.FlexJustifyContent;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialLayoutCell;
import gwt.material.design.components.client.ui.MaterialLayoutInner;

public class ElevationDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ElevationDemo> {
	}

	@UiField
	MaterialLayoutInner content;

	public ElevationDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
		for(Elevation elevation : Elevation.values())
			content.add(cell(elevation));
	}
	
	MaterialWidget cell(Elevation elevation) {
		
		final MaterialLayoutCell cell = new MaterialLayoutCell();
		cell.setCols(2);
		cell.setPadding(4);
		
		final MaterialLabel label = new MaterialLabel();
		label.setBackgroundColor(Color.MDC_THEME_SURFACE);
		label.setColor(Color.MDC_THEME_ON_SURFACE);
		label.setText(elevation.toString());
		label.setDisplay(Display.FLEX);
		label.setFlexAlignContent(FlexAlignContent.CENTER);
		label.setFlexAlignItems(FlexAlignItems.CENTER);
		label.setFlexJustifyContent(FlexJustifyContent.CENTER);
		label.setWidth("100%");
		label.setHeight("96px");
		label.setElevation(elevation);
		label.setBorderRadius("4px");
		cell.add(label);
		
		//UiHelper.turnDraggable(label.getElement());
		
		return cell;
	}
}
