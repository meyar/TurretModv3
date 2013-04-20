package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Flamethrower;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Laser;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T4FLAK;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T4Sniper;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T5Artillery;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T5Railgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSCollector;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TUpgExpStorageC extends TurretUpgrades {

	public TUpgExpStorageC() {
		this.upgName = "turretmod3.upgrades.nameMoreXP";
		this.upgDesc = "turretmod3.upgrades.descMoreXP";
		this.upgItem = new ItemStack(Item.eyeOfEnder);
		
		this.turrets.add(EntityTurret_TSCollector.class);
	}
}