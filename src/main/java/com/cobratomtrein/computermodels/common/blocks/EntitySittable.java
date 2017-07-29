/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.common.blocks;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 *
 * @author tomtrein
 */
public class EntitySittable extends EntityPig {

    public static List<EntitySittable> entityList = new ArrayList<EntitySittable>();
    
    protected BlockPos m_pos;
    
    public EntitySittable(World worldIn,BlockPos pos) {
        super(worldIn);
        m_pos = pos;
        this.noClip = true;
        this.setPosition(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5);
        this.setSize(0.1f, 0.1f);
    }
    
    public static EntitySittable getEntityFor(World worldIn,BlockPos pos){
        for (EntitySittable sittable : entityList){
            if (sittable.m_pos.equals(pos)){
                return sittable;
            }
        }
        return null;
    }
    
    public boolean isAIDisabled()
    {
        return true;
    }    
}
