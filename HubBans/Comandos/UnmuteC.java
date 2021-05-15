package HubBans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HubBans.Main;
import HubBans.API.APIGeral;

public class UnmuteC implements CommandExecutor {
	
	APIGeral api = new APIGeral();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("HubBans.unmute")) {
			sender.sendMessage(Main.m.getConfig().getString(Main.m.getConfig().getString("Prefix").replace("&", "§")
					+ " " + Main.m.getConfig().getString("SemPermissao")));
			return true;
		}
		if (args.length == 1) {
			Player tn = Bukkit.getPlayer(args[0]);
			OfflinePlayer tff = Bukkit.getOfflinePlayer(args[0]);
			if (tn != null) {
				api.Unmute(tn.getName(), sender);
			} else {
				api.Unmute(tff.getName(), sender);
			}
		} else {
			sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§")
							+ " " + Main.m.getConfig().getString("UnmuteI").replace("&", "§"));
		}
		return false;

	}

}
