package com.nekozouneko.periodicAnnounce

import com.nekozouneko.periodicAnnounce.tasks.PeriodicMessageTask
import lombok.Getter
import org.bukkit.plugin.java.JavaPlugin

class PeriodicAnnounce : JavaPlugin() {
    companion object{
        @Getter
        lateinit var instance: PeriodicAnnounce

        val isFolia: Boolean = isClassExists("io.papermc.paper.threadedregions.RegionizedServer") || isClassExists("io.papermc.paper.threadedregions.RegionizedServerInitEvent")
        private fun isClassExists(clazz: String) : Boolean{
            try {
                Class.forName(clazz)
                return true
            }catch (e: ClassNotFoundException){
                return false
            }
        }
    }

    override fun onEnable() {
        instance = this

        saveDefaultConfig()
        val periodicMessageTask = PeriodicMessageTask()
        val interval = config.getInt("interval", 300)
        periodicMessageTask.repeatingTask(this, 0, interval * 20L)
    }

    override fun onDisable() {
    }
}
