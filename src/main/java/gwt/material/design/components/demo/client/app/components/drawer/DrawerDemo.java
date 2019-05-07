package gwt.material.design.components.demo.client.app.components.drawer;

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

import gwt.material.design.components.client.constants.DrawerType;
import gwt.material.design.components.client.ui.MaterialDrawer;
import gwt.material.design.components.client.ui.MaterialList;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.demo.client.app.DemoApp;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class DrawerDemo extends Composite implements DemoPanel<MaterialDrawer> {

	private static DemoAppUiBinder uiBinder = GWT.create(DemoAppUiBinder.class);

	interface DemoAppUiBinder extends UiBinder<Widget, DrawerDemo> {
	}

	@UiField
	MaterialDrawer drawer;
	@UiField
	MaterialList items_list;
	@UiField
	MaterialSelect<DrawerType> type_select;

	public DrawerDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		items_list.add(getItem("Menu item 1", true));
		items_list.add(getItem("Menu item 2", false));
		items_list.add(getItem("Menu item 3", false));
		items_list.add(getItem("Menu item 4", false));
		items_list.add(getItem("Menu item 5", false));
		items_list.add(getItem("Menu item 6", false));

		for (DrawerType type : DrawerType.values()) {
			final MaterialSelect.Option<DrawerType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setValue(DrawerType.PERMANENT);
	}
	
	@UiHandler("back_act")
	void onBack(final ClickEvent event) {
		RootPanel.get().clear();
		RootPanel.get().add(new DemoApp());
	}
	
	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<DrawerType> event) {
		drawer.setType(event.getValue());
	}


	MaterialListItem getItem(final String title, final boolean selected) {
		final MaterialListItem item = new MaterialListItem();
		item.setText(title);
		item.setSelected(selected);
		return item;
	}

	@Override
	public Set<MaterialDrawer> getWidgetForTests() {
		return Arrays.asList(drawer).stream().collect(Collectors.toSet());
	}

}
