package com.taamc.PlayerDinger;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handle events for all Player related events
 * 
 * @author Dinnerbone
 */
public class SamplePlayerListener implements Listener {
	private final PlayerDinger plugin;

	public SamplePlayerListener(PlayerDinger instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		doDing(EventType.JOIN, event.getPlayer());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		doDing(EventType.QUIT, event.getPlayer());
	}

	void doDing(EventType et, Player p) {

		// get Applicable config
		Map maxPriority = null;
		int maxInt = 0;
		String maxperm = null;
		for (Entry ent : plugin.config.advanced.entrySet()) {
			Map map = (Map) ent.getValue();
			boolean enabled = (Boolean) ((Map) map.get(et.getConfig())).get("enabled");
			int priority = (Integer) map.get("priority");
			if (priority > maxInt && enabled) {
				if (p.hasPermission((String) map.get("causePermission"))) {
					maxInt = priority;
					maxPriority = map;
					maxperm = (String) map.get("recievePermission");
				}
			}
		}
		if (maxPriority == null){
			maxPriority = plugin.config.player;
		  maxperm = "playerdinger.ding.receive";
		}

		if (!p.hasPlayedBefore()){
			maxPriority = plugin.config.newPlayer;
			maxperm = "playerdinger.ding.receive";
		}
		if (p.hasPermission("playerdinger.ding.cause")) {
			Player[] plist = plugin.getServer().getOnlinePlayers();
			for (Player victim : plist) {
				if (p.hasPermission(maxperm)
						&& victim.hasPermission("playerdinger.ding.receive")) {
					Map map = (Map) maxPriority.get(et.getConfig());
					victim.playSound(victim.getLocation(),

					SoundUtil.matchSoundType((String) map.get("sound")), (Float) map.get("volume"), (Float) map.get("pitch"));
				}
			}
		}
	}
}
