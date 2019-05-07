package gwt.material.design.components.demo.client.app.components.banner;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialBanner;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.demo.client.app.base.DemoPanel;

public class BannerDemo extends Composite implements DemoPanel<MaterialBanner> {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, BannerDemo> {
	}

	public BannerDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		getWidgetForTests();
	}

	@Override
	public Set<MaterialBanner> getWidgetForTests() {
		final Set<MaterialBanner> banners = DOMHelper.findByClass(MaterialBanner.class, getWidget());
		return banners;
	}

}
