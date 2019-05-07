package gwt.material.design.components.demo.client.app.components.leaderLine;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.LeaderLinePath;
import gwt.material.design.components.client.constants.PlugSocket;
import gwt.material.design.components.client.constants.PlugType;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.ui.MaterialChip;
import gwt.material.design.components.client.ui.MaterialLeaderLine;
import gwt.material.design.components.client.ui.MaterialSelect;

public class LeaderLineDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, LeaderLineDemo> {
	}

	@UiField
	MaterialChip startElement;
	@UiField
	MaterialChip endElement;
	@UiField
	MaterialSelect<LeaderLinePath> path__select;
	@UiField
	MaterialSelect<PlugType> start_plug__select;
	@UiField
	MaterialSelect<PlugSocket> start_plug_socket___select;
	@UiField
	MaterialSelect<PlugType> end_plug__select;
	@UiField
	MaterialSelect<PlugSocket> end_plug_socket___select;
	
	MaterialLeaderLine leaderLine;

	public LeaderLineDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		leaderLine = new MaterialLeaderLine(startElement, endElement);
		leaderLine.setHide(true);
		leaderLine.draw();
		
		path__select.addOptions(LeaderLinePath.values());		
		path__select.setValue(LeaderLinePath.MAGNET, false);
		
		start_plug__select.addOptions(PlugType.values());		
		start_plug__select.setValue(PlugType.BEHIND, false);
		
		start_plug_socket___select.addOptions(PlugSocket.values());		
		start_plug_socket___select.setValue(PlugSocket.AUTO, false);
		
		end_plug__select.addOptions(PlugType.values());		
		end_plug__select.setValue(PlugType.ARROW_2, false);
		
		end_plug_socket___select.addOptions(PlugSocket.values());		
		end_plug_socket___select.setValue(PlugSocket.AUTO, false);
						
		startElement.addClickHandler(event -> leaderLine.show());
		endElement.addClickHandler(event -> leaderLine.hide());
	}

	@UiHandler("line_size__slider")
	void onChangeLineSize(final ValueChangeEvent<Double> event) {
		leaderLine.setSize(event.getValue().intValue());
		leaderLine.draw();
	}

	@UiHandler("outline__checkbox")
	void onChangeOutline(final SelectionEvent<Boolean> event) {
		leaderLine.setOutline(event.getValue());
		leaderLine.draw();
	}

	@UiHandler("outline_size__slider")
	void onChangeOutlineSize(final ValueChangeEvent<Double> event) {
		leaderLine.setOutlineSize(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("path__select")
	void onSelectPath(final ValueChangeEvent<LeaderLinePath> event) {
		leaderLine.setPath(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("start_label__field")
	void onChangeStartLabel(final ValueChangeEvent<String> event) {
		leaderLine.setStartLabel(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("middle_label__field")
	void onChangeMiddleLabel(final ValueChangeEvent<String> event) {
		leaderLine.setMiddleLabel(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("end_label__field")
	void onChangeEndLabel(final ValueChangeEvent<String> event) {
		leaderLine.setEndLabel(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("start_plug_size__slider")
	void onChangeStartPlugSize(final ValueChangeEvent<Double> event) {
		leaderLine.setStartPlugSize(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("end_plug_size__slider")
	void onChangeEndPlugSize(final ValueChangeEvent<Double> event) {
		leaderLine.setEndPlugSize(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("start_plug_outline__checkbox")
	void onChangeStartPlugOutline(final SelectionEvent<Boolean> event) {
		leaderLine.setStartPlugOutline(event.getValue());
		leaderLine.draw();
	}

	@UiHandler("start_plug_outline_size__slider")
	void onChangeStartPlugOutlineSize(final ValueChangeEvent<Double> event) {
		leaderLine.setStartPlugOutlineSize(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("end_plug_outline__checkbox")
	void onChangeEndPlugOutline(final SelectionEvent<Boolean> event) {
		leaderLine.setEndPlugOutline(event.getValue());
		leaderLine.draw();
	}

	@UiHandler("end_plug_outline_size__slider")
	void onChangeEndPlugOutlineSize(final ValueChangeEvent<Double> event) {
		leaderLine.setEndPlugOutlineSize(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("start_plug__select")
	void onSelectStartPlug(final ValueChangeEvent<PlugType> event) {
		leaderLine.setStartPlug(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("start_plug_socket___select")
	void onSelectStartPlugSocket(final ValueChangeEvent<PlugSocket> event) {
		leaderLine.setStartPlugSocket(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("end_plug__select")
	void onSelectEndPlug(final ValueChangeEvent<PlugType> event) {
		leaderLine.setEndPlug(event.getValue());
		leaderLine.draw();
	}
	
	@UiHandler("end_plug_socket___select")
	void onSelectEndPlugSocket(final ValueChangeEvent<PlugSocket> event) {
		leaderLine.setEndPlugSocket(event.getValue());
		leaderLine.draw();
	}
}
