package gwt.material.design.components.demo.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.makesystem.pidgey.console.Console;

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

		Console.log("Current Locale: {dt}", new Date());
		Console.log("Current Locale: {s}", LocaleInfo.getCurrentLocale().getLocaleName());

	}

}
