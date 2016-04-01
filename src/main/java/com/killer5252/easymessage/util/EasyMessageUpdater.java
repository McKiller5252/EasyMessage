package com.killer5252.easymessage.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.killer5252.easymessage.EasyMessage;

public class EasyMessageUpdater extends Thread {

	public EasyMessageUpdater() {
		start();
	}

	private static String version;
	private static boolean update;

	@Override
	public void run() {
		ArrayList<String> data = new ArrayList<String>();
		try {
			URL remoteFile = new URL("");
			BufferedReader reader = new BufferedReader(new InputStreamReader(remoteFile.openStream()));
			data.add(reader.readLine());
		} catch (Exception e) {
			EasyMessage.instance().getLogger().warn("An error has occured while checking updates.");
		}
		String[] modVersion = EasyMessage.VERSION.replaceAll("[^0-9.]", "").split("[.]");
		String[] latestVersion = data.get(0).split("[.]");
		if (Integer.valueOf(modVersion[0]) < Integer.valueOf(latestVersion[0])) {
			version = data.get(0);
			update = true;
			EasyMessage.instance().getLogger().info("A New version available " + version);
		} else if (Integer.valueOf(modVersion[0]) > Integer.valueOf(latestVersion[0])) {
			return;
		} else if (Integer.valueOf(modVersion[1]) < Integer.valueOf(latestVersion[1])) {
			version = data.get(0);
			update = true;
			EasyMessage.instance().getLogger().info("A New version available " + version);
		} else if (Integer.valueOf(modVersion[1]) > Integer.valueOf(latestVersion[1])) {
			return;
		} else if (Integer.valueOf(modVersion[2]) < Integer.valueOf(latestVersion[2])) {
			version = data.get(0);
			update = true;
			EasyMessage.instance().getLogger().info("A New version available " + version);
		} else if (Integer.valueOf(modVersion[2]) > Integer.valueOf(latestVersion[2])) {
			return;
		}
	}

	public static boolean isUpdateAvailable() {
		return update;
	}

	public static String getVersionString() {
		return version;
	}
}
