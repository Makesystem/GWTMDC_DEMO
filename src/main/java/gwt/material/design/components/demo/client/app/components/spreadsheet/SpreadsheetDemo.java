package gwt.material.design.components.demo.client.app.components.spreadsheet;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.events.AddEvent;
import gwt.material.design.components.client.ui.MaterialFileUpload;
import gwt.material.design.components.client.ui.MaterialSpreadsheet;

public class SpreadsheetDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, SpreadsheetDemo> {
	}

	@UiField
	MaterialSpreadsheet to_do_tests_element;

	public SpreadsheetDemo() {
		initWidget(uiBinder.createAndBindUi(this));

		to_do_tests_element.setFormatter((id, cell, row, column, value) -> {
			if (row == 0) {				
				final String header = CsvUtils.identifyColumnName(value == null ? "" : value.toString());				
				if(header == null) {
					to_do_tests_element.setHeader("Não identificada", column);
					to_do_tests_element.setCellState(id, State.ERROR);
				} else {
					to_do_tests_element.setHeader(header, column);
					to_do_tests_element.setCellState(id, State.SUCCESS);
				}
			}
			return value;
		});
	}

	@UiHandler("file_upload")
	void onAdd(final AddEvent<Collection<MaterialFileUpload.File>> event) {
		event.getValue().forEach(file -> {
			final String url = file.getDataAsUrl();
			to_do_tests_element.setUrl(url);
		});
	}

}
