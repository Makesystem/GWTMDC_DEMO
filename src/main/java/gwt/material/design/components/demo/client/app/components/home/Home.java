package gwt.material.design.components.demo.client.app.components.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.Elevation;
import gwt.material.design.components.client.constants.Typography;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.MaterialButton;
import gwt.material.design.components.client.ui.MaterialCard;
import gwt.material.design.components.client.ui.MaterialContent;
import gwt.material.design.components.client.ui.MaterialLabel;
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

public class Home extends Composite implements HasSelectionHandlers<Widget> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, Home> {
	}

	@UiField
	MaterialContent content;

	public Home() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		content.add(getItem("Banner", 				event -> fireSelectEvent(new BannerDemo())));
		content.add(getItem("Button", 				event -> fireSelectEvent(new ButtonDemo())));
		content.add(getItem("Card", 				event -> fireSelectEvent(new CardDemo())));
		content.add(getItem("Chart - Bar",	 		event -> fireSelectEvent(new ChartBarDemo())));
		content.add(getItem("Chart - Line", 		event -> fireSelectEvent(new ChartLineDemo())));
		content.add(getItem("Chart - Pie", 			event -> fireSelectEvent(new ChartPieDemo())));
		content.add(getItem("Checkbox", 			event -> fireSelectEvent(new CheckboxDemo())));
		content.add(getItem("Chips", 				event -> fireSelectEvent(new ChipDemo())));
		content.add(getItem("Circular Progress",	event -> fireSelectEvent(new CircularProgressDemo())));
		content.add(getItem("Data Table", 			event -> fireSelectEvent(new DataTableDemo())));
		content.add(getItem("Date Picker", 			event -> fireSelectEvent(new DatePickerDemo())));
		content.add(getItem("Dialog", 				event -> fireSelectEvent(new DialogDemo())));
		content.add(getItem("Drawer", 				event -> fireSelectEvent(new DrawerDemo())));
		content.add(getItem("Elevation", 			event -> fireSelectEvent(new ElevationDemo())));
		content.add(getItem("Fab", 					event -> fireSelectEvent(new FabDemo())));
		content.add(getItem("Fab Progress", 		event -> fireSelectEvent(new FabProgressDemo())));
		content.add(getItem("File Upload", 			event -> fireSelectEvent(new FileUploadDemo())));
		content.add(getItem("Icons", 				event -> fireSelectEvent(new IconDemo())));
		content.add(getItem("Icon Button", 			event -> fireSelectEvent(new IconButtonDemo())));
		content.add(getItem("Icon Toggle", 			event -> fireSelectEvent(new IconToggleDemo())));
		content.add(getItem("Image List", 			event -> fireSelectEvent(new ImageListDemo())));
		content.add(getItem("Layout Grid", 			event -> fireSelectEvent(new LayoutGridDemo())));
		content.add(getItem("Leader Line", 			event -> fireSelectEvent(new LeaderLineDemo())));
		content.add(getItem("List", 				event -> fireSelectEvent(new ListDemo())));
		content.add(getItem("Linear Progress", 		event -> fireSelectEvent(new LinearProgressDemo())));
		content.add(getItem("Link", 				event -> fireSelectEvent(new LinkDemo())));
		content.add(getItem("Menu", 				event -> fireSelectEvent(new MenuDemo())));
		content.add(getItem("Multi Date Picker",	event -> fireSelectEvent(new MultiDatePickerDemo())));
		content.add(getItem("Radio", 				event -> fireSelectEvent(new RadioButtonDemo())));
		content.add(getItem("Range Date Picker",	event -> fireSelectEvent(new RangeDatePickerDemo())));
		content.add(getItem("Select", 				event -> fireSelectEvent(new SelectDemo())));
		content.add(getItem("Side Sheet", 			event -> fireSelectEvent(new SideSheetDemo())));
		content.add(getItem("Slider", 				event -> fireSelectEvent(new SliderDemo())));
		content.add(getItem("Snackbar", 			event -> fireSelectEvent(new SnackbarDemo())));
		content.add(getItem("Spreadsheet", 			event -> fireSelectEvent(new SpreadsheetDemo())));
		content.add(getItem("Switch", 				event -> fireSelectEvent(new SwitchDemo())));
		content.add(getItem("Tab Bar", 				event -> fireSelectEvent(new TabBarDemo())));
		content.add(getItem("Text Area", 			event -> fireSelectEvent(new TextAreaDemo())));
		content.add(getItem("Text Field", 			event -> fireSelectEvent(new TextFieldDemo())));
		content.add(getItem("Theme", 				event -> fireSelectEvent(new ThemeDemo())));
		content.add(getItem("Top App bar", 			event -> fireSelectEvent(new TopAppBarDemo())));
		content.add(getItem("Tree", 				event -> fireSelectEvent(new TreeDemo())));
		content.add(getItem("Typography", 			event -> fireSelectEvent(new TypographyDemo())));

	}

	protected void fireSelectEvent(final Widget widget) {
		SelectionEvent.fire(this, widget);
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Widget> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	MaterialCard getItem(final String title, final ClickHandler handler) {

		final MaterialCard card = new MaterialCard() {
			@Override
			protected void onLoad() {
				super.onLoad();
				setWidth("100%");
				setMaxWidth("240px");
				setMargin(4);
				setBorderRadius("24px 8px 24px 8px");
				setBorderWidth(1);
				setBorderStyle(BorderStyle.SOLID);
				setBorderColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);
				setElevation(Elevation.Z_0);
				setPaddingTop(16);

				final MaterialLabel label = new MaterialLabel(title);
				label.setTypography(Typography.HEADLINE_6);
				label.setMargin(16);
				addContent(label);

				final MaterialButton button = new MaterialButton();
				button.setText("See");
				button.setType(ButtonType.OUTLINE);
				button.setMarginLeft(8);
				button.addClickHandler(handler);
				addButtons(button);
			}
		};

		return card;
	}
}
