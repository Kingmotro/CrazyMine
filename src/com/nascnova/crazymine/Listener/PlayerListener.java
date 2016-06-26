package com.nascnova.crazymine.Listener;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener {
	private HashMap<String, SelectedPoints> playerSelected;

	public PlayerListener() {
		this.playerSelected = new HashMap<String, SelectedPoints>();
	}
	
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent e) {
		if (e.isCancelled() == true) return;
		if (e.getClickedBlock() == null) return;

	}

	class SelectedPoints {
		Location point1;
		Location point2;
	}
}
