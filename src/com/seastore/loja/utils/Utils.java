package com.seastore.loja.utils;

import com.seastore.loja.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public  abstract  class Utils {


    protected static FileConfiguration cf = Main.getPlugin(Main.class).getSettings().getConfig();
    protected static FileConfiguration msg = Main.getPlugin(Main.class).getMensagens().getConfig();

    public static FileConfiguration getSettings() {
        return cf;
    }
    public static FileConfiguration getMessages(){
        return msg;
    }

    public static void saveConfig(){
        try{
            getSettings().save(Main.getPlugin(Main.class).getDataFolder() + File.separator + getSettings().getName());
        }catch (IOException e){
            e.printStackTrace();

        }
    }

    public static void sendMessage(Player p, String s){
        p.sendMessage(s
                .replace('&', '§')
                .replace("${novo_mito}", Utils.getSettings().getString("Mito_Atual"))
                .replace("<nl>", "\n"));
    }
    public static void sendTitle(Player p, String s, String sub){
        p.sendTitle(
                s
                        .replace('&', '§')
                        .replace("${novo_mito}", Utils.getSettings().getString("Mito_Atual"))
                        .replace("<nl>", "\n")
                        .replace('&', '§')
                        .replace("<nl>", "\n"),
                sub
                        .replace('&', '§')
                        .replace("${novo_mito}", Utils.getSettings().getString("Mito_Atual"))
                        .replace("<nl>", "\n")
                        .replace('&', '§')
                        .replace("<nl>", "\n"
                        ));

    }
    public static void setPlayerListName(Player p, String s){
        p.setPlayerListName(s
                .replace('&', '§')
                .replace("${Tag_Mito}", Utils.getSettings().getString("Tag_Mito"))
        );
    }



}
