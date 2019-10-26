package gwt.material.design.components.demo.client.app;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.events.AcceptEvent.HasAcceptHandlers;
import gwt.material.design.components.client.events.ActiveEvent.HasActiveHandlers;
import gwt.material.design.components.client.events.CancelEvent.HasCancelHandlers;
import gwt.material.design.components.client.events.CloseEvent.HasCloseHandlers;
import gwt.material.design.components.client.events.ClosingEvent.HasClosingHandlers;
import gwt.material.design.components.client.events.IconClickEvent.HasIconClickHandlers;
import gwt.material.design.components.client.events.InputEvent.HasInputHandlers;
import gwt.material.design.components.client.events.OpenEvent.HasOpenHandlers;
import gwt.material.design.components.client.events.OpeningEvent.HasOpeningHandlers;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.ValidationEvent.HasValidationHandlers;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialDrawer;
import gwt.material.design.components.client.ui.MaterialList;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.ui.MaterialSideSheet;
import gwt.material.design.components.client.utils.debug.Console;
import gwt.material.design.components.client.utils.helper.StyleHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;
import gwt.material.design.components.demo.client.app.components.banner.BannerDemo;
import gwt.material.design.components.demo.client.app.components.button.ButtonDemo;
import gwt.material.design.components.demo.client.app.components.card.CardDemo;
import gwt.material.design.components.demo.client.app.components.chart.bar.ChartBarDemo;
import gwt.material.design.components.demo.client.app.components.chart.line.ChartLineDemo;
import gwt.material.design.components.demo.client.app.components.chart.pie.ChartPieDemo;
import gwt.material.design.components.demo.client.app.components.checkBox.CheckboxDemo;
import gwt.material.design.components.demo.client.app.components.chip.ChipDemo;
import gwt.material.design.components.demo.client.app.components.circularProgress.CircularProgressDemo;
import gwt.material.design.components.demo.client.app.components.dataTable.DataTableDemo;
import gwt.material.design.components.demo.client.app.components.datePicker.DatePickerDemo;
import gwt.material.design.components.demo.client.app.components.dialog.DialogDemo;
import gwt.material.design.components.demo.client.app.components.drawer.DrawerDemo;
import gwt.material.design.components.demo.client.app.components.elevation.ElevationDemo;
import gwt.material.design.components.demo.client.app.components.fab.FabDemo;
import gwt.material.design.components.demo.client.app.components.fabProgress.FabProgressDemo;
import gwt.material.design.components.demo.client.app.components.fileUpload.FileUploadDemo;
import gwt.material.design.components.demo.client.app.components.icon.IconDemo;
import gwt.material.design.components.demo.client.app.components.iconButton.IconButtonDemo;
import gwt.material.design.components.demo.client.app.components.iconToggle.IconToggleDemo;
import gwt.material.design.components.demo.client.app.components.imageList.ImageListDemo;
import gwt.material.design.components.demo.client.app.components.layoutGrid.LayoutGridDemo;
import gwt.material.design.components.demo.client.app.components.leaderLine.LeaderLineDemo;
import gwt.material.design.components.demo.client.app.components.linearProgress.LinearProgressDemo;
import gwt.material.design.components.demo.client.app.components.link.LinkDemo;
import gwt.material.design.components.demo.client.app.components.listItem.ListDemo;
import gwt.material.design.components.demo.client.app.components.menu.MenuDemo;
import gwt.material.design.components.demo.client.app.components.multiDatePicker.MultiDatePickerDemo;
import gwt.material.design.components.demo.client.app.components.radioButton.RadioButtonDemo;
import gwt.material.design.components.demo.client.app.components.rangeDatePicker.RangeDatePickerDemo;
import gwt.material.design.components.demo.client.app.components.select.SelectDemo;
import gwt.material.design.components.demo.client.app.components.sideSheet.SideSheetDemo;
import gwt.material.design.components.demo.client.app.components.slider.SliderDemo;
import gwt.material.design.components.demo.client.app.components.snackbar.SnackbarDemo;
import gwt.material.design.components.demo.client.app.components.spreadsheet.SpreadsheetDemo;
import gwt.material.design.components.demo.client.app.components.switchDemo.SwitchDemo;
import gwt.material.design.components.demo.client.app.components.tabBar.TabBarDemo;
import gwt.material.design.components.demo.client.app.components.textArea.TextAreaDemo;
import gwt.material.design.components.demo.client.app.components.textField.TextFieldDemo;
import gwt.material.design.components.demo.client.app.components.theme.ThemeDemo;
import gwt.material.design.components.demo.client.app.components.topAppBar.TopAppBarDemo;
import gwt.material.design.components.demo.client.app.components.tree.TreeDemo;
import gwt.material.design.components.demo.client.app.components.typography.TypographyDemo;

