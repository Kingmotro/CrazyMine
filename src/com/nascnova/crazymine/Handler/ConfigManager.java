package com.nascnova.crazymine.Handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.plugin.Plugin;

import com.nascnova.crazymine.Util.YmlConfiguration;

public class ConfigManager {
	private Plugin plugin;
	private YmlConfiguration yml_Config;
	public ConfigManager(Plugin plugin) {
		this.plugin = plugin;
	}
	public void initCrazyConfig() {
		YmlConfiguration.createFolderIfNotExist(plugin, "language");
	}
	private void releaseResource(String fileName, String saveTo) {
		try {
			File saveFile = new File(plugin.getDataFolder(), saveTo);
			InputStream in = plugin.getResource(fileName);
			byte[] fileBytes = new byte[in.available()];

			in.read(fileBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
