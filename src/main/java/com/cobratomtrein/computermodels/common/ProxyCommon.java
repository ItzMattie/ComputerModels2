/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.common;

import com.cobratomtrein.computermodels.CCSandbox.blocks.ComputerBlock;
import com.cobratomtrein.computermodels.CCSandbox.blocks.TileComputerCobra;
import com.cobratomtrein.computermodels.ComputerModels;
import com.cobratomtrein.computermodels.common.blocks.BlockIComputer;
import com.cobratomtrein.computermodels.common.items.ItemUsableComputer;
import dan200.computercraft.ComputerCraft;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 *
 * @author tomtrein
 */
public class ProxyCommon {
    
    public void init(){
        
        GameRegistry.registerTileEntity(TileComputerCobra.class, "tilecomputercobra");
        
        
    }
    
    public void postInit(){
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }
    
}
