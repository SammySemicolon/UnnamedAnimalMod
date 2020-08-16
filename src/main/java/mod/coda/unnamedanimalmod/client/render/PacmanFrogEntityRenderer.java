package mod.coda.unnamedanimalmod.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.coda.unnamedanimalmod.Main;
import mod.coda.unnamedanimalmod.client.model.PacmanFrogEntityModel;
import mod.coda.unnamedanimalmod.entity.HumpheadParrotfishEntity;
import mod.coda.unnamedanimalmod.entity.PacmanFrogEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class PacmanFrogEntityRenderer extends MobRenderer<PacmanFrogEntity, PacmanFrogEntityModel<PacmanFrogEntity>> {
    protected static final ResourceLocation ADULT = new ResourceLocation(Main.MOD_ID, "textures/entity/pacman_frog.png");
    protected static final ResourceLocation CHILD = new ResourceLocation(Main.MOD_ID, "textures/entity/tadpole.png");
    private final PacmanFrogEntityModel adult;
    private final PacmanFrogEntityModel child;

    public PacmanFrogEntityRenderer(EntityRendererManager manager) {
        super(manager, new PacmanFrogEntityModel.Adult(), 0.2f);
        adult = entityModel;
        child = new PacmanFrogEntityModel.Child();
    }

    @Override
    public void render(PacmanFrogEntity entity, float yaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        entityModel = entity.isChild() ? child : adult;
        super.render(entity, yaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(PacmanFrogEntity entity) {
        if (entity.isChild()) {
            return CHILD;
        } else {
            return ADULT;
        }
    }

    protected void applyRotations(PacmanFrogEntity entity, MatrixStack matrix, float var1, float var2, float var3) {
        super.applyRotations(entity, matrix, var1, var2, var3);
        if(entity.isChild()) {
            float rotate = 4.3F * MathHelper.sin(0.6F * var1);
            matrix.rotate(Vector3f.YP.rotationDegrees(rotate));
            if (!entity.isInWater()) {
                matrix.translate(0.0, 0.10000000149011612D, 0.0D);
                matrix.rotate(Vector3f.ZP.rotationDegrees(90.0F));
            }
        }
    }
}