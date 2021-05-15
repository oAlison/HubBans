package HubBans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HubBans.Main;
import HubBans.API.APIGeral;
import HubBans.Eventos.BanEvent;

public class BanC implements CommandExecutor {
	
	APIGeral api = new APIGeral();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("ban")) {
			if (!sender.hasPermission("HubBans.ban")) {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
						+ Main.m.getConfig().getString("SemPermissao").replace("&", "§"));
				return true;
			}
			if (args.length >= 2) {

				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					sb.append(args[i]).append(" ");
				}

				String allArgs = sb.toString().trim();
				Player targeton = Bukkit.getPlayer(args[0]);
				OfflinePlayer targetoff = Bukkit.getOfflinePlayer(args[0]);
				if (targeton != null) {
					if (api.CheckBan(targeton.getName()) == true) {
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
								+ Main.m.getConfig().getString("JaBanido").replace("&", "§"));
						return true;
					}
					if (targeton.hasPermission("HubBans.Imune.Ban")){
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
								+ Main.m.getConfig().getString("PlayerImune").replace("&", "§"));
						return true;
					}

					api.Ban(sender.getName(), targeton, allArgs, true, targeton.getName());
					api.addBan();
					BanEvent event = new BanEvent(sender.getName(), allArgs, targeton.getName());
					Bukkit.getServer().getPluginManager().callEvent(event);

				} else {
					if (api.CheckBan(targetoff.getName()) == true) {
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
								+ Main.m.getConfig().getString("JaBanido").replace("&", "§"));
						return true;
					}

					api.Ban(sender.getName(), targetoff.getPlayer(), allArgs, false, args[0]);
					api.addBan();
					BanEvent event = new BanEvent(sender.getName(), allArgs, targetoff.getName());
					Bukkit.getServer().getPluginManager().callEvent(event);

				}
				return true;

			} else {
				sender.sendMessage((Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
						+ Main.m.getConfig().getString("BanI").replace("&", "§")));
			}
		}

		return false;
	}

}