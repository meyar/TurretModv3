package sanandreasp.mods.TurretMod3.entity.turret;

import java.util.AbstractMap.SimpleEntry;

import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Pebble;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.TurretMod3.registry.AchievementPageTM;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgEnderHitting;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgInfAmmo;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTurret_T1Shotgun extends EntityTurret_Base {
	
	private boolean firstStrike = false;

	public EntityTurret_T1Shotgun(World par1World) {
		super(par1World);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t1Shotgun.png";
	}

	@Override
	public TurretProjectile getProjectile() {
		return new TurretProj_Pebble(this.worldObj);
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		boolean isPickupable = !TurretUpgrades.hasUpgrade(TUpgInfAmmo.class, this.upgrades) && !this.isEconomied;
        boolean isEndermanDamageable = TurretUpgrades.hasUpgrade(TUpgEnderHitting.class, this.upgrades);
		for (int i = 0; i < 5; i++) {
	        TurretProjectile var2 = this.getProjectile();
	        var2.isPickupable = isPickupable;
	        if (isRidden) {
	        	EntityPlayer player = (EntityPlayer) this.riddenByEntity;
	        	var2.hasNoTarget = true;
	        	var2.setLocationAndAngles(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, player.rotationYaw, player.rotationPitch);
	        	var2.posX -= (double)(MathHelper.cos(var2.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
	        	var2.posY -= 0.10000000149011612D;
	        	var2.posZ -= (double)(MathHelper.sin(var2.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
	        	var2.setPosition(var2.posX, var2.posY, var2.posZ);
	        	var2.yOffset = 0.0F;
	        	var2.motionX = (double)(-MathHelper.sin(var2.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(var2.rotationPitch / 180.0F * (float)Math.PI));
	        	var2.motionZ = (double)(MathHelper.cos(var2.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(var2.rotationPitch / 180.0F * (float)Math.PI));
	        	var2.motionY = (double)(-MathHelper.sin(var2.rotationPitch / 180.0F * (float)Math.PI));
	        	var2.setHeading(var2.motionX, var2.motionY, var2.motionZ, 1F * 1.5F, 12.0F);
	        	var2.shootingEntity = this;
	        } else {
	        	var2.setTarget(this, this.currentTarget, 1.4F, 0.0F);
	        	var2.motionX /= var2.getSpeedVal();
	        	var2.motionY /= var2.getSpeedVal();
	        	var2.motionZ /= var2.getSpeedVal();
	        	var2.setHeading(var2.motionX, var2.motionY, var2.motionZ, 1F * 1.0F, 12.0F);
	        }
	        var2.isEndermanDamageable = isEndermanDamageable;
	        this.worldObj.spawnEntityInWorld(var2);
	        var2.isMoving = true;
		}
		
		double partX = this.posX - Math.sin((this.rotationYawHead / 180) * Math.PI) * 0.5D * (Math.cos(this.rotationPitch / (180F / (float)Math.PI)));
		double partY = this.posY + this.getEyeHeight() - Math.sin(this.rotationPitch / (180F / (float)Math.PI)) * 0.5D;
		double partZ = this.posZ + Math.cos((this.rotationYawHead / 180) * Math.PI) * 0.5D * (Math.cos(this.rotationPitch / (180F / (float)Math.PI)));
		
		TM3ModRegistry.proxy.spawnParticle(0, partX, partY, partZ, 64, this.worldObj.getWorldInfo().getDimension(), this);
		
	    this.worldObj.playSoundAtEntity(this, this.getShootSound(), 1.5F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	}
	
	@Override
	public int getMaxShootTicks() {
		this.firstStrike = false;
		return 20;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t1ShotgunG.png";
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.shotgun";
	}
	
	@Override
	public void onKillEntity(EntityLiving par1EntityLiving) {
		super.onKillEntity(par1EntityLiving);
		if (this.firstStrike && this.riddenByEntity == null) {
			EntityPlayer player = this.worldObj.getPlayerEntityByName(this.getPlayerName());
			if (player != null)
				player.triggerAchievement(AchievementPageTM.multiDeath);
		}
		this.firstStrike = true;
	}
}
