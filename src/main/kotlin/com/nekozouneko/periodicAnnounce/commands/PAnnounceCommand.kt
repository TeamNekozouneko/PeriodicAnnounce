package com.nekozouneko.periodicAnnounce.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class PAnnounceCommand : CommandExecutor, TabExecutor {
    interface SubCommand{
        fun handle(sender: CommandSender) {}
    }
    private val subCommands: Map<String, SubCommand> = mapOf(
        "reload" to ReloadCommand()
    )
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if(p3 == null || p3.isEmpty() || !subCommands.keys.contains(p3[0])){
            showHelp(p0)
            return true
        }
        subCommands[p3[0]]?.handle(p0)
        return true
    }

    private fun showHelp(sender: CommandSender){
        sender.sendMessage("§c使用方法：/pannounce reload")
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>?
    ): MutableList<String>? {
        if(p3?.size == 1) return mutableListOf("reload")
        return mutableListOf()
    }
}