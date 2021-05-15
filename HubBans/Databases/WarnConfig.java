package HubBans.Databases;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import HubBans.Main;

public class WarnConfig {

	static File f;
	public static FileConfiguration fc;
	static WarnConfig m;

	public static void Create() {
		f = new File(Main.m.getDataFolder() + "/warn.db");
		fc = YamlConfiguration.loadConfiguration(f);
	}

	public static void Save() {
		try {
			fc.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public WarnConfig config() {
		return m;
	}
	

}