package mod.coda.unnamedanimalmod;

import mod.coda.unnamedanimalmod.client.ClientEventHandler;
import mod.coda.unnamedanimalmod.data.*;
import mod.coda.unnamedanimalmod.entity.*;
import mod.coda.unnamedanimalmod.init.*;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UnnamedAnimalMod.MOD_ID)
@Mod.EventBusSubscriber(modid = UnnamedAnimalMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UnnamedAnimalMod {
    public static final String MOD_ID = "unnamedanimalmod";

    public UnnamedAnimalMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::registerClient);
        bus.addListener(this::registerCommon);

        UAMSounds.REGISTRY.register(bus);
        UAMBlocks.REGISTRY.register(bus);
        UAMItems.REGISTRY.register(bus);
        UAMEntities.REGISTRY.register(bus);
        UAMFeatures.REGISTRY.register(bus);
        UAMBiomes.BIOMES.register(bus);
        UAMBiomes.BUILDERS.register(bus);
        UAMItemTags.init();
        bus.addListener(this::gatherData);
    }
    public void gatherData(GatherDataEvent evt)
    {
        BlockTagsProvider provider = new ModBlockTagProvider(evt.getGenerator());
        evt.getGenerator().addProvider(new ModBlockStateProvider(evt.getGenerator(), evt.getExistingFileHelper()));
        evt.getGenerator().addProvider(new ModItemModelProvider(evt.getGenerator(), evt.getExistingFileHelper()));
        evt.getGenerator().addProvider(new ModLangProvider(evt.getGenerator()));
        evt.getGenerator().addProvider(provider);
        evt.getGenerator().addProvider(new ModLootTableProvider(evt.getGenerator()));
        evt.getGenerator().addProvider(new ModItemTagProvider(evt.getGenerator(),provider));
        evt.getGenerator().addProvider(new ModRecipeProvider(evt.getGenerator()));
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        registerEntityAttributes();
        //EntitySpawnPlacementRegistry.register(UAMEntities.HONDURAN_WHITE_BAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        //EntitySpawnPlacementRegistry.register(UAMEntities.VINE_SNAKE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.BLACK_DIAMOND_STINGRAY.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(UAMEntities.GREATER_PRAIRIE_CHICKEN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.TOMATO_FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.SOUTHERN_RIGHT_WHALE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SouthernRightWhaleEntity::canWhaleSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.FLASHLIGHT_FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlashlightFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(UAMEntities.HUMPHEAD_PARROTFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HumpheadParrotfishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(UAMEntities.MUSK_OX.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.MARINE_IGUANA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MarineIguanaEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.PLATYPUS.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.BANANA_SLUG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BananaSlugEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(UAMEntities.ELEPHANTNOSE_FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(UAMEntities.PACMAN_FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
    }

    private void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(UAMEntities.HONDURAN_WHITE_BAT.get(), HonduranWhiteBatEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.VINE_SNAKE.get(), VineSnakeEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.BLACK_DIAMOND_STINGRAY.get(), BlackDiamondStingrayEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.TOMATO_FROG.get(), TomatoFrogEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.SOUTHERN_RIGHT_WHALE.get(), SouthernRightWhaleEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.GREATER_PRAIRIE_CHICKEN.get(), GreaterPrairieChickenEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.FLASHLIGHT_FISH.get(), AbstractFishEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(UAMEntities.HUMPHEAD_PARROTFISH.get(), HumpheadParrotfishEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.MUSK_OX.get(), MuskOxEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.BANANA_SLUG.get(), BananaSlugEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.MARINE_IGUANA.get(), MarineIguanaEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.PLATYPUS.get(), PlatypusEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(UAMEntities.ELEPHANTNOSE_FISH.get(), AbstractFishEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(UAMEntities.PACMAN_FROG.get(), PacmanFrogEntity.createAttributes().create());
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEventHandler.init();
    }

    /*
    Does work:
    - Black Diamond Stingray
    - Tomato Frog
    - Greater Prairie Chicken
    - Musk Ox
    - Flashlight Fish
    - Marine Iguana
    - Banana Slug
    - Platypus
    - Elephantnose Fish

    Doesn't work:
    - Southern Right Whale
    - Humphead Parrotfish
    */

    @SubscribeEvent
    public static void registerBiomes(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.JUNGLE) {
            //event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.HONDURAN_WHITE_BAT.get(), 1, 2, 3));
            //event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.VINE_SNAKE.get(), 2, 1, 1));
            event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.BLACK_DIAMOND_STINGRAY.get(), 2, 1, 1));
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.TOMATO_FROG.get(), 3, 1, 2));
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(UAMEntities.ELEPHANTNOSE_FISH.get(), 1, 1, 5));
        }

        if (Biomes.SUNFLOWER_PLAINS.getLocation().equals(event.getName())) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.GREATER_PRAIRIE_CHICKEN.get(), 15, 4, 4));
        }

        if (event.getCategory() == Biome.Category.ICY) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.MUSK_OX.get(), 1, 2, 4));
        }

        if (Biomes.COLD_OCEAN.getLocation().equals(event.getName())) {
            event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.SOUTHERN_RIGHT_WHALE.get(), 15, 2, 4));
        }

        if (Biomes.WARM_OCEAN.getLocation().equals(event.getName())) {
            event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.HUMPHEAD_PARROTFISH.get(), 150, 1, 3));
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(UAMEntities.FLASHLIGHT_FISH.get(), 10, 4, 8));
        }

        if (Biomes.STONE_SHORE.getLocation().equals(event.getName())) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.MARINE_IGUANA.get(), 15, 4, 6));
        }

        if (Biomes.GIANT_TREE_TAIGA.getLocation().equals(event.getName()) || Biomes.GIANT_TREE_TAIGA_HILLS.getLocation().equals(event.getName()) || Biomes.GIANT_SPRUCE_TAIGA_HILLS.getLocation().equals(event.getName()) || Biomes.GIANT_SPRUCE_TAIGA.getLocation().equals(event.getName())) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.BANANA_SLUG.get(), 35, 1, 1));
        }

        if (Biomes.SWAMP.getLocation().equals(event.getName()) || Biomes.SWAMP_HILLS.getLocation().equals(event.getName())) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.PLATYPUS.get(), 5, 1, 1));
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(UAMEntities.PACMAN_FROG.get(), 2, 1, 2));
        }
    }

    @SubscribeEvent
    public static void spawnEntity(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof WolfEntity) {
            ((WolfEntity) entity).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((MobEntity) entity, MuskOxEntity.class, true));
        }
    }

    public final static ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UAMItems.MARINE_IGUANA_EGG.get());}
    };
}
