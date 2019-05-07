package gwt.material.design.components.demo.client.themeEditor.misc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ExtrasColumn extends Composite {

	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	interface HomeViewUiBinder extends UiBinder<Widget, ExtrasColumn> {
	}

	public ExtrasColumn() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
}
