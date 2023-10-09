package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ZomboRenderFactory implements EntityRendererProvider<Zombo> {

    public static ZomboRenderFactory INSTANCE = new ZomboRenderFactory();

    @Override
    public EntityRenderer<Zombo> create(EntityRendererProvider.Context manager) {
        return new ZomboRenderer(manager);
    }
}
