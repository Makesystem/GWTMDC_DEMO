package gwt.material.design.components.demo.client.app.components.dataTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialDataTable;

public class DataTableDemo extends Composite {
	
	private static DemoUiBinder uiBinder = GWT.create(DemoUiBinder.class);
	
	interface DemoUiBinder extends UiBinder<Widget, DataTableDemo> {
	}
	
	@UiField
	MaterialDataTable dataTable;
	
	public DataTableDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
		dataTable.setHeader("Column 1", "Column 2", "Column 3", "Column 4", "Column 5");
		
		for (int r = 1; r <= 1000; r++) {
			
			final String[] row = { "colun 1: " + r, "colun 2: " + r, "colun 3: " + r, "colun 4: " + r,
					"colun 5: " + r, };
			
			dataTable.addData(row);
			
		}
		
		dataTable.layout();
	}
}
