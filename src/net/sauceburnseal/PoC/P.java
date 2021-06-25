package net.sauceburnseal.PoC;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;

public class P extends JavaPlugin implements Listener
{
	public static P pl;
	ConcurrentHashMap<Entity, TargetChan> track = new ConcurrentHashMap(); // wait for setable AI

	public void onEnable() {
		pl = this;
		Bukkit.getPluginManager().registerEvents(this, this);

		/*Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				updateTarget();
			}
		}, 20, 10);*/
	}

	public void onDisable() {
		HandlerList.unregisterAll((org.bukkit.plugin.java.JavaPlugin)pl);
		this.track.clear();
	}

	public void setFly(LivingEntity ent) {
		final Vector vel = ent.getVelocity();
		final World ww = ent.getWorld();
		//Mob bat = (Mob) ww.spawnEntity(ent.getLocation(), EntityType.BAT);
		Mob bat = (Mob) ww.spawnEntity(ent.getLocation(), EntityType.BEE);
		//Mob bat = (Mob) ww.spawnEntity(ent.getLocation(), EntityType.PARROT);
		//Mob bat = (Mob) ww.spawnEntity(ent.getLocation(), EntityType.PHANTOM);
		//Mob bat = (Mob) ww.spawnEntity(ent.getLocation(), EntityType.VEX);
		//bat.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("hp", 20, AttributeModifier.Operation.ADD_NUMBER));
		bat.setVelocity(vel);
		bat.addPassenger(ent);
		// bat.addPotionEffect(new PotionEffect(PotionEffectType.getByName("REGENERATION"), Integer.MAX_VALUE, 1, false, false));
		// bat.addPotionEffect(new PotionEffect(PotionEffectType.getByName("HEALTH_BOOST"), Integer.MAX_VALUE, 5, false, false));
		// bat.addPotionEffect(new PotionEffect(PotionEffectType.getByName("HEAL"), Integer.MAX_VALUE, 5, false, false));
		// bat.addPotionEffect(new PotionEffect(PotionEffectType.getByName("FIRE_RESISTANCE"), Integer.MAX_VALUE, 0, false, false));
		//bat.addPotionEffect(new PotionEffect(PotionEffectType.getByName("SPEED"), Integer.MAX_VALUE, 5, false, false));
		// TODO: other buff
		bat.setTarget(((Mob)ent).getTarget());

		this.track.put(ent, new TargetChan((Mob)bat, (Mob)ent));
	}

	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		EntityType mobType = e.getEntityType();
		LivingEntity ent = e.getEntity();
		if (ent == null) return;
		switch (mobType) {
		case ZOMBIE:
		case DROWNED:
		case HUSK:
		case ZOMBIFIED_PIGLIN:
		case PIGLIN:
		case PIGLIN_BRUTE:
		case CREEPER:
		case SKELETON:
		case WITHER_SKELETON:
			this.setFly(ent);
		default:
			// nop
		}
	}

	@EventHandler
	public void onEntityRemoveFromWorld(EntityRemoveFromWorldEvent e) {
		Entity ent = e.getEntity();
		TargetChan tchan = this.track.get(ent);
		if (tchan != null) {

			// this cause java.util.ConcurrentModificationException
			tchan.Carrier.remove();

			// workaround:
			// pl.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			// 	public void run() {
			// 		tchan.Carrier.remove();
			// 	}
			// }, 4L);

			this.track.remove(ent);
		}
	}

	static class TargetChan {
		public final Mob Carrier;
		public final Mob Passenger;
		public LivingEntity Target;

		TargetChan(Mob carrier, Mob passenger) {
			this.Carrier = carrier;
			this.Passenger = passenger;
		}
	}
}

