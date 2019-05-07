package gwt.material.design.components.demo.client.app.components.listItem;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialList;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class ListDemo extends Composite implements DemoPanel<MaterialListItem>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ListDemo> {
	}

	@UiField
	MaterialList to_do_tests_element;	

	public ListDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@Override
	public Set<MaterialListItem> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialListItem.class, to_do_tests_element);
	}
}
