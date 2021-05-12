package com.seastore.loja.commands;

import com.seastore.loja.Main;
import com.seastore.loja.utils.Utils;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandMito implements CommandExecutor  {

    private Player t;
    private Player p;

    public CommandMito(Main plugin){
        plugin.getCommand("mito").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player))return true;
        p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("mito")) {
            if (args.length == 0) {
                if (Utils.getSettings().getString("Mito_Atual").contains("nulo")){
                    p.sendMessage(Utils.getMessages().getString("Mensagens.Mito_Atual")
                            .replace('&', '§')
                            .replace("${mito}", "Ninguém")

                    );
                    return true;
                }
                p.sendMessage(Utils.getMessages().getString("Mensagens.Mito_Atual")
                        .replace('&', '§')
                        .replace("${mito}", Utils.getSettings().getString("Mito_Atual"))
                );
                return true;

            }
            if(args[0].equalsIgnoreCase("remover")){
                if (args.length == 1){
                    p.sendMessage("§eUtilize §b/mito ajuda");
                    return true;
                }
                t = Bukkit.getPlayer(args[1]);
                if (t == null){
                    p.sendMessage(Utils.getMessages().getString("Mensagens.Jogador_Inexistente")
                            .replace('&', '§')
                    );

                }else {
                    if (p.hasPermission("seamito.setarmito.admin")) {
                        if (!Utils.getSettings().getString("Mito_Atual").contains(t.getName())) {
                            p.sendMessage(Utils.getMessages().getString("Mensagens.Jogador_Ja_Removido")
                                    .replace('&', '§')
                                    .replace("${target}", t.getName())
                            );
                            return true;
                        }
                        Utils.getSettings().set("Mito_Atual", "nulo");
                        Utils.saveConfig();
                        p.sendMessage("§eMito Atual removido com sucesso!");
                        return true;
                    }
                }
                }


            if (args[0].equalsIgnoreCase("setar")) {

                if (args.length == 1) { // se o argumento for so igual a 1 /mito setar
                    p.sendMessage("§eUtilize §b/mito ajuda");
                    return true;
                }
                t = Bukkit.getPlayer(args[1]); // `t` recebe player no argumento1 = /mito setar PlayerFulano
                if (t == null) {
                    p.sendMessage(Utils.getMessages().getString("Mensagens.Jogador_Inexistente")
                            .replace('&', '§')
                    );

                } else {
                    if (p.hasPermission("seamito.setarmito.admin")) {
                        if (Utils.getSettings().getString("Mito_Atual").contains(t.getName())) {
                            p.sendMessage(Utils.getMessages().getString("Mensagens.Jogador_Ja_Setado")
                                    .replace('&', '§')
                                    .replace("${target}", Utils.getSettings().getString("Mito_Atual"))
                            );
                            return true;
                        }
                        Utils.getSettings().set("Mito_Atual", t.getName());
                        Utils.saveConfig();
                        p.sendMessage("§eMito Atual setado com sucesso!");

                        return true;
                    }
                }
            }
            if (args[0].equalsIgnoreCase("ajuda")){
                if (p.hasPermission("seamito.ajuda.admin")){
                    List<String> list_admin = Utils.getMessages().getStringList("Mensagens.Ajuda_Admin");
                    for(String s : list_admin){
                        p.sendMessage(s
                                .replace('&', '§')
                        );
                    }
                    return true ;
                }
                    List<String> list = Utils.getMessages().getStringList("Ajuda_Membro");
                    for(String s : list){
                        p.sendMessage(
                                s.replace('&', '§')
                        );
                    }
                    return true ;
                }

            }
        return false;
        }




    }



