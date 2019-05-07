package gwt.material.design.components.demo.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface IResources extends ClientBundle {
	public static final IResources INSTANCE = GWT.create(IResources.class);

	// ////////////////////////////////////////
	// Images
	// ////////////////////////////////////////

	@Source("images/logo.png")
	ImageResource logo();

	@Source("images/wallpaper.jpg")
	ImageResource wallpaper();

	@Source("images/img_1.png")
	ImageResource img1();

	@Source("images/img_2.png")
	ImageResource img2();

	@Source("images/img_3.png")
	ImageResource img3();

	@Source("images/img_4.png")
	ImageResource img4();

	// ////////////////////////////////////////
	// Themes
	// ////////////////////////////////////////
	@Source("css/theme_black_lime.css")
	TextResource theme();

	// ////////////////////////////////////////
	// Icons
	// ////////////////////////////////////////
	@Source("icons/ic_component_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_component();
	
	@Source("icons/ic_typography_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_typography();

	@Source("icons/ic_responsive_layout_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_layout_grid();

	@Source("icons/ic_card_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_grid_list();

	@Source("icons/ic_toolbar_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_toolbar();

	@Source("icons/ic_button_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_button();

	@Source("icons/ic_text_field_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_inputs();

	@Source("icons/ic_theme_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_theme();

	@Source("icons/ic_dialog_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_dialog();
	
	@Source("icons/ic_list_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_list();
	
	@Source("icons/ic_menu_24px.svg")
	@MimeType("image/svg+xml")
	TextResource ic_menu();

}
