package com.nekozouneko.periodicAnnounce.tasks

import com.nekozouneko.periodicAnnounce.PeriodicAnnounce
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import java.util.function.Consumer

abstract class TaskManager {
    lateinit var currentBukkitTask: BukkitTask
    lateinit var currentScheduledTask: ScheduledTask
    abstract fun run()
    fun repeatingTask(plugin: JavaPlugin, delay: Long, period: Long){
        cancelTasks()
        if(!PeriodicAnnounce.isFolia){
            currentBukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, Runnable { this.run() }, delay, period)
        }else{
            currentScheduledTask = Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, Consumer { this.run() }, Math.max(delay, 1), period)
        }
    }
    fun cancelTasks(){
        if(::currentBukkitTask.isInitialized && !currentBukkitTask.isCancelled) currentBukkitTask.cancel()
        if(::currentScheduledTask.isInitialized && !currentScheduledTask.isCancelled) currentScheduledTask.cancel()
    }
}