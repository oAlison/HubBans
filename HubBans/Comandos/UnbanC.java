package HubBans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import HubBans.Main;
import HubBans.API.APIGeral;

public class UnbanC implements CommandExecutor {
	
	APIGeral api = new APIGeral();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("HubBans.unban")) {
			sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
					+ Main.m.getConfig().getString("SemPermissao"));
			return true;
		}

		if (args.length >= 1) {
			OfflinePlayer toff = Bukkit.getOfflinePlayer(args[0]);
			api.Unban(toff.getName(), sender);
		}
		return false;

	}

}
