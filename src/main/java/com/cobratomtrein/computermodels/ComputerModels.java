/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cobratomtrein.computermodels;


import com.cobratomtrein.computermodels.CCSandbox.blocks.BlockRegisterComputer;
import com.cobratomtrein.computermodels.common.ProxyCommon;
import com.cobratomtrein.computermodels.common.utils.CreativeTabModels;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * @author tomtrein
 */

@Mod(modid = ComputerModels.MODID, version = ComputerModels.VERSION)
public class ComputerModels {
    public static final String MODID = "computermodels";
    public static final String VERSION = "0.0.1";
    public static final CreativeTabModels CreativeTab = new CreativeTabModels();
    
    @Mod.Instance
    public static ComputerModels instance = new ComputerModels();
    
    @SidedProxy(clientSide="com.cobratomtrein.computermodels.client.ProxyClient", serverSide="com.cobratomtrein.computermodels.common.ProxyCommon")
    public static ProxyCommon proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        BlockRegisterComputer.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
    
    
    
}
