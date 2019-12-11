package io.github.alexandregerault.battleroyale.builders;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.GZIPInputStream;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.persistence.DataFormats;
import org.spongepowered.api.data.persistence.DataTranslators;
import org.spongepowered.api.world.BlockChangeFlags;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.schematic.Schematic;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;

public class Builder {
	
	public static void build(Location<World> loc_, String structureName_, BattleRoyale plugin_) throws FileNotFoundException {
		//Loading the schematic file
		File inputFile = new File(plugin_.schematicDir().getPath() + "/" + structureName_ + ".schematic");
		
		//If the file doesn't exist, throw FileNotFoundException
        if (!inputFile.exists()) {
            plugin_.plugin().getLogger().error("File doesn't exist");
            throw new FileNotFoundException();
        }
        
        DataContainer schematicData;
        
        try {
            schematicData = DataFormats.NBT.readFrom(new GZIPInputStream(new FileInputStream(inputFile)));
        } catch (Exception e) {
            e.printStackTrace();
            plugin_.plugin().getLogger().error("Error loading schematic: " + e.getMessage());
            return;
        }
        
        Schematic schematic = DataTranslators.SCHEMATIC.translate(schematicData);
        
        schematic.apply(loc_, BlockChangeFlags.ALL);
	}
}
