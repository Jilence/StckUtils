package de.stckoverflw.stckutils.minecraft.gamechange

import de.stckoverflw.stckutils.minecraft.gamechange.impl.*
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigotMainInstance

object GameChangeManager {

    lateinit var gameChanges: ArrayList<GameChange>

    operator fun invoke() {
        gameChanges = arrayListOf(
            MaxHealth,
            DeathCounter,
            DamageMultiplier,
            MobDuplicator,
            MobMagnet,
        )
    }

    fun registerGameChangeListeners() {
        gameChanges.forEach { change ->
            change.unregister()
            if (change.usesEvents) {
                pluginManager.registerEvents(change, KSpigotMainInstance)
            }
        }
    }

    fun getGameChange(id: String): GameChange? {
        gameChanges.forEach { change ->
            if (change.id.equals(id, true)) {
                return change
            }
        }
        return null
    }
}
