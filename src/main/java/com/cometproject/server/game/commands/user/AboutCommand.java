package com.cometproject.server.game.commands.user;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.CometSettings;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.CometManager;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.misc.AdvancedAlertMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.utilities.TimeSpan;

import java.text.NumberFormat;

public class AboutCommand extends ChatCommand {

    @Override
    public void execute(Session client, String message[]) {
        StringBuilder about = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance();

        about.append("Comet Server is a unique Habbo emulator written in Java.<br><br>");

        if(CometSettings.showActiveRoomsInAbout || CometSettings.showActiveRoomsInAbout || CometSettings.showUptimeInAbout || client.getPlayer().getPermissions().hasPermission("about_detailed")) {
            about.append("<b>Server Status</b><br>");

            if (CometSettings.showUsersOnlineInAbout || client.getPlayer().getPermissions().hasPermission("about_detailed"))
                about.append("Users online: " + format.format(Comet.getServer().getNetwork().getSessions().getUsersOnlineCount()) + "<br>");

            if (CometSettings.showActiveRoomsInAbout || client.getPlayer().getPermissions().hasPermission("about_detailed"))
                about.append("Loaded rooms: " + format.format(CometManager.getRooms().getActiveRooms().size()) + "<br>");

            if (CometSettings.showUptimeInAbout || client.getPlayer().getPermissions().hasPermission("about_detailed"))
                about.append("Uptime: " + TimeSpan.millisecondsToDate(System.currentTimeMillis() - Comet.start) + "<br>");
        }

        if (client.getPlayer().getPermissions().hasPermission("about_detailed")) {
            about.append("<br><b>Server Info</b><br>");
            about.append("Allocated memory: " + format.format(((runtime.totalMemory() / 1024) / 1024)) + "MB<br>");
            about.append("Used memory: " + format.format(((runtime.totalMemory() / 1024) / 1024) - ((runtime.freeMemory() / 1024) / 1024)) + "MB<br>");
            about.append("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")<br>");
            about.append("CPU cores:  " + runtime.availableProcessors() + "\n");
        }

        client.send(AdvancedAlertMessageComposer.compose(
                "Comet Server - " + Comet.getBuild(),
                about.toString(),
                CometSettings.hotelName,
                CometSettings.hotelUrl + "?ref=alert_link",
                CometSettings.aboutImg
        ));
    }

    @Override
    public String getPermission() {
        return "about_command";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.about.description");
    }
}
