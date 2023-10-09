package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZomboRenderer extends MobRenderer<Zombo, ZomboModel<Zombo>> {

    public ZomboRenderer(EntityRendererProvider.Context provider) {
        super(provider, new ZomboModel<>(provider.bakeLayer(ZomboModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombo p_114482_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/zombo.png");
    }

}
