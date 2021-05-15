package HubBans.Databases;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import HubBans.Main;

public class TempBanConfig {

	static File f;
	public static FileConfiguration fc;
	static TempBanConfig m;

	public static void create() {
		f = new File(Main.m.getDataFolder() + "/tempban.db");
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

	public TempBanConfig config() {
		return m;
	}
	

}
