package com.nekozouneko.periodicAnnounce.commands

import com.nekozouneko.periodicAnnounce.PeriodicAnnounce
import org.bukkit.command.CommandSender

class ReloadCommand : PAnnounceCommand.SubCommand {
    override fun handle(sender: CommandSender) {
        val reloadTime = kotlin.system.measureTimeMillis {
            PeriodicAnnounce.instance.reloadConfig()
            val interval = PeriodicAnnounce.instance.config.getInt("interval", 300)
            PeriodicAnnounce.periodicMessageTask.repeatingTask(PeriodicAnnounce.instance, 0, interval * 20L)
        }
        sender.sendMessage("§aコンフィグを再読み込みしました！(${reloadTime}ms)")
    }
}