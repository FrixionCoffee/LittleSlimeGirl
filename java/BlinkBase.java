import javax.annotation.Nonnull;
import net.blacklab.lmr.entity.maidmodel.IModelCaps;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

/**
 * ModelLittleMaidBaseを継承した時のまばたき実装用インターフェース
 * <p>
 * このインターフェースを実装すると、他クラスを継承しつつModelLittleMaid_SR2と同等の機能を持つことができる 最終的にはModelLittleMaidBaseを継承して様々なパーツを自由に簡単に扱えるようにしたい
 * 下のほうにパッケージプライベートの実装例がある
 * <p>
 * まばたきに必要なコードはModelLittleMaid_SR2から持ってきている MMM氏をはじめとするlittleMaidMobに関わった皆様に感謝します
 * <p>
 * Thanks to all "littleMaidMob" developerS
 */

public interface BlinkBase {

  int RIGHT_EYE_X = 32;
  int LEFT_EYE_X = 42;
  int EYE_Y = 19;

  /**
   * @param bipedHead クラスのメンバ変数のbipedHeadをそのまま渡す
   * @param eyeR      Nonnull
   * @param eyeL      Nonnull
   * @param pSize     psizeをそのまま入れる
   */
  default void initEyeSR2(@Nonnull ModelRenderer bipedHead, @Nonnull ModelRenderer eyeR,
      @Nonnull ModelRenderer eyeL, float pSize) {
    eyeR.addPlate(-4.0F, -5.0F, -4.001F, 4, 4, 0, pSize);
    eyeL.addPlate(0.0F, -5.0F, -4.001F, 4, 4, 0, pSize);
    bipedHead.addChild(eyeR);
    bipedHead.addChild(eyeL);
  }

  default void initEyeSetLivingAnimations(@Nonnull ModelRenderer eyeR, @Nonnull ModelRenderer eyeL,
      int entityTicksExisted, float pRenderPartialTicks, float entityIdFactor) {

    float f3 = (float) entityTicksExisted + pRenderPartialTicks + entityIdFactor;

    if (0.0F > net.blacklab.lmr.entity.maidmodel.ModelBase.mh_sin(f3 * 0.05F)
        + net.blacklab.lmr.entity.maidmodel.ModelBase.mh_sin(f3 * 0.13F)
        + net.blacklab.lmr.entity.maidmodel.ModelBase.mh_sin(f3 * 0.7F) + 2.55F) {
      eyeR.setVisible(true);
      eyeL.setVisible(true);
    } else {
      eyeR.setVisible(false);
      eyeL.setVisible(false);
    }
  }

  default void initEyeRotationAngles(ModelRenderer eyeR, ModelRenderer eyeL, IModelCaps pEntityCaps,
      boolean aimedBow) {

    if (aimedBow) {
      if (net.blacklab.lmr.entity.maidmodel.ModelCapsHelper.getCapsValueInt(pEntityCaps, 293)
          == 0) {
        eyeL.setVisible(true);
      } else {
        eyeR.setVisible(true);
      }
    }

  }

}

/*

実装例 このクラスは使われないが
LMMが読み取ろうとして無駄な時間を消費する可能性があるのでコメントアウトするかも

import net.blacklab.lmr.entity.maidmodel.IModelCaps;
import net.blacklab.lmr.entity.maidmodel.ModelLittleMaidBase;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

class ClassExample extends ModelLittleMaidBase implements BlinkBase {

  public ModelRenderer eyeR = new ModelRenderer(this, RIGHT_EYE_X, EYE_Y);
  public ModelRenderer eyeL = new ModelRenderer(this, LEFT_EYE_X, EYE_Y);

  public ClassExample() {
  }

  public ClassExample(float psize) {
    super(psize);
  }

  public ClassExample(float psize, float pyoffset, int pTextureWidth, int pTextureHeight) {
    super(psize, pyoffset, pTextureWidth, pTextureHeight);
  }

  @Override
  public void initModel(float psize, float pyoffset) {

    super.initModel(psize, pyoffset);
    initEyeSR2(this.bipedHead, eyeR, eyeL, psize);

  }

  @Override
  public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3,
      float pRenderPartialTicks) {

    super.setLivingAnimations(pEntityCaps, par2, par3, pRenderPartialTicks);
    initEyeSetLivingAnimations(eyeR, eyeL, this.entityTicksExisted, pRenderPartialTicks, this.entityIdFactor);

  }

  @Override
  public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw,
      float pHeadPitch, float par6, IModelCaps pEntityCaps) {

    super.setRotationAngles(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
    initEyeRotationAngles(eyeR, eyeL, pEntityCaps, this.aimedBow);

  }

  @Override
  public String getUsingTexture() {
    return null;
  }
}
*/

