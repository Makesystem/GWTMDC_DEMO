package gwt.material.design.components.demo.client.app.components.typography;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.Typography;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialLayoutCell;

public class TypographyDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, TypographyDemo> {
	}

	@UiField
	MaterialContent content;

	public TypographyDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
		for(Typography typography : Typography.values())
			content.add(cell(typography));
	}
	
	MaterialWidget cell(Typography typography) {
		
		final MaterialLayoutCell cell = new MaterialLayoutCell();
		cell.setCols(12);
		
		final MaterialLabel label = new MaterialLabel();
		label.setBackgroundColor(Color.MDC_THEME_ON_SURFACE);
		label.setColor(Color.MDC_THEME_SURFACE);
		label.setText(typography.toString());
		label.setTypography(typography);
		label.setBorderColor(Color.MDC_THEME_ON_SURFACE);
		label.setBorderStyle(BorderStyle.SOLID);
		label.setBorderWidth(1);
		label.setMargin(4);
		cell.add(label);
		
		return cell;
	}
}
