package gwt.material.design.components.demo.client.app.components.topAppBar;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.TopAppBarType;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialTopAppBar;
import gwt.material.design.components.demo.client.app.DemoApp;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class TopAppBarDemo extends Composite implements DemoPanel<MaterialTopAppBar> {

	private static DemoAppUiBinder uiBinder = GWT.create(DemoAppUiBinder.class);

	interface DemoAppUiBinder extends UiBinder<Widget, TopAppBarDemo> {
	}

	@UiField
	MaterialTopAppBar appBar;
	@UiField
	MaterialSelect<TopAppBarType> type_select;

	public TopAppBarDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();		
		for (TopAppBarType type : TopAppBarType.values()) {
			final MaterialSelect.Option<TopAppBarType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setValue(TopAppBarType.FIXED);
	}
	
	@UiHandler("back_act")
	void onBack(final ClickEvent event) {
		RootPanel.get().clear();
		RootPanel.get().add(new DemoApp());
	}
	
	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<TopAppBarType> event) {
		appBar.setType(event.getValue());
	}


	MaterialListItem getItem(final String title, final boolean selected) {
		final MaterialListItem item = new MaterialListItem();
		item.setText(title);
		item.setSelected(selected);
		return item;
	}

	@Override
	public Set<MaterialTopAppBar> getWidgetForTests() {
		return Arrays.asList(appBar).stream().collect(Collectors.toSet());
	}

}
