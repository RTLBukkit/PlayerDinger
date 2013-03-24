package com.taamc.PlayerDinger;

import java.io.IOException;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;


public class PlayerDinger extends JavaPlugin {
	ConfigSound config;
	
	private final SamplePlayerListener playerListener = new SamplePlayerListener(this);

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("playerdingerdebug")) {
			
			if (!(sender instanceof Player)) 						return false;
			if (!sender.hasPermission("playerdinger.debugcommand")) return false;
			if (args.length < 3) 									return false;
			if (args.length > 3) 									return false;
			
			Sound sound = SoundUtil.matchSoundType(args[0]);
			if (sound == null)	return false;
			
		    float pitch 		= 	Float.valueOf(args[1]);
		    float vol		 	= 	Float.valueOf(args[2]);
			((Player)sender).playSound(((Player) sender).getLocation(), sound, vol, pitch);
			
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("playerdingerreload")) {
			boolean doreload = false;
			if (!(sender instanceof Player)){
				doreload = true;
			} else {
				if (sender.hasPermission("playerdinger.reload")) doreload = true;
			}
			
			if (doreload){
				config.load();
				return true;
			}
			return false;
		}
		// If this hasn't happened the a value of false will be returned.
		return false;
	}

	public void onDisable() {
	}

	public void onEnable() {
		
		config = new ConfigSound(this);
		config.load();
		
		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(playerListener, this);

		// EXAMPLE: Custom code, here we just output some info so we can check
		// all is well
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(
				pdfFile.getName() + " version " + pdfFile.getVersion()
						+ " is enabled!");
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
	
	}
}
