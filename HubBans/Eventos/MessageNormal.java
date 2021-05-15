package HubBans.Eventos;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import HubBans.Main;
import HubBans.API.APIGeral;
import HubBans.Databases.MuteConfig;
import HubBans.Databases.TempMuteConfig;
import HubBans.Utils.Data;
import HubBans.Utils.TimeAPI;

public class MessageNormal implements Listener {
	
	APIGeral api = new APIGeral();

	@EventHandler
	public void chaty(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (api.CheckMute(p.getName()) == true) {
			if (Main.m.getConfig().getBoolean("MySQL.Ativado") == true) {
				List<String> messagesbanlogin = Main.m.getConfig().getStringList("MuteChat");
				String messagebanlogin = "";
				for (String mbanlogin : messagesbanlogin) {
					messagebanlogin += mbanlogin.replace("&", "�").replace("%m", Data.getMuteMotivo(p.getName()));
					messagebanlogin += "\n";
				}
				p.sendMessage(messagebanlogin);
				e.setCancelled(true);
			} else {
				List<String> messagesbanlogin = Main.m.getConfig().getStringList("MuteChat");
				String messagebanlogin = "";
				for (String mbanlogin : messagesbanlogin) {
					messagebanlogin += mbanlogin.replace("&", "�").replace("%m",
							MuteConfig.fc.getString(p.getName() + ".motivo"));
					messagebanlogin += "\n";
				}
				p.sendMessage(messagebanlogin);
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void chaty2(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (api.CheckTempMute(p.getName()) == true) {
			if (Main.m.getConfig().getBoolean("MySQL.Ativado") == true) {
				long endOfBan = Data.gettempmuteTempo(p.getName());
				long now = System.currentTimeMillis();
				long diff = endOfBan - now;
				if (diff <= 0) {
					Data.tempunmute(p.getName());
				} else {
					List<String> messagesbanlogin = Main.m.getConfig().getStringList("TempMuteChat");
					String messagebanlogin = "";
					for (String mbanlogin : messagesbanlogin) {
						messagebanlogin += mbanlogin.replace("&", "�").replace("%b", p.getName())
								.replace("%m", Data.getTempMuteMotivo(p.getName()))
								.replace("%t", String.valueOf(Data.gettempmuteTempo(p.getName())));
						messagebanlogin += "\n";
					}
					p.sendMessage(messagebanlogin);
					e.setCancelled(true);
				}
			} else {
				long endOfBan = Long.valueOf(TempMuteConfig.fc.getString(p.getName() + ".Tempo"));
				long now = System.currentTimeMillis();
				long diff = endOfBan - now;
				if (diff <= 0) {
					TempMuteConfig.fc.set(p.getName() + ".mutado", false);
					TempMuteConfig.Save();
				} else {
					List<String> messagesbanlogin = Main.m.getConfig().getStringList("TempMuteChat");
					String messagebanlogin = "";
					for (String mbanlogin : messagesbanlogin) {
						messagebanlogin += mbanlogin.replace("&", "�").replace("%b", p.getName())
								.replace("%m", TempMuteConfig.fc.getString(p.getName() + ".Motivo"))
								.replace("%t", TimeAPI.getMSG(Long.valueOf(TempMuteConfig.fc.getString(p.getName() + ".Tempo"))));
						messagebanlogin += "\n";
					}
					p.sendMessage(messagebanlogin);
					e.setCancelled(true);
				}
			}
		}
	}
}