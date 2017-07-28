/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.CCSandbox.blocks;

import com.cobratomtrein.computermodels.ComputerModels;
import com.cobratomtrein.computermodels.common.blocks.BlockIComputer;
import com.cobratomtrein.computermodels.common.items.ItemUsableComputer;
import com.cobratomtrein.computermodels.common.utils.CreativeTabModels;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author tomtrein
 */
public class BlockRegisterComputer {
    
    public static ComputerBlock m_test; 
    
    
    public static String[] computerNamesUsable = new String[]{"tower_computer","tower_computer2","tower_computer3","laptop"};
    public static String[] modelNames = new String[]{"monitor","modem","xhair"};
    public static HashMap<String,Block> blocks = new HashMap<String,Block>();
    
    
    public static void init(){
        //m_test = registerComputer(new ComputerBlock("laptop",false));
        for (String name : computerNamesUsable){
            blocks.put(name, registerComputer(new ComputerBlock(name,true)));
        }
        for (String name : modelNames){
            blocks.put(name, registerModel(new BlockIComputer(Material.ROCK,name)));
        }
        
        
        ComputerModels.CreativeTab.Icon = Item.getItemFromBlock(blocks.get("tower_computer"));
    }
    
    public static void registerItemRenderer(Item item, int meta, String id) {
        ComputerModels.proxy.registerItemRenderer(item, meta, id);
    }
    
    
    
    private static <T extends Block> T register(T block, Item itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if (block instanceof ComputerBlock) {
            ((ComputerBlock)block).registerItemModel(itemBlock);
        } else if (block instanceof BlockIComputer) {
            ((BlockIComputer)block).registerItemModel(itemBlock);
        }
        
        return block;
    }

    private static <T extends Block> T registerComputer(T block) {
        
        Item itemBlock = new ItemUsableComputer(block,true);
        
        return register(block, itemBlock);
    }
    
    private static <T extends Block> T registerModel(T block) {
        
        Item itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        
        return register(block, itemBlock);
    }
}
