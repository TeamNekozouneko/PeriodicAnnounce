package com.nekozouneko.periodicAnnounce.tasks

import com.nekozouneko.periodicAnnounce.PeriodicAnnounce
import net.kyori.adventure.text.Component

class PeriodicMessageTask : TaskManager() {
    override fun run() {
        val messages = PeriodicAnnounce.instance.config.getStringList("messages")
        if(messages.isEmpty()) return
        val randomMessage = messages.random()
        PeriodicAnnounce.instance.server.onlinePlayers.forEach{ player ->
            player.sendMessage(Component.text(randomMessage))
        }
    }
}