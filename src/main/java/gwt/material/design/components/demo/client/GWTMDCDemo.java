package gwt.material.design.components.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.theme.MaterialThemes;
import gwt.material.design.components.client.theme.ThemeManager;
import gwt.material.design.components.demo.client.app.DemoApp;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTMDCDemo implements EntryPoint {

	public void onModuleLoad() {

		// GWT.log("IP: " + publicIp());

		// RootPanel.get().add(new DemoApp());
		// ThemeManager.applyTheme(IResources.INSTANCE.theme());
		// ThemeManager.applyTheme(MaterialThemes.INSTANCE.dark_amber());

		GWT.log("Current Locale: " + LocaleInfo.getCurrentLocale().getLocaleName());

		final String api = "ajax.googleapis.com/ajax/libs/jquery";
		final String src = "https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js";

		GWT.log("Has: " + hasScript(api));
		injectScript(src);
		GWT.log("Has: " + hasScript(api));
		
		GWT.log("IP: " + publicIp());
		
		removeScript(src);
		GWT.log("Has: " + hasScript(api));
	}

	native boolean hasScript(final String src)/*-{
		return document.querySelector('script[src*="' + src + '"]') ? true
				: false;
	}-*/;

	native void injectScript(final String src)/*-{
		var head = document.getElementsByTagName('head')[0];
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = src;
		head.appendChild(script);
	}-*/;

	native void removeScript(final String src)/*-{
		var script = document.querySelector('script[src*="' + src + '"]');
		if (script)
			script.remove();
	}-*/;

	native String publicIp()/*-{
		var url = "http://checkip.amazonaws.com";
		var public_ip = null;

		$wnd.jQuery.ajax({
			url : url,
			type: 'GET',
			async: false,
			dataType: 'TEXT',
			success : function(result) {
				public_ip = result;
			}
		});

		return public_ip;
	}-*/;

}
