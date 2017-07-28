/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.CCSandbox.blocks;

import dan200.computercraft.shared.computer.blocks.TileComputer;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import javax.annotation.Nonnull;
import net.minecraft.nbt.NBTTagCompound;

/**
 *
 * @author tomtrein
 */
public class TileComputerCobra extends TileComputer {
    
    protected boolean isAdvanced = true;
    
    public TileComputerCobra(){
    }
    
    public void setAdvanced(boolean adv){
        isAdvanced = adv;
        System.out.println("Advanced: "+isAdvanced);
    }
    
    
    @Override
    public ComputerFamily getFamily(){
        return isAdvanced ? ComputerFamily.Advanced : ComputerFamily.Normal;
    }
    
    
    @Nonnull
    @Override
    public NBTTagCompound writeToNBT( NBTTagCompound nbttagcompound )
    {
        nbttagcompound = super.writeToNBT( nbttagcompound );
        System.out.println("Written to nbt");
        System.out.println("ID: "+nbttagcompound.getInteger( "computerID" ));
        return nbttagcompound;
    }

    @Override
    public void readFromNBT( NBTTagCompound nbttagcompound )
    {
        super.readFromNBT( nbttagcompound );
        System.out.println("Read from nbt");
        System.out.println("ID: "+nbttagcompound.getInteger( "computerID" ));
    }
    
}
