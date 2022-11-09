package com.elenai.elenaidodge2.util;

import com.elenai.elenaidodge2.client.ED2ClientStorage;
import com.elenai.elenaidodge2.client.animation.DodgeAnimator;
import com.elenai.elenaidodge2.client.animation.DodgeAnimator.DodgeDirection;
import com.elenai.elenaidodge2.event.ClientEvents;
import com.elenai.elenaidodge2.networking.DodgeEffectsCTSPacket;
import com.elenai.elenaidodge2.networking.ED2Messages;
import com.elenai.feathers.api.FeathersHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public class DodgeHandler {

	public static void handleDodge(DodgeAnimator.DodgeDirection direction) {
		Minecraft instance = Minecraft.getInstance();
		if (instance.player != null && (instance.player.isOnGround() || ED2ClientStorage.allowAirborne())
				&& !instance.player.isRidingJumpable() && !instance.player.isCrouching()
				&& ClientEvents.currentCooldown == 0 && instance.screen == null && !instance.player.isSwimming()
				&& (instance.player.getFoodData().getFoodLevel() > 6) && !instance.player.isBlocking()
				&&FeathersHelper.spendFeathers(ED2ClientStorage.getCost())) {

			String animationDirection = DodgeDirection.BACKWARDS.name();

			double power = ED2ClientStorage.getPower();
			double verticality = ED2ClientStorage.getVerticality();

			Vec3 look = instance.player.getLookAngle().multiply(power, 0, power).normalize();
			Vec3 forwards = new Vec3(look.x, verticality, look.z);
			Vec3 backwards = new Vec3(-look.x, verticality, -look.z);
			Vec3 left = new Vec3(look.z, verticality, -look.x);
			Vec3 right = new Vec3(-look.z, verticality, look.x);
			
			Vec3 forwardsLeft = forwards.add(left).scale(0.5);
			Vec3 forwardsRight = forwards.add(right).scale(0.5);
			Vec3 backwardsLeft = backwards.add(left).scale(0.5);
			Vec3 backwardsRight = backwards.add(right).scale(0.5);

			switch (direction) {
			case FORWARDS:
				instance.player.push(forwards.x, forwards.y, forwards.z);
				animationDirection = DodgeDirection.FORWARDS.name();
				break;
			case BACKWARDS:
				instance.player.push(backwards.x, backwards.y, backwards.z);
				animationDirection = DodgeDirection.BACKWARDS.name();
				break;
			case LEFT:
				instance.player.push(left.x, left.y, left.z);
				animationDirection = DodgeDirection.LEFT.name();
				break;
			case RIGHT:
				instance.player.push(right.x, right.y, right.z);
				animationDirection = DodgeDirection.RIGHT.name();
				break;
			case BACKWARDS_LEFT:
				instance.player.push(backwardsLeft.x, backwardsLeft.y, backwardsLeft.z);
				animationDirection = DodgeDirection.BACKWARDS_LEFT.name();
				break;
			case BACKWARDS_RIGHT:
				instance.player.push(backwardsRight.x, backwardsRight.y, backwardsRight.z);
				animationDirection = DodgeDirection.BACKWARDS_RIGHT.name();
				break;
			case FORWARDS_LEFT:
				instance.player.push(forwardsLeft.x, forwardsLeft.y, forwardsLeft.z);
				animationDirection = DodgeDirection.FORWARDS_LEFT.name();
				break;
			case FORWARDS_RIGHT:
				instance.player.push(forwardsRight.x, forwardsRight.y, forwardsRight.z);
				animationDirection = DodgeDirection.FORWARDS_RIGHT.name();
				break;
			}

			ClientEvents.currentCooldown = ED2ClientStorage.getCooldown();
			ED2Messages.sendToServer(new DodgeEffectsCTSPacket(animationDirection));
		}
	}

}
