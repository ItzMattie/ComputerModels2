/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cobratomtrein.computermodels.CCSandbox.blocks;

import com.cobratomtrein.computermodels.ComputerModels;
import static com.cobratomtrein.computermodels.common.blocks.BlockIComputer.FACING;
import dan200.computercraft.ComputerCraft;
import dan200.computercraft.shared.common.TileGeneric;
import dan200.computercraft.shared.computer.blocks.ComputerState;
import dan200.computercraft.shared.computer.blocks.TileComputer;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author tomtrein
 */
public class ComputerBlock extends Block implements ITileEntityProvider {
    public static final PropertyDirection FACING = PropertyDirection.create("facing",EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool ADVANCED = PropertyBool.create("advanced");
    public static final PropertyEnum<ComputerState> STATE = PropertyEnum.create("state", ComputerState.class);
    
    public String name;
    protected boolean isAdvanced = false;
    private IBlockState defaultBlockState;
    
    public ComputerBlock(String name) {
        this(name,true);
    }
    
    public ComputerBlock(String name,boolean isAdvanced) {
        super(Material.ROCK);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ComputerModels.CreativeTab);
        this.name = name;
        this.isAdvanced = isAdvanced;
        this.defaultBlockState = this.getBlockState().getBaseState().withProperty(STATE, ComputerState.On).withProperty(ADVANCED, isAdvanced);
    }
    
    public void registerItemModel(Item itemBlock) {
        BlockRegisterComputer.registerItemRenderer(itemBlock, 0, name);
    }
    
    public int getMetaFromState(IBlockState state){
        return state.getValue(FACING).getHorizontalIndex();
    }
    
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this,new IProperty[]{FACING,ADVANCED,STATE});
    }
    
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isTranslucent(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileComputerCobra tile = new TileComputerCobra();
        tile.setAdvanced(isAdvanced);
        return tile;
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileGeneric generic = (TileGeneric) worldIn.getTileEntity(pos);
        return generic.onActivate( playerIn, side, hitX, hitY, hitZ );
    }
    
    
    //Dropped Items and such
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        System.out.println("Get me some pick block");
        TileGeneric generic = (TileGeneric) world.getTileEntity(pos);
        ItemStack item = generic.getPickedItem();
        ItemStack newItem = convertItem(item);
        return newItem;       
    }
    
    private ItemStack convertItem(ItemStack item) {
        if (item.getItem() == Item.getItemFromBlock(ComputerCraft.Blocks.computer)){
            ItemStack newItem = new ItemStack(Item.getItemFromBlock(this));
            newItem.stackSize = item.stackSize;
            newItem.setTagCompound(item.getTagCompound());
            newItem.setItemDamage(item.getItemDamage());
            return newItem;  
        }
        return item;
    }
    
    
    
    @Override
    public final void dropBlockAsItemWithChance( World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, float chance, int fortune )
    {
    }

    @Nonnull
    @Override
    public final List<ItemStack> getDrops( IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune )
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>( 1 );
        TileEntity tile = world.getTileEntity( pos );
        if( tile != null && tile instanceof TileGeneric )
        {
            TileGeneric generic = (TileGeneric)tile;
            generic.getDroppedItems( drops, false );
            ArrayList<ItemStack> dropsOld = drops;
            drops = new ArrayList<ItemStack>();
            for (ItemStack item : dropsOld){
                drops.add(convertItem(item));
            }
        }
        return drops;
    }
    
    @Override
    public final boolean removedByPlayer( @Nonnull IBlockState state, World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player, boolean willHarvest )
    {
        if( !world.isRemote )
        {
            // Drop items
            boolean creative = player.capabilities.isCreativeMode;
            dropAllItems( world, pos, creative );
        }

        // Remove block
        return super.removedByPlayer( state, world, pos, player, willHarvest );
    }

    public final void dropAllItems( World world, BlockPos pos, boolean creative )
    {
        // Get items to drop
        List<ItemStack> drops = new ArrayList<ItemStack>( 1 );
        TileEntity tile = world.getTileEntity( pos );
        if( tile != null && tile instanceof TileGeneric )
        {
            TileGeneric generic = (TileGeneric)tile;
            generic.getDroppedItems( drops, creative );
        }

        // Drop items
        if( drops.size() > 0 )
        {
            for (ItemStack item : drops)
            {
                dropItem( world, pos, convertItem(item) );
            }
        }
    }

    public final void dropItem( World world, BlockPos pos, ItemStack stack )
    {
        Block.spawnAsEntity( world, pos, stack );
    }

    @Override
    public final void breakBlock( @Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState newState )
    {
        TileEntity tile = world.getTileEntity( pos );
        if( tile != null && tile instanceof TileGeneric )
        {
            TileGeneric generic = (TileGeneric)tile;
            generic.destroy();
        }
        super.breakBlock( world, pos, newState );
        world.removeTileEntity( pos );
    }
    
    
    
    
    
    //Neighbour
    
    @Override
    @Deprecated
    public final void neighborChanged( IBlockState state, World world, BlockPos pos, Block block )
    {
        TileEntity tile = world.getTileEntity( pos );
        if( tile != null && tile instanceof TileGeneric )
        {
            TileGeneric generic = (TileGeneric)tile;
            generic.onNeighbourChange();
        }
    }

    @Override
    public final void onNeighborChange( IBlockAccess world, BlockPos pos, BlockPos neighbour )
    {
        TileEntity tile = world.getTileEntity( pos );
        if( tile instanceof TileGeneric )
        {
            TileGeneric generic = (TileGeneric)tile;
            generic.onNeighbourTileEntityChange( neighbour );
        }
    }
    
    public int getLightValue(IBlockState state)
    {
        return 6;
    }
    
    
    //I Be advanced    
    
    
    
}
