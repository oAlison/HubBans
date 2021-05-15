package HubBans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HubBans.Main;
import HubBans.API.APIGeral;

public class UnwarnC implements CommandExecutor {
	
	APIGeral api = new APIGeral();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("unwarn")) {
			if (!sender.hasPermission("HubBans.unwarn")) {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
						+ Main.m.getConfig().getString("SemPermissao").replace("&", "§"));
				return true;
			}

			if (args.length != 2) {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
						+ Main.m.getConfig().getString("UnwarnI").replace("&", "§"));
				return true;
			}

			Player t = Bukkit.getPlayer(args[0]);
			if (args.length >= 1) {
				if (t != null) {
					if (args[1].equalsIgnoreCase("um")) {
						api.Unwarn(t, "Um", sender);
					} else if (args[1].equalsIgnoreCase("todos")) {
						if (api.getWarns(t) != 0) {
							api.Unwarn(t, "Todos", sender);
						} else {
							sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
									+ Main.m.getConfig().getString("UnwarnM").replace("&", "§"));
						}
					} else {
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
								+ Main.m.getConfig().getString("UnwarnI").replace("&", "§"));
					}
				}
				return true;
			} else {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "§") + " "
						+ Main.m.getConfig().getString("UnwarnI").replace("&", "§"));
			}
		}
		return false;
	}

}
