package dev.martinl.bsbrewritten.util;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

@Data @RequiredArgsConstructor
public class ConfigurationParser {
    private final FileConfiguration fileConfiguration;


    private Sound openSound;
    private Sound closeSound;
    private int cooldown;
    private boolean requiresPermission;
    private boolean enableReadOnly;
    private boolean enableRightClickOpen;
    private boolean enableInventoryClickOpen;

    private String prefix;
    private String inventoryName;
    private String openMessage;
    private String closeMessage;
    private String noPermissionMessage;
    private String cooldownMessage;



    public void parseConfiguration() {
        openSound = Sound.valueOf(fileConfiguration.getString("open_sound"));
        closeSound = Sound.valueOf(fileConfiguration.getString("close_sound"));
        cooldown = fileConfiguration.getInt("cooldown");
        requiresPermission = fileConfiguration.getBoolean("requires_permission");
        enableReadOnly = fileConfiguration.getBoolean("enable_read_only");
        enableRightClickOpen = fileConfiguration.getBoolean("enable_right_click_open");
        enableInventoryClickOpen = fileConfiguration.getBoolean("enable_inventory_click_open");

        prefix = translateCC(strFromConfig("prefix"));
        prefix = (prefix.isEmpty() ? "" : prefix + ChatColor.RESET + " ");
        openMessage = strFromConfig("open_message");
        closeMessage = strFromConfig("close_message");
        inventoryName = translateCC(strFromConfig("inventory_name"));
        noPermissionMessage = translateCC(strFromConfig("no_permission_message"));
        cooldownMessage = translateCC(strFromConfig("cooldown_message"));
    }

    private String strFromConfig(String path) {
        return translateCC(fileConfiguration.getString(path));
    }

    private String translateCC(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}