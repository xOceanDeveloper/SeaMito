package com.seastore.loja.listener;

import com.seastore.loja.Main;
import com.seastore.loja.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class PlayerDeathListener implements Listener {
    private String getAtual = Utils.getSettings().getString("Mito_Atual");


    public PlayerDeathListener(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    void deathMitoEvent(PlayerDeathEvent e) {
        Player killer = e.getEntity().getPlayer().getKiller();
        Player death = e.getEntity().getPlayer();
        if (killer == null || death == null) return;
        Utils.getSettings().set("Mito_Atual", getAtual.equals("nulo") | getAtual.contains(death.getName()) ? killer.getName() :
                killer.getName());
        if (Utils.getSettings().getBoolean("Tab.Ativar")){
            Utils.setPlayerListName(killer, killer.getName() + " "+ Utils.getSettings().getString("Tab.Suffix"));
            Utils.setPlayerListName(killer, Utils.getSettings().getString("Tab.Prefix") + " " + killer.getName());
        }
        Utils.saveConfig();
        if (Utils.getMessages().getBoolean("Mensagens.Title.Ativar")) {

            Utils.sendTitle(killer, Utils.getMessages().getString("Mensagens.Title.Ganhador.Title"),
                    Utils.getMessages().getString("Mensagens.Title.Ganhador.Sub-Title"));

            Utils.sendTitle(death, Utils.getMessages().getString("Mensagens.Title.Perdedor.Title"),
                    Utils.getMessages().getString("Mensagens.Title.Perdedor.Sub-Title"));

            for(Player all : Bukkit.getOnlinePlayers()) {
                Utils.sendTitle(all, Utils.getMessages().getString("Mensagens.Title.Todos.Title"),
                        Utils.getMessages().getString("Mensagens.Title.Todos.Sub-Title"));
            }


            return ;
        } if (Utils.getMessages().getBoolean("Mensagens.Message.Ativar")) {
            Utils.sendMessage(killer, Utils.getMessages().getString("Mensagens.Message.Ganhador"));
            Utils.sendMessage(death, Utils.getMessages().getString("Mensagens.Message.Perdedor"));

            for(Player all : Bukkit.getOnlinePlayers()) {
                Utils.sendMessage(all, Utils.getSettings().getString("Mensagens.Message.Todos"));

            }
            return ;
        }

    }


}
