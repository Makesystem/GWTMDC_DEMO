package gwt.material.design.components.demo.client.themeEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.events.AcceptEvent;
import gwt.material.design.components.client.events.CancelEvent;
import gwt.material.design.components.client.events.CloseEvent;
import gwt.material.design.components.client.events.OpeningEvent;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialChip;
import gwt.material.design.components.client.ui.MaterialCircularProgress;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialDialog;
import gwt.material.design.components.client.ui.MaterialDrawer;
import gwt.material.design.components.client.ui.MaterialFab;
import gwt.material.design.components.client.ui.MaterialFabProgress;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.ui.MaterialMenu;
import gwt.material.design.components.client.ui.MaterialRadioButton;
import gwt.material.design.components.client.ui.MaterialSelect;
import gwt.material.design.components.client.ui.MaterialSideSheet;
import gwt.material.design.components.client.ui.MaterialTab;
import gwt.material.design.components.client.ui.MaterialTabBar;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.utils.helper.TimerHelper;
import gwt.material.design.components.demo.client.themeEditor.items.ChartsUi;
import gwt.material.design.components.demo.client.themeEditor.items.IconsUi;
import gwt.material.design.components.demo.client.themeEditor.misc.ColorPaletteColumn;

public class ThemeEditor extends Composite {

	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	interface HomeViewUiBinder extends UiBinder<Widget, ThemeEditor> {
	}

	@UiField
	ColorPaletteColumn color_palette_column;
	@UiField
	MaterialDrawer drawer;
	@UiField
	MaterialSideSheet side_sheet;
	@UiField
	MaterialCircularProgress circular_progress;

