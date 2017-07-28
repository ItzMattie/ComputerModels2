/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.client;

import com.cobratomtrein.computermodels.CCSandbox.blocks.BlockRegisterComputer;
import com.cobratomtrein.computermodels.ComputerModels;
import com.cobratomtrein.computermodels.common.ProxyCommon;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

/**
 *
 * @author tomtrein
 */
public class ProxyClient extends ProxyCommon {
    public void registerItemRenderer(Item item, int meta, String id) {
        final String my_id = id;
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ComputerModels.MODID + ":" + id, "inventory"));
        ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition(){
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(ComputerModels.MODID + ":" + my_id, "inventory");
            }
            
        });
    }
}
