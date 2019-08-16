package eu.moneypvp;


import java.util.HashMap;
import java.util.LinkedHashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements EloUtils {
	public static Main instance;
	public static HashMap<String, Integer> unSortedMap = new HashMap<>();
	public static LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

	@Override
	public void onEnable() {
		System.out.println("----------------------");
		System.out.println("EloPlugin enabled!");
		System.out.println("----------------------");
		instance = this;
		getConfig().options().copyDefaults(true);
		fm.loadConfigs();
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		getCommand("elo").setExecutor(new Commands());
		getCommand("topelo").setExecutor(new Commands());
		getCommand("eloadmin").setExecutor(new Commands());

	}
	
	@Override
	public void onDisable() {
		System.out.println("----------------------");
		System.out.println("EloPlugin disabled!");
		System.out.println("----------------------");
		instance = null;
	}
	
	
}
