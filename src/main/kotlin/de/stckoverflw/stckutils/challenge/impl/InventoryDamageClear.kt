package de.stckoverflw.stckutils.challenge.impl

import de.stckoverflw.stckutils.challenge.Challenge
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.gui.ForInventoryThreeByNine
import net.axay.kspigot.gui.GUI
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent

object InventoryDamageClear : Challenge() {
    override val id: String = "inventory-damage-clear"
    override val name: String = "§9Inventory Damage Clear"
    override val material: Material = Material.PURPLE_WOOL
    override val description: List<String> = listOf(
        " ",
        "§7Every Time you get §cDamage §7your",
        "§7entire Inventory gets cleared"
    )
    override val usesEvents: Boolean = true

    override fun configurationGUI(): GUI<ForInventoryThreeByNine>? = null

    @EventHandler(ignoreCancelled = true)
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            onlinePlayers.forEach {
                it.inventory.clear()
            }
        }
    }
}