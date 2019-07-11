package gwt.material.design.components.demo.client.app.components.snackbar;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialSnackbar;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class SnackbarDemo extends Composite implements DemoPanel<MaterialSnackbar> {

	private static DemoUiBinder uiBinder = GWT.create(DemoUiBinder.class);

	interface DemoUiBinder extends UiBinder<Widget, SnackbarDemo> {
	}
	
	@UiField
	MaterialSnackbar standard_snackbar;
	@UiField
	MaterialSnackbar dismiss_snackbar;
	@UiField
	MaterialSnackbar at_start_snackbar;
	@UiField
	MaterialSnackbar with_action_snackbar;
	@UiField
	MaterialSnackbar with_action_on_bottom_snackbar;
	@UiField
	MaterialSnackbar with_multiline_snackbar;

	public SnackbarDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("standard_snackbar_action")
	void openStandard(final ClickEvent event) {
		standard_snackbar.open();
	}
	
	@UiHandler("dismiss_snackbar_action")
	void openDismiss(final ClickEvent event) {
		dismiss_snackbar.open();
	}
	
	@UiHandler("at_start_snackbar_action")
	void openAtStart(final ClickEvent event) {
		at_start_snackbar.open();
	}

	@UiHandler("with_action_action")
	void openWithAction(final ClickEvent event) {
		with_action_snackbar.open();
	}

	@UiHandler("with_action_on_bottom_action")
	void openWithActionOnBottom(final ClickEvent event) {
		with_action_on_bottom_snackbar.open();
	}

	@UiHandler("with_multiline_action")
	void openWithMultiline(final ClickEvent event) {
		with_multiline_snackbar.open();
	}

	@UiHandler("show_fab_check")
	native void onSelectEnabled(final SelectionEvent<Boolean> event) /*-{
		var className = "."
				+ @gwt.material.design.components.client.constants.CssName::MDC_FAB__FIXED;
		$wnd.jQuery(className).toggle();
	}-*/;

	@Override
	public Set<MaterialSnackbar> getWidgetForTests() {
		return DOMHelper.findByClass(MaterialSnackbar.class, getWidget());
	}
}
