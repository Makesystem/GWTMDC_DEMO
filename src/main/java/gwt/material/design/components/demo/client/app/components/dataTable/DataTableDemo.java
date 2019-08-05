package gwt.material.design.components.demo.client.app.components.dataTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialDataTable;
import gwt.material.design.components.client.ui.MaterialDataTable.Column;

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
		
		dataTable.setColumns(
				new Column("Column 0"),
				new Column("Column 1"),
				new Column("Column 2"),
				new Column("Column 3"),
				new Column("Column 4"));
		
		for (int r = 1; r <= 1000; r++) {
			
			final String[] row = { 
					"colun 0: " + r,
					"colun 1: " + r, 
					"colun 2: " + r, 
					"colun 3: " + r,
					"colun 4: " + r, };
			
			dataTable.addData(row);
			
		}
		
	}
}
