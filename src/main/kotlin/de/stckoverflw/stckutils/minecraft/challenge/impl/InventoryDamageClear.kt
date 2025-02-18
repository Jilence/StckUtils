package de.stckoverflw.stckutils.minecraft.challenge.impl

import de.stckoverflw.stckutils.minecraft.challenge.Challenge
import net.axay.kspigot.gui.ForInventoryFiveByNine
import net.axay.kspigot.gui.GUI
import org.bukkit.Material
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
        "§7entire Inventory gets cleared",
    )
    override val usesEvents: Boolean = true

    override fun configurationGUI(): GUI<ForInventoryFiveByNine>? = null

    @EventHandler(ignoreCancelled = true)
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            (event.entity as Player).inventory.clear()
        }
    }
}
