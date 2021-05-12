package com.seastore.loja.listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import com.seastore.loja.Main;
import com.seastore.loja.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatManager implements Listener {

    public ChatManager(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    void verifyTag(ChatMessageEvent e){
        Player p = e.getSender();
        if (Utils.getSettings().getString("Mito_Atual").contains(p.getName())){
            if (e.getTags().contains("sea_mito")){
                e.setTagValue("sea_mito", Utils.getSettings().getString("Tag_Mito")
                        .replace('&', '§')
                );
            }
        }

    }
}
