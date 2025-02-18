package de.stckoverflw.stckutils.minecraft.challenge.impl

import de.stckoverflw.stckutils.minecraft.challenge.Challenge
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.gui.ForInventoryFiveByNine
import net.axay.kspigot.gui.GUI
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.entity.PlayerDeathEvent

object BalanceLife : Challenge() {
    override val id: String = "balance-life"
    override val name: String = "§aBalance Life"
    override val material: Material = Material.HEART_OF_THE_SEA
    override val description: List<String> = listOf(
        " ",
        "§7Keep your health in balance.",
        "§7The Challenge is over when you hit §f0 §7or §ffull hp",
    )
    override val usesEvents: Boolean = true

    override fun configurationGUI(): GUI<ForInventoryFiveByNine>? = null

    private var isFirstRun = true

    override fun prepareChallenge() {
        if (isFirstRun) {
            onlinePlayers.forEach { player ->
                player.health = (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value?.div(2)) as Double
                player.foodLevel = 10
            }
            isFirstRun = false
        }
    }

    @EventHandler
    fun onHealtRegain(event: EntityRegainHealthEvent) {
        if (event.entity is Player && (event.entity as Player).health + event.amount >= (event.entity as Player).getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value!!) {
            lose("${event.entity.name} hit ${(event.entity as Player).getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value?.toInt() ?: "full"} hp.")
            isFirstRun = true
        }
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        lose("${event.entity.name} hit 0 hp.")
        isFirstRun = true
    }
}
