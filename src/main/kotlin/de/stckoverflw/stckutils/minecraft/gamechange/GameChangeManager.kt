package de.stckoverflw.stckutils.minecraft.gamechange
import de.stckoverflw.stckutils.minecraft.challenge.ChallengeManager
import de.stckoverflw.stckutils.minecraft.challenge.impl.JackHammer
import de.stckoverflw.stckutils.minecraft.gamechange.impl.gamerule.AllowPvP
import de.stckoverflw.stckutils.minecraft.gamechange.impl.gamerule.Difficulty
import de.stckoverflw.stckutils.minecraft.gamechange.impl.gamerule.KeepInventory
import de.stckoverflw.stckutils.minecraft.gamechange.impl.extension.*
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigotMainInstance

object GameChangeManager {

    lateinit var gameChanges: ArrayList<GameChange>

    operator fun invoke() {
        gameChanges = arrayListOf(
            MaxHealth,
            DeathCounter,
            AllowPvP,
            Difficulty,
            KeepInventory,
            DamageMultiplier,
            MobDuplicator,
            MobMagnet
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

    fun unregisterGameChangeListeners() {
        gameChanges.forEach { change ->
            change.unregister()
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
