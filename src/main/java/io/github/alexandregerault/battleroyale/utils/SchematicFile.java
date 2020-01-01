package io.github.alexandregerault.battleroyale.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.persistence.DataFormats;
import org.spongepowered.api.data.persistence.DataTranslators;
import org.spongepowered.api.world.extent.ArchetypeVolume;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;
import org.spongepowered.api.world.schematic.Schematic;

import io.github.alexandregerault.battleroyale.main.BattleRoyale;

public class SchematicFile {
	
	public static boolean save(ArchetypeVolume volume_, String author_, String name_, String location_) {
		Schematic schematic = Schematic.builder()
                .volume(volume_)
                .metaValue(Schematic.METADATA_AUTHOR, author_)
                .metaValue(Schematic.METADATA_NAME, name_)
                .paletteType(BlockPaletteTypes.LOCAL)
                .build();
        
        DataContainer schematicData = DataTranslators.SCHEMATIC.translate(schematic);
        
        File outputFile = new File(location_);
        
        try {
            DataFormats.NBT.writeTo(new GZIPOutputStream(new FileOutputStream(outputFile)), schematicData);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean exists(String name_, BattleRoyale plugin_) {
		File directory = plugin_.schematicDir();
		File[] schematicFiles = directory.listFiles();
		
		for(File file : schematicFiles) {
			if(file.exists() && file.getName().equals(name_ + ".schematic")) {
				return true;
			}
		}
		
		return false;		
	}
	
	public static File get(String name_, BattleRoyale plugin_) {		
		return new File(plugin_.schematicDir().getPath() + "/" + name_ + ".schematic");
	}
}
