package com.nascnova.crazymine.Command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandManager implements CommandExecutor {
	private String cmdPrefix;
	private boolean prefixIgnoreCase;
	private Plugin plugin;
	private List<Object> registeredCommands;

	private String invalidCommand;

	public CommandManager(Plugin plugin, String prefix, boolean ignoreCase) {
		this.plugin = plugin;
		this.cmdPrefix = prefix;
		this.prefixIgnoreCase = ignoreCase;
		this.registeredCommands = new ArrayList<Object>();
		this.invalidCommand = "Invalid command.";
	}

	public boolean callMainMethod(CommandSender s)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Object o : this.registeredCommands) {
			Class<? extends Object> cmdclass = o.getClass();
			Command cmdAnno;
			Method[] cmdmethods = cmdclass.getMethods();
			for (Method m : cmdmethods) {
				cmdAnno = m.getAnnotation(Command.class);
				if (cmdAnno != null) {
					if (cmdAnno.main() == true) {
						if (s instanceof ConsoleCommandSender && cmdAnno.onlyPlayer() == true) {
							s.sendMessage(cmdAnno.noPlayerMessage());
							return true;
						}
						if (s instanceof Player && ((Player) s).hasPermission(cmdAnno.perm())
								|| s instanceof ConsoleCommandSender) {
							m.invoke(o, s, null);
							return true;
						} else {
							s.sendMessage(cmdAnno.permMessage());
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean callMethod(String command, CommandSender s, String[] a)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Object o : this.registeredCommands) {
			Class<? extends Object> cmdclass = o.getClass();
			Command cmdAnno;
			Method[] cmdmethods = cmdclass.getMethods();
			for (Method m : cmdmethods) {
				cmdAnno = m.getAnnotation(Command.class);
				if (cmdAnno != null) {
					if (cmdAnno.name().equalsIgnoreCase(a[0])) {
						if (s instanceof ConsoleCommandSender && cmdAnno.onlyPlayer() == true) {
							s.sendMessage(cmdAnno.noPlayerMessage());
							return true;
						}
						if (s instanceof Player && ((Player) s).hasPermission(cmdAnno.perm())
								|| s instanceof ConsoleCommandSender) {
							if (cmdAnno.arglength() == (a.length - 1)) {
								m.invoke(o, s, a);
							} else {
								s.sendMessage(cmdAnno.usage());
							}
						} else {
							s.sendMessage(cmdAnno.permMessage());
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public void registerCommand(Object o) {
		this.registeredCommands.add(o);
	}

	public void registerToBukkit() {
		this.plugin.getCommand(this.cmdPrefix).setExecutor(this);
	}

	public void setInvalidCommandMessage(String message) {
		this.invalidCommand = message;
	}
	
	public String getInvalidCommandMessage() {
		return this.invalidCommand;
	}

	@Override
	public boolean onCommand(CommandSender s, org.bukkit.command.Command c, String label, String[] a) {
		try {
			if (this.prefixIgnoreCase == true) {
				if (c.getName().equalsIgnoreCase(this.cmdPrefix)) {
					return executeCommand(s, a);
				}
			} else {
				if (c.getName().equals(this.cmdPrefix)) {
					return executeCommand(s, a);
				}
			}
			return false;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean executeCommand(CommandSender s, String[] a)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (a.length == 0) { // Call main command
			if (this.callMainMethod(s) == false)
				s.sendMessage(this.invalidCommand);
		} else { // Call sub command
			if (this.callMethod(a[0], s, a) == false)
				s.sendMessage(this.invalidCommand);
		}
		return true;
	}
}
