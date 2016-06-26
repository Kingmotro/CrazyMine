package com.nascnova.crazymine.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class YmlConfiguration {
	private File ymlFile;
	private FileConfiguration ymlConfig;

	public YmlConfiguration(Plugin plugin, String fileName) {
		try {
			ymlFile = new File(plugin.getDataFolder(), fileName);
			if (!ymlFile.exists()) ymlFile.createNewFile();
			ymlConfig = new YamlConfiguration();
			ymlConfig.load(ymlFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getConfig() {
		return ymlConfig;
	}
	
	public void save() {
		try {
			this.ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createFolderIfNotExist(Plugin plugin, String name) {
		File folder = new File(plugin.getDataFolder(), name);
		if (!folder.exists()) folder.mkdirs();
	}
}