	public ThemeEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		for (InputType inputType : new InputType[] { InputType.EMAIL, InputType.NUMBER, InputType.PASSWORD,
				InputType.SEARCH, InputType.TEL, InputType.TEXT, InputType.URL, InputType.SUBMIT }) {

			final MaterialListItem item = new MaterialListItem();
			item.setText(inputType.toString());
			item.addSelectionHandler(event -> {
				if (event.getValue())
					input_type__field.setInputType(inputType);
			});

			final MaterialRadioButton radioButton = new MaterialRadioButton();
			radioButton.setName("inputType");

			item.addStartDetail(radioButton, true);

			input_type__menu.add(item);
		}
	}

	@UiHandler("download_act")
	void onDownload(final ClickEvent event) {
		color_palette_column.download();
	}

	@UiHandler("filter_action")
	void onOpenSideSheet(final ClickEvent event) {
		side_sheet.setOpen(!side_sheet.isOpen());
	}

	@UiHandler("menu_act")
	void onMenu(final ClickEvent event) {
		drawer.setOpen(!drawer.isOpen());
	}

	@UiHandler("slider")
	void onSlider(final ValueChangeEvent<Double> event) {
		circular_progress.setProgress(event.getValue() / 100.d);
	}

	@UiField
	MaterialSelect<String> select_box;

	@UiHandler("select_box")
	void onSelectBox(final ValueChangeEvent<String> event) {
		GWT.log("Value on select: " + event.getValue());
	}

	@UiField
	MaterialTabBar tab;

	@UiHandler("fab")
	void onFab(final ClickEvent event) {
		tab.setSelectedTab(2);
	}

	@UiField
	MaterialChip chip_dialog;
	@UiField
	MaterialDialog dialog_default;

	@UiHandler("chip_dialog")
	void onChipDialog(final ClickEvent event) {
		dialog_default.open();
	}

	@UiHandler("tab")
	void onSelect(final SelectionEvent<MaterialTab> event) {
		tab.getTabs().forEach(tab -> GWT.log("Tab: " + tab.getText() + ":" + tab.isSelected()));
	}

	@UiField
	MaterialMenu input_type__menu;
	@UiField
	MaterialTextField input_type__field;

	@UiHandler("input_type__open")
	void onOpenInputTypeMenu(final ClickEvent event) {
		input_type__menu.open();
	}

	@UiField
	MaterialMenu menu;
	@UiField
	MaterialContent components_1;
	@UiField
	MaterialContent components_2;
	@UiField
	MaterialContent components_3;
	@UiField
	ChartsUi charts;
	@UiField
	IconsUi icons;

	@UiHandler("menu__open")
	void onOpenMenu(final ClickEvent event) {
		menu.open();
	}

	@UiHandler("menu_item_components_1")
	void onSelectComponents1(final SelectionEvent<Boolean> event) {
		GWT.log("chupa: " + event.getValue());
		if (event.getValue()) {
			components_1.setDisplay(Display.FLEX);
		} else {
			components_1.setDisplay(Display.NONE);
		}
	}

	@UiHandler("menu_item_components_2")
	void onSelectComponents2(final SelectionEvent<Boolean> event) {
		if (event.getValue()) {
			components_2.setDisplay(Display.FLEX);
		} else {
			components_2.setDisplay(Display.NONE);
		}
	}

	@UiHandler("menu_item_components_3")
	void onSelectComponents3(final SelectionEvent<Boolean> event) {
		if (event.getValue()) {
			components_3.setDisplay(Display.FLEX);
		} else {
			components_3.setDisplay(Display.NONE);
		}
	}

	@UiHandler("menu_item_charts")
	void onSelectCharts(final SelectionEvent<Boolean> event) {
		charts.setVisible(event.getValue());
	}

	@UiHandler("menu_item_icons")
	void onSelectIcons(final SelectionEvent<Boolean> event) {
		icons.setVisible(event.getValue());
	}

	@UiHandler({ "chip_1", "chip_2", "chip_3", "list_item_1", "list_item_2", "list_item_3", "list_item_4",
			"list_item_5", "checkbox_1", "radio_button_1", "radio_button_2", "switch_1", "toggle_1" })
	void onChip(final SelectionEvent<Boolean> event) {

		final MaterialWidget widget = (MaterialWidget) event.getSource();

		final String text = "item: [" + widget.getClass().getSimpleName() + "] [" + event.getValue() + "]";

		if (event.getValue()) {
			//MaterialSnackbar.showSnackBar(text);
		}
		GWT.log(text);

	}

	@UiHandler("dialog_default")
	void onOpeningDialog(final OpeningEvent event) {
		GWT.log("Opening");
	}

	@UiHandler("dialog_default")
	void onAcceptDialog(final AcceptEvent event) {
		GWT.log("accept");
	}

	@UiHandler("dialog_default")
	void onCancelDialog(final CancelEvent event) {
		GWT.log("cancel");
	}

	@UiHandler("dialog_default")
	void onCloseDialog(final CloseEvent event) {
		GWT.log("close: " + event.getAction());
	}

	@UiField
	MaterialFab fab_1;

	@UiHandler("fab_1")
	void onFab1(final ClickEvent event) {
		if (fab_1.getText().isEmpty()) {
			fab_1.setText("Text");
		} else {
			fab_1.setText("");
		}
	}

	@UiField
	MaterialFabProgress fab_progress;
	Timer timer;

	@UiHandler("fab_progress")
	void onFabWithProgress(final ClickEvent event) {

		if (timer != null)
			return;

		fab_progress.setIndeterminate(true);
		fab_progress.setReverse(true);
		TimerHelper.schedule(1550, () -> {
			fab_progress.setIndeterminate(false);
			fab_progress.setProgress(fab_progress.getProgress() + 0.000000001d);
			timer = TimerHelper.scheduleRepeating(400, () -> {
				fab_progress.setProgress(fab_progress.getProgress() + 0.1d);
				if (fab_progress.getProgress() > 0.99d) {
					fab_progress.setIcon(IconType.DONE, true);
					fab_progress.setBackgroundColor(Color.MDC_THEME_SECONDARY);
					fab_progress.setIconColor(Color.MDC_THEME_ON_SECONDARY);
					TimerHelper.schedule(2000, () -> {
						fab_progress.setProgress(0d, false);
						fab_progress.setBackgroundColor(Color.MDC_THEME_PRIMARY);
						fab_progress.setIconColor(Color.MDC_THEME_ON_PRIMARY);
						fab_progress.setIcon(IconType.CLOUD_DOWNLOAD, true);
					});
					timer.cancel();
					timer = null;
				}
			});
		});
	}
}
