package io.github.alexandregerault.battleroyale.utils;

import com.flowpowered.math.vector.Vector3i;
import io.github.alexandregerault.battleroyale.data.ClipboardKeys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;
import org.spongepowered.api.world.schematic.Schematic;

import java.util.Optional;

public class ClipboardHelper {

    public static void updateClipboard(Player player) {
        if (!player.get(ClipboardKeys.CORNER_ONE).isPresent() || !player.get(ClipboardKeys.CORNER_TWO).isPresent())
            return;

        Optional<Vector3i> optPositionOne = player.get(ClipboardKeys.CORNER_ONE).get();
        Optional<Vector3i> optPositionTwo = player.get(ClipboardKeys.CORNER_TWO).get();

        if (!optPositionOne.isPresent() || !optPositionTwo.isPresent())
            return;

        Vector3i positionOne = optPositionOne.get();
        Vector3i positionTwo = optPositionTwo.get();

        Vector3i origin = new Vector3i(
                ( positionOne.getX() + positionTwo.getX() )/2,
                positionOne.min(positionTwo).getY(),
                ( positionOne.getZ() + positionTwo.getZ() )/2
        );

        Schematic schematic = Schematic.builder()
                .volume(player.getWorld().createArchetypeVolume(positionOne.min(positionTwo), positionOne.max(positionTwo), origin))
                .metaValue(Schematic.METADATA_AUTHOR, player.getName())
                .paletteType(BlockPaletteTypes.LOCAL)
                .build();

        player.offer(ClipboardKeys.CLIPBOARD, Optional.of(schematic));
        player.sendMessage(Text.of(TextColors.GREEN, "Copying from " + positionOne.toString() + " to " + positionTwo.toString() +"! Clipboard updated."));
    }

}