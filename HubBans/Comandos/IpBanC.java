package HubBans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HubBans.Main;
import HubBans.API.APIGeral;

public class IpBanC implements CommandExecutor {

	APIGeral api = new APIGeral();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("ipban")) {
			if (!sender.hasPermission("HubBans.ipban")) {
				sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
						+ Main.m.getConfig().getString("SemPermissao").replace("&", "�"));
				return true;
			}
			if (args.length >= 2) {

				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					sb.append(args[i]).append(" ");
				}

				String allArgs = sb.toString().trim();
				Player targeton = Bukkit.getPlayer(args[0]);
				if (targeton != null) {
					if (api.CheckIpBan(targeton.getAddress().getHostName().replace(".", "-")) == true) {
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
								+ Main.m.getConfig().getString("JaBanido").replace("&", "�"));
						return true;
					}
					
					if (targeton.hasPermission("HubBans.Imune.IpBan")){
						sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
								+ Main.m.getConfig().getString("PlayerImune").replace("&", "�"));
						return true;
					}

					api.IpBan(sender.getName(), targeton.getAddress().getHostName(), allArgs, targeton);
					api.addBan();

				} else {

					sender.sendMessage(Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
							+ Main.m.getConfig().getString("PlayerOff").replace("&", "�"));

				}
				return true;

			} else {
				sender.sendMessage((Main.m.getConfig().getString("Prefix").replace("&", "�") + " "
						+ Main.m.getConfig().getString("IpBanI").replace("&", "�")));
			}
		}

		return false;
	}

}
