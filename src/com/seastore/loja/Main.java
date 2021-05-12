package com.seastore.loja;

import com.seastore.loja.commands.CommandMito;
import com.seastore.loja.listener.ChatManager;
import com.seastore.loja.listener.PlayerDeathListener;
import com.seastore.loja.utils.Config;
import com.seastore.loja.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    private Config settings;
    private Config mensagens;
    private String prefix_loja = "§b[SeaMito] ";

    public Config getSettings() {
        return settings;
    }

    public Config getMensagens(){
        return mensagens;
    }

    @Override
    public void onEnable() {
        try {
            mensagens = new Config(this, "messages.yml");
            mensagens.getConfig().options().copyDefaults(false);
            mensagens.saveDefaultConfig();


            settings = new Config(this, "settings.yml");
            settings.getConfig().options().copyDefaults(false);
            settings.saveDefaultConfig();
        }catch(Exception e){
            System.out.println(prefix_loja +"§cHouve um erro ao carregar os arquivos YAML!");
        }
        try {
            new CommandMito(this);
            new PlayerDeathListener(this);
            new ChatManager(this);
            System.out.println(prefix_loja + "Comandos Carregados com sucesso!");
        }catch (Exception e){
            System.out.println(prefix_loja + "§cHouve um erro ao carregar os comandos/eventos!");
        }
        if (settings.getConfig().getString("Mito_Atual").equals("")) Utils.getSettings().set("Mito_Atual", "nulo");
        System.out.println(prefix_loja + "§ePlugin ativado com sucesso!");

    }

    @Override
    public void onLoad() {
        if (settings.getConfig().getString("Mito_Atual").equals("")) Utils.getSettings().set("Mito_Atual", "nulo");
    }

    @Override
    public void onDisable() {
        System.out.println(prefix_loja + "§cPlugin desativado com sucesso");

    }
}
