package io.github.sinhajun

import org.bukkit.plugin.java.JavaPlugin

class KotlinSample : JavaPlugin() {
    override fun onEnable() {
        logger.info("Kotlin")
    }
}