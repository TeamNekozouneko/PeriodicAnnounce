package com.nekozouneko.periodicAnnounce.tasks

import com.nekozouneko.periodicAnnounce.PeriodicAnnounce
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.function.Consumer

abstract class TaskManager {
    abstract fun run()
    fun repeatingTask(plugin: JavaPlugin, delay: Long, period: Long){
        if(!PeriodicAnnounce.isFolia){
            Bukkit.getScheduler().runTaskTimer(plugin, Runnable { this.run() }, delay, period)
        }else{
            Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, Consumer { this.run() }, Math.max(delay, 1), period)
        }
    }
}