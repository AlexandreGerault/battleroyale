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

        Vector3i min = optPositionOne.get().min(optPositionTwo.get());
        Vector3i max = optPositionOne.get().max(optPositionTwo.get());

        if (max.getX() == min.getX()) max = max.add(1,0,0);
        if (max.getY() == min.getY()) max = max.add(0,1,0);
        if (max.getZ() == min.getZ()) max = max.add(0,0,1);

        Schematic schematic = Schematic.builder()
                .volume(player.getWorld().createArchetypeVolume(min, max, min))
                .metaValue(Schematic.METADATA_AUTHOR, player.getName())
                .paletteType(BlockPaletteTypes.LOCAL)
                .build();

        player.sendMessage(Text.of(TextColors.GREEN, "Clipboard updated!"));

        player.offer(ClipboardKeys.CLIPBOARD, Optional.of(schematic));
    }

}