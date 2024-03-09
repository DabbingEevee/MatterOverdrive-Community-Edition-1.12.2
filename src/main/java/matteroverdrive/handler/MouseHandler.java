
package matteroverdrive.handler;

import matteroverdrive.api.weapon.IWeapon;
import matteroverdrive.gui.GuiAndroidHud;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MouseHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		if (GuiAndroidHud.showRadial) {
			GuiAndroidHud.radialDeltaX -= event.getDx() / 100D;
			GuiAndroidHud.radialDeltaY += event.getDy() / 100D;

			double mag = Math.sqrt(GuiAndroidHud.radialDeltaX * GuiAndroidHud.radialDeltaX
					+ GuiAndroidHud.radialDeltaY * GuiAndroidHud.radialDeltaY);
			if (mag > 1.0D) {
				GuiAndroidHud.radialDeltaX /= mag;
				GuiAndroidHud.radialDeltaY /= mag;
			}
		}
		ItemStack heldItem = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
		if (heldItem.getItem() instanceof IWeapon) {
			if (event.getButton() == 0 && event.isButtonstate()) {
				if (((IWeapon) heldItem.getItem()).onLeftClick(
						heldItem,
						Minecraft.getMinecraft().player)) {
					event.setCanceled(true);
				}
			}
		}
	}
}
