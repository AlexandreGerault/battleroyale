package io.github.alexandregerault.battleroyale.events;

import java.util.Optional;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;

public class ClipboardEvents {
	
	
	private final BattleRoyale plugin;
	
	public ClipboardEvents(BattleRoyale plugin_) {
		this.plugin = plugin_;
	}
	
	@Listener
    public void onInteract(InteractBlockEvent.Primary.MainHand event, @Root Player player) {
        Optional<ItemStack> item = player.getItemInHand(HandTypes.MAIN_HAND);
        if (item.isPresent() && item.get().getType().equals(ItemTypes.GOLDEN_HOE) && event.getTargetBlock() != BlockSnapshot.NONE) {
            plugin.getPlayerData(player).setPos2(event.getTargetBlock().getPosition());
            player.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "Position 1 set to " + event.getTargetBlock().getPosition()));
            event.setCancelled(true);
        }
        
        return;
    }
	
	@Listener
    public void onInteract(InteractBlockEvent.Secondary.MainHand event, @Root Player player) {
        Optional<ItemStack> item = player.getItemInHand(HandTypes.MAIN_HAND);
        if (item.isPresent() && item.get().getType().equals(ItemTypes.GOLDEN_HOE) && event.getTargetBlock() != BlockSnapshot.NONE) {
            plugin.getPlayerData(player).setPos1(event.getTargetBlock().getPosition());
            player.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "Position 2 set to " + event.getTargetBlock().getPosition()));
            event.setCancelled(true);
        }
        
        return;
    }
}
