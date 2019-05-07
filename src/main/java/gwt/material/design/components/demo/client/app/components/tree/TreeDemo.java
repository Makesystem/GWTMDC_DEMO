package gwt.material.design.components.demo.client.app.components.tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.TreeType;
import gwt.material.design.components.client.events.FilterEvent;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.TypingEvent;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialTree;

public class TreeDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, TreeDemo> {
	}

	@UiField
	MaterialTree to_do_tests_element;
	@UiField
	MaterialSelect<TreeType> type_select;
	@UiField
	MaterialLabel selected_lbl;
	@UiField
	MaterialLabel filtered_lbl;

	public TreeDemo() {
		initWidget(uiBinder.createAndBindUi(this));
		to_do_tests_element.setData(generateData_forTest());
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
		for (TreeType type : TreeType.values()) {
			final MaterialSelect.Option<TreeType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);
	}

	@UiHandler("filter_text_field")
	void onTyping(final TypingEvent event) {
		to_do_tests_element.filter(event.getText());
	}
	
	@UiHandler("to_do_tests_element")
	void onSelect(final SelectionEvent<Collection<MaterialTree.Item>> event) {
		selected_lbl.setText("Selected items: " + event.getValue().size() + " / 1000");
	}	
	
	@UiHandler("to_do_tests_element")
	void onFilter(final FilterEvent<Collection<MaterialTree.Item>> event) {
		filtered_lbl.setText("Filtered items: " + event.getValue().size() + " / 1000");
	}	
	
	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<TreeType> event) {
		to_do_tests_element.setType(event.getValue());
	}

	 Collection<MaterialTree.Item> generateData_forTest() {

		final int count = 1000;
		final List<MaterialTree.Item> items = new LinkedList<>();

		for (int i = 0; i < count; i++) {

			final MaterialTree.Item item = new MaterialTree.Item();
			item.setId("item_" + i);
			item.setAvatar(IconType.FOLDER);
			//item.setAvatar("https://material-components.github.io/material-components-web-catalog/static/media/photos/3x2/2.jpg");
			// item.onClick = onClick(item);
			item.setName("Item i " + i);
			// item.onSelect = onSelect(item);
			// item.onUnselect = onUnselect(item);
			// Checkbox
			item.setSelected( i == 990);
			//item.setOnSelect(() -> Console.log("Selecionei " + item.getName()));
			// Radio button
			// item.action = "choice";

			final User user = new User();
			user.setName("I'm the user " + i);
			item.setData(user);
			
			final int parent = Random.nextInt(i);

			if (i > 0)
				item.setParent( items.get(parent).getId());

			items.add(item);
		}

		return items;
	}
	 
	 public static class User {
		 
		 private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	 }
}
