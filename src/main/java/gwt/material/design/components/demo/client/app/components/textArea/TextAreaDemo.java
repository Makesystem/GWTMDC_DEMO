package gwt.material.design.components.demo.client.app.components.textArea;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Resize;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.constants.TextFieldType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialTextArea;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.utils.helper.ObjectHelper;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.ValidationForTextField;
import gwt.material.design.components.client.validation.ValidationRegistration;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class TextAreaDemo extends Composite implements DemoPanel<MaterialTextArea>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, TextAreaDemo> {
	}

	@UiField
	MaterialTextArea to_do_tests_element;
	@UiField
	MaterialTextField min_length_field;
	@UiField
	MaterialTextField max_length_field;
	@UiField
	MaterialTextField rows_field;
	@UiField
	MaterialTextField cols_field;
	@UiField
	MaterialSelect<TextFieldType> type_select;
	@UiField
	MaterialSelect<Validation<MaterialInput>> validation_select;
	@UiField
	MaterialSelect<Resize> resize_select;
	@UiField
	MaterialSelect<State> state_select;

	public TextAreaDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
		for (TextFieldType type : TextFieldType.values()) {
			final MaterialSelect.Option<TextFieldType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);
		
		final Map<String, Validation<MaterialInput>> validations = new LinkedHashMap<>();
		validations.put("None", null);
		validations.put("Security level", ValidationForTextField.password_security_level());
		validations.put("Min and Max none result", ValidationForTextField.min_max_length__none_result());
		validations.put("Min and Max success result", ValidationForTextField.min_max_length__success_result());
		validations.put("Required none result", ValidationForTextField.required__none_result());
		validations.put("Required success result", ValidationForTextField.required__success_result());
		validations.put("Input Mask", ValidationForTextField.input_mask());
		validations.put("Date", ValidationForTextField.date());

		for (Map.Entry<String, Validation<MaterialInput>> validation : validations.entrySet()) {
			final MaterialSelect.Option<Validation<MaterialInput>> option = new MaterialSelect.Option<>();
			option.setText(validation.getKey());
			option.setValue(validation.getValue());
			validation_select.add(option);
		}
		validation_select.setSelectedIndex(0, false);

		for (Resize type : Resize.values()) {
			final MaterialSelect.Option<Resize> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			resize_select.add(option);
		}
		resize_select.setSelectedIndex(0, false);
		
		for (State state : State.values()) {
			final MaterialSelect.Option<State> option = new MaterialSelect.Option<>();
			option.setText(state.toString());
			option.setValue(state);
			state_select.add(option);
		}
		state_select.setSelectedIndex(0, false);

	}

	@UiHandler("min_length_field")
	void onSelecMinLength(final KeyUpEvent event) {

		to_do_tests_element.setMinLength(ObjectHelper.toInteger(min_length_field.getValue()));
	}

	@UiHandler("max_length_field")
	void onSelecMaxLenght(final KeyUpEvent event) {
		to_do_tests_element.setMaxLength(ObjectHelper.toInteger(max_length_field.getValue()));
	}
	
	@UiHandler("rows_field")
	void onSelecRows(final KeyUpEvent event) {
		to_do_tests_element.setRows(ObjectHelper.toInteger(rows_field.getValue()));
	}

	@UiHandler("max_length_field")
	void onSelecCols(final KeyUpEvent event) {
		to_do_tests_element.setCols(ObjectHelper.toInteger(cols_field.getValue()));
	}

	@UiHandler("resize_select")
	void onSelectResize(final ValueChangeEvent<Resize> event) {
		to_do_tests_element.setResize(event.getValue());
	}

	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<TextFieldType> event) {
		to_do_tests_element.setType(event.getValue());
	}
	
	ValidationRegistration registration; 
	@UiHandler("validation_select")
	void onSelectValidation(final ValueChangeEvent<Validation<MaterialInput>> event) {
		if(registration != null)
			registration.removeValidation();
		registration = to_do_tests_element.addValidation(event.getValue());
	}
	
	@UiHandler("state_select")
	void onSelectState(final ValueChangeEvent<State> event) {
		to_do_tests_element.setState(event.getValue());
	}
	
	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}
	
	@UiHandler("dense_check")
	void onSelectDense(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setDense(event.getValue());
	}
	
	@UiHandler("read_only_check")
	void onSelectReadOnly(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setReadOnly(event.getValue());
	}
	
	@UiHandler("unbordered_check")
	void onSelectUnbordered(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setUnbordered(event.getValue());
	}

	@Override
	public Set<MaterialTextArea> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
