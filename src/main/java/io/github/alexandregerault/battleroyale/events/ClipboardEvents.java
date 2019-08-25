package io.github.alexandregerault.battleroyale.events;

import java.util.Optional;

import io.github.alexandregerault.battleroyale.utils.ClipboardHelper;
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

import io.github.alexandregerault.battleroyale.data.ClipboardKeys;

public class ClipboardEvents {

	public ClipboardEvents() {}
	
	@Listener
    public void onInteract(InteractBlockEvent.Primary.MainHand event, @Root Player player) {
        Optional<ItemStack> item = player.getItemInHand(HandTypes.MAIN_HAND);

        if (item.isPresent() && item.get().getType().equals(ItemTypes.GOLDEN_HOE) && event.getTargetBlock() != BlockSnapshot.NONE) {
        	player.offer(ClipboardKeys.CORNER_ONE, Optional.of(event.getTargetBlock().getPosition()));

        	if (! player.get(ClipboardKeys.CORNER_ONE).isPresent() || !player.get(ClipboardKeys.CORNER_ONE).get().isPresent()) {
        	    player.sendMessage(Text.of(TextColors.RED, "An error has occurred!"));
        	    return;
            }

            player.sendMessage(Text.of(TextColors.GREEN, "Position 1 set to " + player.get(ClipboardKeys.CORNER_ONE).get().get()));

            ClipboardHelper.updateClipboard(player);

            event.setCancelled(true);
        }
    }
	
	@Listener
    public void onInteract(InteractBlockEvent.Secondary.MainHand event, @Root Player player) {
        Optional<ItemStack> item = player.getItemInHand(HandTypes.MAIN_HAND);
        if (item.isPresent() && item.get().getType().equals(ItemTypes.GOLDEN_HOE) && event.getTargetBlock() != BlockSnapshot.NONE) {
        	player.offer(ClipboardKeys.CORNER_TWO, Optional.of(event.getTargetBlock().getPosition()));

        	if (!player.get(ClipboardKeys.CORNER_TWO).isPresent() || !player.get(ClipboardKeys.CORNER_TWO).get().isPresent()) {
                player.sendMessage(Text.of(TextColors.RED, "An error has occurred!"));
                return;
            }

            player.sendMessage(Text.of(TextColors.GREEN, "Position 2 set to " + player.get(ClipboardKeys.CORNER_TWO).get().get()));

            ClipboardHelper.updateClipboard(player);

            event.setCancelled(true);
        }
    }
}
