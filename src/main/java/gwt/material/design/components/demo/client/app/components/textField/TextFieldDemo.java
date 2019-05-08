package gwt.material.design.components.demo.client.app.components.textField;

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

import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.constants.TextFieldType;
import gwt.material.design.components.client.events.IconClickEvent;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.utils.debug.Console;
import gwt.material.design.components.client.utils.helper.ObjectHelper;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.ValidationForTextField;
import gwt.material.design.components.client.validation.ValidationRegistration;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class TextFieldDemo extends Composite implements DemoPanel<MaterialTextField>{

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, TextFieldDemo> {
	}


	@UiField
	MaterialTextField date_mask_field;
	
	@UiField
	MaterialTextField to_do_tests_element;
	@UiField
	MaterialTextField maske_field;
	@UiField
	MaterialTextField min_length_field;
	@UiField
	MaterialTextField max_length_field;
	@UiField
	MaterialSelect<Validation<MaterialInput>> validation_select;
	@UiField
	MaterialSelect<TextFieldType> type_select;
	@UiField
	MaterialSelect<InputType> input_type_select;
	@UiField
	MaterialSelect<State> state_select;

	public TextFieldDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
		date_mask_field.addValidation(ValidationForTextField.input_mask());
		
		
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

		for (TextFieldType type : TextFieldType.values()) {
			final MaterialSelect.Option<TextFieldType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			type_select.add(option);
		}
		type_select.setSelectedIndex(0, false);

		for (InputType type : new InputType[] { InputType.EMAIL, InputType.NUMBER, InputType.PASSWORD, InputType.SEARCH, InputType.TEL, InputType.TEXT, InputType.URL }) {
			final MaterialSelect.Option<InputType> option = new MaterialSelect.Option<>();
			option.setText(type.toString());
			option.setValue(type);
			input_type_select.add(option);
		}
		input_type_select.setValue(InputType.TEXT, false);
		
		for (State state : State.values()) {
			final MaterialSelect.Option<State> option = new MaterialSelect.Option<>();
			option.setText(state.toString());
			option.setValue(state);
			state_select.add(option);
		}
		state_select.setSelectedIndex(0, false);

	}

	@UiHandler("maske_field")
	void onSelecMasker(final KeyUpEvent event) {
		to_do_tests_element.setInputMask(maske_field.getValue());
	}

	@UiHandler("min_length_field")
	void onSelecMinLength(final KeyUpEvent event) {
		to_do_tests_element.setMinLength(ObjectHelper.toInteger(min_length_field.getValue()));
	}

	@UiHandler("max_length_field")
	void onSelecMaxLenght(final KeyUpEvent event) {
		to_do_tests_element.setMaxLength(ObjectHelper.toInteger(max_length_field.getValue()));
	}

	@UiHandler("type_select")
	void onSelectType(final ValueChangeEvent<TextFieldType> event) {
		to_do_tests_element.setType(event.getValue());
	}

	@UiHandler("input_type_select")
	void onInptSelectType(final ValueChangeEvent<InputType> event) {
		to_do_tests_element.setInputType(event.getValue());
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
	
	@UiHandler("require_check")
	void onSelectRequired(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setRequired(event.getValue());
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
	
	@UiHandler("helper_text_persistent_check")
	void onSelectHelperTextPersistent(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setHelperTextPersistent(event.getValue());
	}
	
	@UiHandler("helper_text_validation_check")
	void onSelectHelperTextValidation(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setHelperTextValidation(event.getValue());
	}

	@Override
	public Set<MaterialTextField> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
	
	@UiHandler({"leading_icon_field", "outline_leading_icon_field"})
	void onIconLeadingEvent(final IconClickEvent event) {
		Console.log("leading_icon_field");
	}
	
	@UiHandler("trailing_icon_field")
	void onIconTrailingEvent(final IconClickEvent event) {
		Console.log("trailing_icon_field");
	}
}
