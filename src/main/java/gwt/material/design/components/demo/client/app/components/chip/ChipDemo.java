package gwt.material.design.components.demo.client.app.components.chip;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.ChipSetType;
import gwt.material.design.components.client.constants.ChipType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialChip;
import gwt.material.design.components.client.ui.MaterialChipSet;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class ChipDemo extends Composite implements DemoPanel<MaterialChip>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, ChipDemo> {
	}

	@UiField
	MaterialChipSet to_do_tests_element;
	@UiField
	MaterialSelect<ChipType> type_select;
	@UiField
	MaterialSelect<ChipSetType> type_set_select;

	public ChipDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		for (ChipType type : ChipType.values()) {
			final MaterialSelect.Option<ChipType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);

		for (ChipSetType type : ChipSetType.values()) {
			final MaterialSelect.Option<ChipSetType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_set_select.add(option);
		}
		type_set_select.setSelectedIndex(0, false);

	}

	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<ChipType> event) {
		to_do_tests_element.getChildrenList().stream().filter(child -> child instanceof MaterialChip).forEach(child -> ((MaterialChip) child).setType(event.getValue()));
	}

	@UiHandler("type_set_select")
	void onSelectSetType(final ValueChangeEvent<ChipSetType> event) {
		to_do_tests_element.setType(event.getValue());
	}

	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@UiHandler("closable_check")
	void onSelectClosable(final SelectionEvent<Boolean> event) {
		to_do_tests_element.getChildrenList().stream().filter(child -> child instanceof MaterialChip).forEach(child -> ((MaterialChip) child).setCloseable(event.getValue()));
	}
	
	@UiHandler("checkmarker_check")
	void onSelectShowCheckmark(final SelectionEvent<Boolean> event) {
		to_do_tests_element.getChildrenList().stream().filter(child -> child instanceof MaterialChip).forEach(child -> ((MaterialChip) child).setShowCheckmark(event.getValue()));
	}

	@Override
	public Set<MaterialChip> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialChip.class, to_do_tests_element);
	}
}
