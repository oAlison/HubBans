package HubBans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HubBans.Main;
import HubBans.API.APIGeral;
import HubBans.Utils.TimeAPI;

public class TempMuteC implements CommandExecutor {
	
	APIGeral api = new APIGeral();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		StringBuilder sb = new StringBuilder();
		for (int i = 3; i < args.length; i++) {
			sb.append(args[i]).append(" ");
		}

		String allArgs = sb.toString().trim();
		if (label.equalsIgnoreCase("tempmute")) {
			if (!sender.hasPermission("HubBans.TempMute")) {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
						+ Main.m.getConfig().getString("SemPermissao").replace("&", "�"));
				return true;
			}
			if (args.length >= 4) {
				Player t = Bukkit.getPlayer(args[0]);
				if (t != null) {

					if (api.CheckTempMute(t.getName()) == true) {
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
								+ Main.m.getConfig().getString("JaMute").replace("&", "�"));
						return true;
					}
					
					if (t.hasPermission("HubBans.Imune.TempMute")){
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
								+ Main.m.getConfig().getString("PlayerImune").replace("&", "�"));
						return true;
					}
					long endOfBan = System.currentTimeMillis() + TimeAPI.getTicks(args[2], Integer.parseInt(args[1]));

					long now = System.currentTimeMillis();
					long diff = endOfBan - now;

					if (diff > 0) {
						api.TempMute(sender.getName(), t.getName(), allArgs, endOfBan);
						api.addMute();
					}

				} else {
					sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
							+ Main.m.getConfig().getString("PlayerOff").replace("&", "�"));
				}
				return true;
			} else {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
						+ Main.m.getConfig().getString("TempMuteI").replace("&", "�"));
			}
		}
		return false;
	}

}
