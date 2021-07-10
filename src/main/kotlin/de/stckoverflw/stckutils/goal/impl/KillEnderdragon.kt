package de.stckoverflw.stckutils.goal.impl

import de.stckoverflw.stckutils.goal.Goal
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDeathEvent

object KillEnderdragon : Goal() {

    override val id: String = "kill-dragon"
    override val name: String = "§cKill Enderdragon"
    override val description: List<String> = listOf(
        " ",
        "§7The Challenge is finished when someone",
        "§ckills the Enderdragon"
    )

    override val material: Material = Material.DRAGON_HEAD

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        if (event.entityType == EntityType.ENDER_DRAGON) {
            win("§7The §9Enderdragon §adied")
        }
    }
}