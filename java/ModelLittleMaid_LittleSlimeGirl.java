import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.annotation.Nonnull;
import net.blacklab.lmr.entity.maidmodel.IModelCaps;
import net.blacklab.lmr.entity.maidmodel.ModelLittleMaidBase;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

@LittleMaidMobMultiModel(
    modelName = "LittleSlimeGirl",
    supportedVersion = {"1.10", "1.12.2"},
    warning = "LittleMaidMob1.12.2Reengaged-unofficial Not model read",
    modelInformation = "Use it for anything other than commercial use!"
)
public final class ModelLittleMaid_LittleSlimeGirl extends ModelLittleMaidBase implements
    BluePrintLittleSlimeGirl, BlinkBase {

  private static EntityLivingBase effectTarget = null;
  private static EntityPlayer myMaster = null;
  private static boolean haveMaster = false;
  private static boolean haveTarget = false;
  private static boolean nonTroubleFlag = true;

  public ModelRenderer eyeR;
  public ModelRenderer eyeL;

  public ModelRenderer gel1;
  public ModelRenderer gel2;
  public ModelRenderer bodyGel1;
  public ModelRenderer foolHair;
  public ModelRenderer foolHairWide;
  public ModelRenderer sideTail;

  @SuppressWarnings("unused")
  public ModelRenderer testParts = null;

  private ModelRenderer cache = null;

  @SuppressWarnings("unused")
  public ModelLittleMaid_LittleSlimeGirl() {
    super();
  }

  @SuppressWarnings("unused")
  public ModelLittleMaid_LittleSlimeGirl(float partSize) {
    super(partSize);
  }

  @SuppressWarnings("unused")
  public ModelLittleMaid_LittleSlimeGirl(float partsSize, float partsOffsetY, int pTextureWidth,
      int pTextureHeight) {
    super(partsSize, partsOffsetY, pTextureWidth, pTextureHeight);
  }

  static void setMyMaster(EntityPlayer player) {
    myMaster = player;
    haveMaster = true;
  }

  static void setEffectTarget(EntityLivingBase target) {
    effectTarget = target;
    haveTarget = true;
  }

  @Override
  public void initModel(float partsSize, float partsOffsetY) {
    super.initModel(partsSize, partsOffsetY);
    eyeR = newModelRenderer(RIGHT_EYE_X, EYE_Y);
    eyeL = newModelRenderer(LEFT_EYE_X, EYE_Y);

    gel1 = newModelRenderer(32, 13);
    gel2 = newModelRenderer(24, 15);
    bodyGel1 = newModelRenderer(24, 0);
    foolHair = newModelRenderer(28, 5);
    foolHairWide = newModelRenderer(32, 8);
    sideTail = newModelRenderer(20, 0);

    initEyeSR2(bipedHead, eyeR, eyeL, partsSize);
    initOutsideGel(Skirt, bipedHead, gel1, gel2);
    initBodyGel(bipedBody, bodyGel1);
    initFoolHair(bipedHead, foolHair, foolHairWide);
    initSideTail(bipedHead, sideTail);
    /*    initTestParts(20,0);*/
  }

/*  private void initTestParts(int pX, int pY){
    test = new ModelRenderer(this,pX,pY);
    dorsalFin(test,0,-10,0);
    cacheDeg(0,90,0);
    cacheChild(bipedBody);
  }*/

  @Override
  public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3,
      float pRenderPartialTicks) {
    super.setLivingAnimations(pEntityCaps, par2, par3, pRenderPartialTicks);
    initEyeSetLivingAnimations(eyeR, eyeL, entityTicksExisted, pRenderPartialTicks, entityIdFactor);
  }

  @Override
  public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw,
      float pHeadPitch, float par6, IModelCaps pEntityCaps) {
    super.setRotationAngles(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
    initEyeRotationAngles(eyeR, eyeL, pEntityCaps, aimedBow);

    PotionFactory potionFactory = new PotionFactory();

    addPotion:
    try {
      if (!nonTroubleFlag) {
        break addPotion;
      }

      if (!isWait && haveMaster) {
        myMasterAddPotion(potionFactory.makePotionEffect(Potion.getPotionById(11), 600, 1));
      }

      if (!isWait && haveTarget) {
        targetAddPotion(potionFactory.makePotionEffect(Potion.getPotionById(18), 5, 1));
      }

    } catch (NullPointerException | NoSuchElementException | NoSuchMethodError ignored) {
      /*これらの例外、エラーをキャッチしたらtryをすぐ抜けるようにnotTroubleFlagにfalseを代入する
       * */
      nonTroubleFlag = false;
    }

  }

  @Override
  public @Nonnull
  ArrayList<ModelRenderer> getOriginPartsList() {
    final ArrayList<ModelRenderer> list = new ArrayList<>();
    list.add(gel1);
    list.add(gel2);
    list.add(bodyGel1);
    list.add(foolHair);
    list.add(foolHairWide);
    list.add(sideTail);
    return list;
  }

  @Override
  public void showAllParts() {
    super.showAllParts();
    originPartsSetVisible(true);
  }

  @Override
  public ModelRenderer getCache() {
    return cache;
  }

  @Override
  public void setCache(ModelRenderer cacheParts) {
    cache = cacheParts;
  }

  @Override
  public void endCache() {
    cache = null;
  }

  /**
   * メイドさんの頭長Y軸位置　ちょうど飛び出る位置 上に伸ばすなら+1する　下方向に伸ばすならそのまま
   *
   * @return メイドさんの頭長Y軸位置
   */
  @Override
  public float getHeadLengthAxis() {
    return -9f;
  }

  private void myMasterAddPotion(PotionEffect potionEffect)
      throws NullPointerException, NoSuchElementException {
    myMaster.addPotionEffect(potionEffect);
    haveMaster = false;
  }

  private void targetAddPotion(PotionEffect potionEffect)
      throws NullPointerException, NoSuchElementException {
    effectTarget.addPotionEffect(potionEffect);
    haveTarget = false;
  }

  private @Nonnull
  ModelRenderer newModelRenderer(int pixelX, int pixelY) {
    return new ModelRenderer(this, pixelX, pixelY);
  }
}