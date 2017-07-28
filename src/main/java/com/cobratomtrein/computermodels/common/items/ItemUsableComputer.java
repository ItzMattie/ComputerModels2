/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.common.items;

import dan200.computercraft.shared.computer.core.ComputerFamily;
import dan200.computercraft.shared.computer.items.ItemComputer;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author tomtrein
 */
public class ItemUsableComputer extends ItemComputer {
    
    public String unlocalizedName;
    public boolean isAdvanced;
    
    public ItemUsableComputer(Block block,boolean isAdvanced) {
        super(block);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setUnlocalizedName(block.getUnlocalizedName());
        unlocalizedName = block.getUnlocalizedName();
        this.setRegistryName(block.getRegistryName());
        setHasSubtypes( false );
        if (!isAdvanced){
            System.out.println(block.getUnlocalizedName() + " is NOT  advanced");
        } else {
            System.out.println(block.getUnlocalizedName() + " IS advanced");
        }
        this.isAdvanced = isAdvanced;
    }
    
    @Override
    public String getUnlocalizedName( ItemStack stack )
    {
        return unlocalizedName;
    }
    
    @Override
    public ComputerFamily getFamily(int i) {
        System.out.println("Advanced? "+isAdvanced);
        return isAdvanced ? ComputerFamily.Advanced : ComputerFamily.Normal;
    }
    
    @Override
    public void getSubItems( @Nonnull Item itemID, @Nullable CreativeTabs tabs, @Nonnull List<ItemStack> list )
    {
        list.add( new ItemStack(itemID) );
    }
    
    
}
