package com.nascnova.crazymine;

import org.bukkit.plugin.java.JavaPlugin;

import com.nascnova.crazymine.Command.CmdVersion;
import com.nascnova.crazymine.Command.CommandManager;

public class Main extends JavaPlugin {
	public final String version = "1.0.0-SNAPSHOT";
	public static Main plugin;
	private CommandManager cmdManager;
	
	@Override
	public void onEnable() {
		plugin = this;
		this.cmdManager = new CommandManager(this, "cm", true);
		this.cmdManager.registerCommand(new CmdVersion());
		this.cmdManager.registerToBukkit();
		this.getLogger().info("CrazyMine " + version + " is now enabled.");
		this.getLogger().info("Please report bugs on spigot forum!");
		this.getLogger().info("Have fun!");
	}

	@Override
	public void onDisable() {
		this.getLogger().info("CrazyMine " + version + " is now disabled.");
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
