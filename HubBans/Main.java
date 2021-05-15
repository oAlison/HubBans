package HubBans;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import HubBans.API.APIGeral;
import HubBans.Comandos.BanC;
import HubBans.Comandos.DupeipC;
import HubBans.Comandos.IpBanC;
import HubBans.Comandos.IpUnbanC;
import HubBans.Comandos.KickC;
import HubBans.Comandos.MuteC;
import HubBans.Comandos.PunirC;
import HubBans.Comandos.ReportarC;
import HubBans.Comandos.ReportesC;
import HubBans.Comandos.TempBanC;
import HubBans.Comandos.TempMuteC;
import HubBans.Comandos.UnbanC;
import HubBans.Comandos.UnmuteC;
import HubBans.Comandos.UnwarnC;
import HubBans.Comandos.VerificarC;
import HubBans.Comandos.WarnC;
import HubBans.Databases.BanConfig;
import HubBans.Databases.IpBanConfig;
import HubBans.Databases.IpBanConfig2;
import HubBans.Databases.IpConfig;
import HubBans.Databases.MotivoReportConfig;
import HubBans.Databases.MuteConfig;
import HubBans.Databases.ReportesConfig;
import HubBans.Databases.StatusConfig;
import HubBans.Databases.TempBanConfig;
import HubBans.Databases.TempMuteConfig;
import HubBans.Databases.WarnConfig;
import HubBans.Eventos.InventoryClick;
import HubBans.Eventos.JoinBan;
import HubBans.Eventos.MessageNormal;
import HubBans.Utils.Data;
import net.dv8tion.jda.api.JDA;

public class Main extends JavaPlugin {

	public static Main m;
	public static JDA jda;

	public void onEnable() {
		Data.ConnectDiscord();
		Data.Connect();
		
		m = this;
		saveDefaultConfig();
		
		BanConfig.create();
		BanConfig.SaveConfig();
		IpBanConfig.create();
		IpBanConfig.SaveConfig();
		IpBanConfig2.create();
		IpBanConfig2.SaveConfig();
		ReportesConfig.create();
		ReportesConfig.SaveConfig();
		MotivoReportConfig.create();
		MotivoReportConfig.SaveConfig();
		MuteConfig.Create();
		MuteConfig.Save();
		WarnConfig.Create();
		WarnConfig.Save();
		TempMuteConfig.Create();
		TempMuteConfig.Save();
		TempBanConfig.create();
		TempBanConfig.SaveConfig();
		StatusConfig.Create();
		StatusConfig.Save();
		IpConfig.create();
		IpConfig.SaveConfig();
		
		Bukkit.getPluginManager().registerEvents(new MessageNormal(), this);
		Bukkit.getPluginManager().registerEvents(new JoinBan(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		getCommand("ban").setExecutor(new BanC());
		getCommand("ipban").setExecutor(new IpBanC());
		getCommand("unban").setExecutor(new UnbanC());
		getCommand("ipunban").setExecutor(new IpUnbanC());
		getCommand("mute").setExecutor(new MuteC());
		getCommand("mutar").setExecutor(new MuteC());
		getCommand("unmute").setExecutor(new UnmuteC());
		getCommand("unwarn").setExecutor(new UnwarnC());
		getCommand("kick").setExecutor(new KickC());
		getCommand("tempban").setExecutor(new TempBanC());
		getCommand("tempmute").setExecutor(new TempMuteC());
		getCommand("warn").setExecutor(new WarnC());
		getCommand("reportes").setExecutor(new ReportesC());
		getCommand("reportar").setExecutor(new ReportarC());
		getCommand("verificar").setExecutor(new VerificarC());
		getCommand("punir").setExecutor(new PunirC());
		getCommand("dupeip").setExecutor(new DupeipC());
		
		new APIGeral().startRunnable();
	}
}
