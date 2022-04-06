package gwt.material.design.components.demo.client.app.components.dialog;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialDialog;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.MaterialTextArea;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class DialogDemo extends Composite implements DemoPanel<MaterialDialog> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, DialogDemo> {
	}

	@UiField
	MaterialDialog to_do_tests_element;
	@UiField
	MaterialLabel to_do_tests_element_content;
	@UiField
	MaterialTextField accept_text_field;
	@UiField
	MaterialTextField cancel_text_field;
	@UiField
	MaterialTextField title_field;
	@UiField
	MaterialTextArea content_text_field;

	public DialogDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("auto_stack_buttons_check")
	void onSelectAutoStackButtons(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAutoStackButtons(event.getValue());
	}

	@UiHandler("accept_enabled_check")
	void onSelectEnableAccept(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAcceptEnabled(event.getValue());
	}

	@UiHandler("accept_text_field")
	void onSelectAcceptText(final KeyUpEvent event) {
		to_do_tests_element.setAcceptText(accept_text_field.getValue());
	}

	@UiHandler("cancel_text_field")
	void onSelectCancelText(final KeyUpEvent event) {
		to_do_tests_element.setCancelText(cancel_text_field.getValue());
	}

	@UiHandler("title_field")
	void onSelectTitleText(final KeyUpEvent event) {
		to_do_tests_element.setTitle(title_field.getValue());
	}

	@UiHandler("content_text_field")
	void onSelectContentText(final KeyUpEvent event) {
		to_do_tests_element_content.setText(content_text_field.getValue());
	}

	@Override
	public Set<MaterialDialog> getWidgetForTests() {
		return Arrays.asList(to_do_tests_element).stream().collect(Collectors.toSet());
	}
}