public class DemoApp extends Composite {

	private static DemoAppUiBinder uiBinder = GWT.create(DemoAppUiBinder.class);

	interface DemoAppUiBinder extends UiBinder<Widget, DemoApp> {
	}

	@UiField
	MaterialDrawer drawer;
	@UiField
	MaterialList items_list;
	@UiField
	MaterialSideSheet side_sheet;
	@UiField
	MaterialContent content;

	public DemoApp() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		items_list.add(getItem("Banner", event -> setPanel(new BannerDemo())));
		items_list.add(getItem("Button", event -> setPanel(new ButtonDemo())));
		items_list.add(getItem("Card", event -> setPanel(new CardDemo())));
		items_list.add(getItem("Chart - Bar", event -> setPanel(new ChartBarDemo())));
		items_list.add(getItem("Chart - Line", event -> setPanel(new ChartLineDemo())));
		items_list.add(getItem("Chart - Pie", event -> setPanel(new ChartPieDemo())));
		items_list.add(getItem("Checkbox", event -> setPanel(new CheckboxDemo())));
		items_list.add(getItem("Chips", event -> setPanel(new ChipDemo())));
		items_list.add(getItem("Circular Progress", event -> setPanel(new CircularProgressDemo())));
		items_list.add(getItem("Data Table", event -> setPanel(new DataTableDemo())));
		items_list.add(getItem("Date Picker", event -> setPanel(new DatePickerDemo())));
		items_list.add(getItem("Dialog", event -> setPanel(new DialogDemo())));
		items_list.add(getItem("Drawer", event -> setPanel(new DrawerDemo())));
		items_list.add(getItem("Elevation", event -> setPanel(new ElevationDemo())));
		items_list.add(getItem("Fab", event -> setPanel(new FabDemo())));
		items_list.add(getItem("Fab Progress", event -> setPanel(new FabProgressDemo())));
		items_list.add(getItem("File Upload", event -> setPanel(new FileUploadDemo())));
		items_list.add(getItem("Icons", event -> setPanel(new IconDemo())));
		items_list.add(getItem("Icon Button", event -> setPanel(new IconButtonDemo())));
		items_list.add(getItem("Icon Toggle", event -> setPanel(new IconToggleDemo())));
		items_list.add(getItem("Image List", event -> setPanel(new ImageListDemo())));
		items_list.add(getItem("Layout Grid", event -> setPanel(new LayoutGridDemo())));
		items_list.add(getItem("Leader Line", event -> setPanel(new LeaderLineDemo())));
		items_list.add(getItem("List", event -> setPanel(new ListDemo())));
		items_list.add(getItem("Linear Progress", event -> setPanel(new LinearProgressDemo())));
		items_list.add(getItem("Link", event -> setPanel(new LinkDemo())));
		items_list.add(getItem("Menu", event -> setPanel(new MenuDemo())));
		items_list.add(getItem("Multi Date Picker",	event -> setPanel(new MultiDatePickerDemo())));
		items_list.add(getItem("Radio", event -> setPanel(new RadioButtonDemo())));
		items_list.add(getItem("Range Date Picker",	event -> setPanel(new RangeDatePickerDemo())));
		items_list.add(getItem("Select", event -> setPanel(new SelectDemo())));
		items_list.add(getItem("Side Sheet", event -> setPanel(new SideSheetDemo())));
		items_list.add(getItem("Slider", event -> setPanel(new SliderDemo())));
		items_list.add(getItem("Snackbar", event -> setPanel(new SnackbarDemo())));
		items_list.add(getItem("Spreadsheet", event -> setPanel(new SpreadsheetDemo())));
		items_list.add(getItem("Switch", event -> setPanel(new SwitchDemo())));
		items_list.add(getItem("Tab Bar", event -> setPanel(new TabBarDemo())));
		items_list.add(getItem("Text Area", event -> setPanel(new TextAreaDemo())));
		items_list.add(getItem("Text Field", event -> setPanel(new TextFieldDemo())));
		items_list.add(getItem("Theme",	event -> setPanel(new ThemeDemo())));
		items_list.add(getItem("Top App bar", event -> setPanel(new TopAppBarDemo())));
		items_list.add(getItem("Tree", event -> setPanel(new TreeDemo())));
		items_list.add(getItem("Typography", event -> setPanel(new TypographyDemo())));
	}

	@UiHandler("menu_act")
	void onMenu(final ClickEvent event) {
		drawer.setOpen(!drawer.isOpen());
	}

	@UiHandler("download_act")
	void onDownload(final ClickEvent event) {
	}

	@UiHandler("filter_action")
	void onToggleSideSheet(final ClickEvent event) {
		side_sheet.setOpen(!side_sheet.isOpen());
	}

	@UiHandler("home")
	void onSelectPanel(final SelectionEvent<Widget> event) {
		setPanel(event.getValue());
	}

	MaterialListItem getItem(final String title, final ClickHandler handler) {
		final MaterialListItem item = new MaterialListItem();
		item.setText(title);
		item.addClickHandler(handler);
		return item;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	void setPanel(final Widget widget) {

		if (widget instanceof DrawerDemo || widget instanceof TopAppBarDemo || widget instanceof SideSheetDemo) {
			RootPanel.get().clear();
			RootPanel.get().add(widget);
		} else {
			content.clear();
			StyleHelper.setCssProperty(widget, "margin-bottom", "36px");
			content.add(widget);
		}
		
		content.getElement().setScrollTop(0);

		if (widget instanceof DemoPanel) {
			final DemoPanel demoPanel = (DemoPanel) widget;

			final Set<MaterialUIObject> uiObjects = demoPanel.getWidgetForTests();

			for (MaterialUIObject uiObject : uiObjects) {

				if (uiObject instanceof HasSelectionHandlers) {
					final HasSelectionHandlers handlers = (HasSelectionHandlers) uiObject;
					handlers.addSelectionHandler(event -> {
						Console.log("Selection event -> element id: " + uiObject.getAttribute("id") + " | value: "
								+ event.getValue());
					});
				}

				if (uiObject instanceof HasValueChangeHandlers) {
					final HasValueChangeHandlers handlers = (HasValueChangeHandlers) uiObject;
					handlers.addValueChangeHandler(event -> {
						Console.log("Value Change event -> element id: " + uiObject.getAttribute("id")
								+ " | Class: " + uiObject.getClass().getName()
								+ " | value: " + event.getValue());
					});
				}

				if (uiObject instanceof HasOpenHandlers) {
					final HasOpenHandlers handlers = (HasOpenHandlers) uiObject;
					handlers.addOpenHandler(event -> {
						Console.log("Open event -> element id: " + uiObject.getAttribute("id"));
					});
				}

				if (uiObject instanceof HasOpeningHandlers) {
					final HasOpeningHandlers handlers = (HasOpeningHandlers) uiObject;
					handlers.addOpeningHandler(event -> {
						Console.log("Opening event -> element id: " + uiObject.getAttribute("id"));
					});
				}

				if (uiObject instanceof HasCloseHandlers) {
					final HasCloseHandlers handlers = (HasCloseHandlers) uiObject;
					handlers.addCloseHandler(event -> {
						Console.log("Close event -> element id: " + uiObject.getAttribute("id") + " | action: "
								+ event.getAction());
					});
				}

				if (uiObject instanceof HasClosingHandlers) {
					final HasClosingHandlers handlers = (HasClosingHandlers) uiObject;
					handlers.addClosingHandler(event -> {
						Console.log("Closing event -> element id: " + uiObject.getAttribute("id") + " | action: "
								+ event.getAction());
					});
				}

				if (uiObject instanceof HasAcceptHandlers) {
					final HasAcceptHandlers handlers = (HasAcceptHandlers) uiObject;
					handlers.addAcceptHandler(event -> {
						Console.log("Accept event -> element id: " + uiObject.getAttribute("id"));
					});
				}

				if (uiObject instanceof HasActiveHandlers) {
					final HasActiveHandlers handlers = (HasActiveHandlers) uiObject;
					handlers.addActiveHandler(event -> {
						Console.log("Active event -> element id: " + uiObject.getAttribute("id") + " | value: "
								+ event.getValue());
					});
				}

				if (uiObject instanceof HasCancelHandlers) {
					final HasCancelHandlers handlers = (HasCancelHandlers) uiObject;
					handlers.addCancelHandler(event -> {
						Console.log("Cancel event -> element id: " + uiObject.getAttribute("id"));
					});
				}
				
				if (uiObject instanceof HasIconClickHandlers) {
					final HasIconClickHandlers handlers = (HasIconClickHandlers) uiObject;
					handlers.addIconClickHandler(event -> {
						Console.log("Icon Click event -> element id: " + uiObject.getAttribute("id"));
					});
				}
				
				if (uiObject instanceof HasInputHandlers) {
					final HasInputHandlers handlers = (HasInputHandlers) uiObject;
					handlers.addInputHandler(event -> {
						Console.log("Input event -> element id: " + uiObject.getAttribute("id") + " | value: "
								+ event.getValue());
					});
				}
				
				if (uiObject instanceof HasValidationHandlers) {
					final HasValidationHandlers handlers = (HasValidationHandlers) uiObject;
					handlers.addValidationHandler(event -> {
						Console.log("Input event -> element id: " + uiObject.getAttribute("id") + " | result: "
								+ event.getResult());
					});
				}
			}

		}
	}

}
