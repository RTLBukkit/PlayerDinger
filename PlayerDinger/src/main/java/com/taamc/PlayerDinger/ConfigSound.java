package com.taamc.PlayerDinger;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;
 
 
public class ConfigSound extends Config {
	public HashMap<String, Map> player = new HashMap<String, Map>(); 
	public HashMap<String, Map> newPlayer = new HashMap<String, Map>();
	public HashMap<String, Map> advanced = new HashMap<String, Map>();
	{
		
	HashMap<String, Object> pjoinmap = quickMap(true,"LEVEL_UP",0.5f,2.0f);
	HashMap<String, Object> pquitmap = quickMap(false,"ORB_PICKUP",0.5f,2.0f);
  player.put("join", pjoinmap);
  player.put("quit", pquitmap);
  
  HashMap<String, Object> newpjoinmap = quickMap(false,"CAT_PURREOW",0.5f,2.0f);
	HashMap<String, Object> newquitmap = quickMap(false,"BAT_HURT",0.5f,2.0f);
  newPlayer.put("join", newpjoinmap);
  newPlayer.put("quit", newquitmap);
  
  HashMap<String, Object> exampleadmin = new HashMap<String, Object>();
  advanced.put("exampleadmin", exampleadmin);
  
  exampleadmin.put("causePermission", "yourserver.admins");
  exampleadmin.put("recievePermission", "yourserver.moderators");
  exampleadmin.put("priority", 100);
  HashMap<String, Object> exadminjoin = quickMap(true,"ANVIL_LAND",2f,2.0f);
	HashMap<String, Object> exadminquit = quickMap(false,"BURP",0.5f,2.0f);
  exampleadmin.put("join", exadminjoin);
  exampleadmin.put("quit", exadminquit);
	}
	
	
    public ConfigSound(Plugin plugin) {
        this.setFile(plugin);
    }
 
    private HashMap<String, Object> quickMap(boolean enabled, String sound, float pitch, float volume){
   	 HashMap<String, Object> quickmap = new HashMap<String, Object>();
      quickmap.put("enabled", enabled);
      quickmap.put("sound", sound);
      quickmap.put("pitch", pitch);
      quickmap.put("volume", volume);
      return quickmap;}
    
    


}