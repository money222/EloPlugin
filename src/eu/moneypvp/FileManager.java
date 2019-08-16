package eu.moneypvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class FileManager {

	private java.io.File File;
	private FileConfiguration Config;

	
	private java.io.File messagesfile;
	private FileConfiguration messagesConfig;
	
	protected FileManager() {
		messagesfile = new java.io.File("plugins/EloPlugin/messages.yml");
		messagesConfig = (YamlConfiguration.loadConfiguration(messagesfile));
   	 	File = new java.io.File("plugins/EloPlugin/player-data.yml");
		Config = YamlConfiguration.loadConfiguration(File);

	}
	
	 protected void loadConfigs() {
		 if(!File.exists() || !messagesfile.exists()) {
		 Main.instance.saveResource("messages.yml", true);
		 Main.instance.saveResource("player-data.yml", true);
		 Main.instance.saveResource("config.yml", true);
		 Config.options().copyDefaults(true);
		 getMessagesConfig().options().copyDefaults(true);
		 Main.instance.getConfig().options().copyDefaults(true);
		 }
	 }
	 protected void reloadConfig() {
		Config = YamlConfiguration.loadConfiguration(File);
		messagesConfig = (YamlConfiguration.loadConfiguration(messagesfile));
	    }
	 
	    protected FileConfiguration getConfig() {
	    	return Config;
	    }
	    
	    protected FileConfiguration getConfigMessages() {
	    	return getMessagesConfig();
	    }
	    
	    
	    
	    protected void saveMessages(){
	        try{
	        	getMessagesConfig().save(messagesfile);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    
	    
	    protected void saveConfig(){
	        try{
	        	Config.save(File);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

		public FileConfiguration getMessagesConfig() {
			return messagesConfig;
		}
	    

	    }
