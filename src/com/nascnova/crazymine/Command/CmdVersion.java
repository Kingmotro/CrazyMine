package com.nascnova.crazymine.Command;

import org.bukkit.command.CommandSender;

import com.nascnova.crazymine.Main;

import org.bukkit.*;

public class CmdVersion {
	@Command(name="version",arglength=0, perm="cm.version", opBypass=true, onlyPlayer=true, main=true)
	public void onCmdVersion(CommandSender s, String[] a) {
		s.sendMessage(ChatColor.GREEN + "CrazyMine " + Main.getPlugin().version + " By NascentNova");
		s.sendMessage(ChatColor.GREEN + "Please report bugs on spigot forum");
	}
	@Command(name="test",arglength=0, perm="cm.test", opBypass=true, onlyPlayer=false)
	public void a(CommandSender s, String[] a) {
		s.sendMessage(ChatColor.GREEN + "test success!");
	}
}
