/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.common.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 *
 * @author tomtrein
 */
public class CreativeTabModels extends CreativeTabs {
    
    public Item Icon;
    
    public CreativeTabModels(){
        super("modelCreativeTab");
    }
    
    @Override
    public Item getTabIconItem() {
        return Icon;
    }
    
}
