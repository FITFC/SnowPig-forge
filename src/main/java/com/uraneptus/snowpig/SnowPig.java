package com.uraneptus.snowpig;

import com.uraneptus.snowpig.client.entity.render.SnowPigRender;
import com.uraneptus.snowpig.common.capabilities.SPEntityCap;
import com.uraneptus.snowpig.core.registry.SPEntityTypes;
import com.uraneptus.snowpig.core.registry.SPItems;
import com.uraneptus.snowpig.core.registry.SPLootPoolEntryTypes;
import com.uraneptus.snowpig.core.registry.SPSounds;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(com.uraneptus.snowpig.SnowPig.MOD_ID)
@Mod.EventBusSubscriber(modid = com.uraneptus.snowpig.SnowPig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowPig
{
    public static final String MOD_ID = "snowpig";

    public SnowPig() {
        IEventBus event_bus = FMLJavaModLoadingContext.get().getModEventBus();
        event_bus.addListener(this::setupClient);

        SPSounds.SOUNDS.register(event_bus);
        SPItems.ITEMS.register(event_bus);
        SPEntityTypes.ENTITY_TYPES.register(event_bus);
        SPLootPoolEntryTypes.LOOT_ENTRY.register(event_bus);

        MinecraftForge.EVENT_BUS.register(this);

    }
    public void setupClient(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypes.SNOW_PIG.get(), SnowPigRender::new);
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SPEntityTypes.SNOW_PIG.get(), com.uraneptus.snowpig.common.entities.SnowPig.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SPEntityCap.class);
    }
}
