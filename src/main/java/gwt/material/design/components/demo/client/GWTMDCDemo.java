package gwt.material.design.components.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.RootPanel;
import com.makesystem.pidgey.console.Console;
import com.makesystem.pidgey.console.ConsoleColor;
import com.makesystem.pidgey.io.net.IpAddressGWT;

import gwt.material.design.components.client.theme.MaterialThemes;
import gwt.material.design.components.client.theme.ThemeManager;
import gwt.material.design.components.demo.client.app.DemoApp;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTMDCDemo implements EntryPoint {
	
	public void onModuleLoad() {
		
		// GWT.log("IP: " + publicIp());
		try {
			final IpAddressGWT ip = new IpAddressGWT();
			final String publicIp = ip.getPublic();
			Console.log("IP: {cc}{s}", ConsoleColor.BLUE, publicIp);
		} catch (Throwable e) {
			Console.log("IP: {cc}{s}", ConsoleColor.RED, e.getMessage());
		}
		
		Console.log("Current Locale: {cc}{s}", ConsoleColor.BLUE, LocaleInfo.getCurrentLocale().getLocaleName());
		
		RootPanel.get().add(new DemoApp());
		ThemeManager.applyTheme(MaterialThemes.INSTANCE.dark__ligth_blue());
		// ThemeManager.applyTheme(IResources.INSTANCE.theme());
		
		
		
	}
}
