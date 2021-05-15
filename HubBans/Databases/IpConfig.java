package HubBans.Databases;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import HubBans.Main;

public class IpConfig {

	static File f;
	public static FileConfiguration fc;
	static IpConfig m;

	public static void create() {
		f = new File(Main.m.getDataFolder() + "/dupeip.db");
		fc = YamlConfiguration.loadConfiguration(f);
	}

	public static void SaveConfig() {
		try {
			fc.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IpConfig config() {
		return m;
	}
	

}
