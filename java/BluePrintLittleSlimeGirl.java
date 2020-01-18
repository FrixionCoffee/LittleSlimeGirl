import java.util.ArrayList;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

/**
 * 汎用性がないのでリトルスライムガール以外がこのインターフェースを実装することは推奨しない
 */
public interface BluePrintLittleSlimeGirl extends MultiModelChainUtil {

  default void initOutsideGel(ModelRenderer Skirt, ModelRenderer bipedHead, ModelRenderer gel1,
      ModelRenderer gel2) {
    setCache(gel1);
    cacheBox(8, 8, 8, 4, 2, 6);
    cacheChild(Skirt);

    setCache(gel2);
    cacheSquare(0, -17, 1, 3);
    cacheOffset(1, 1);
    cacheSquare(20, 20, 20, 5);
    cacheOffset(1, 1);
    cacheSquare(-13, -5, -3, 1);
    cacheChild(bipedHead);
    endCache();

  }

  default void initBodyGel(ModelRenderer bipedBody, ModelRenderer bodyGel1) {
    setCache(bodyGel1);
    cacheBox(2, 0.5f, 0, 3, 5, 3);
    cacheChild(bipedBody);
    endCache();
  }

  default void initFoolHair(ModelRenderer bipedHead, ModelRenderer foolHair,
      ModelRenderer foolHairWide) {

    setCache(foolHair);
    cacheAddFoolHairOffsetPlusOne(1, 1, -5);
    cacheAddFoolHairOffsetPlusOne(1, 0, -3);

    cacheAddFoolHairOffsetPlusOne(-4, 0, 1);
    cacheAddFoolHairOffsetPlusOne(-4, -1, 3);

    cacheAddFoolHairOffsetPlusOne(-5, -2, 2);
    cacheAddFoolHairOffsetPlusOne(-5, -3, 1);

    cacheDegX(15);
    cacheChild(bipedHead);

    setCache(foolHairWide);
    cacheAddFoolHairWideOffsetPlusOne(1, 1, -5);
    cacheAddFoolHairWideOffsetPlusOne(1, 0, -3);

    cacheAddFoolHairWideOffsetPlusOne(-4, 0, 1);
    cacheAddFoolHairWideOffsetPlusOne(-4, -1, 3);

    cacheAddFoolHairWideOffsetPlusOne(-5, -2, 2);
    cacheAddFoolHairWideOffsetPlusOne(-5, -3, 1);

    cacheDegX(15);
    cacheChild(bipedHead);

    endCache();
  }

  default void initSideTail(ModelRenderer bipedHead, ModelRenderer sideTail) {
    setCache(sideTail);

    float tempZ = -2;
    cacheAddTowerOffsetPlusOne(5, getHeadLengthAxis(), tempZ, 4);
    cacheAddTowerOffsetPlusOne(6, getHeadLengthAxis(), tempZ, 3);
    cacheAddTowerOffsetPlusOne(7, getHeadLengthAxis(), tempZ, 8);

    tempZ = -1;
    cacheAddTowerOffsetPlusOne(5, getHeadLengthAxis() - 1, tempZ, 9);
    cacheAddTowerOffsetPlusOne(6, getHeadLengthAxis() + 1, tempZ, 10);
    cacheAddTowerOffsetPlusOne(7, getHeadLengthAxis() + 1, tempZ, 14);

    tempZ = 0;
    cacheAddTowerOffsetPlusOne(4, getHeadLengthAxis(), tempZ, 7);
    cacheAddTowerOffsetPlusOne(5, getHeadLengthAxis(), tempZ, 8);
    cacheAddTowerOffsetPlusOne(6, getHeadLengthAxis(), tempZ, 12);

    tempZ = 1;
    cacheAddTowerOffsetPlusOne(4, getHeadLengthAxis(), tempZ, 3);
    cacheAddTowerOffsetPlusOne(5, getHeadLengthAxis(), tempZ, 5);

    cacheAddTowerOffsetPlusOne(6, getHeadLengthAxis(), tempZ, 9);
    cacheAddTowerOffsetPlusOne(7, getHeadLengthAxis(), tempZ, 13);

    cacheChild(bipedHead);
    endCache();
  }

  default void cacheAddFoolHair(float offsetY, float axisZ, int height) {
    cachePlate(0, getHeadLengthAxis() + offsetY, axisZ, 1, height, 5);
  }

  default void cacheAddFoolHairOffsetPlusOne(float offsetY, float axisZ, int height) {
    cacheAddFoolHair(offsetY, axisZ, height);
    cacheOffset(1, 1);
  }

  default void cacheAddFoolHairWide(float offsetY, float axisX, int height) {
    cachePlate(axisX, getHeadLengthAxis() + offsetY, 0, 1, height, 0);
  }

  default void cacheAddFoolHairWideOffsetPlusOne(float offsetY, float axisX, int height) {
    cacheAddFoolHairWide(offsetY, axisX, height);
    cacheOffset(1, 1);
  }

  default void cacheAddTowerOffsetPlusOne(float axisX, float axisY, float axisZ, int height) {
    cacheTower(axisX, axisY, axisZ, height);
    cacheOffset(1, 1);
  }

  ArrayList<ModelRenderer> getOriginPartsList();

  float getHeadLengthAxis();

}
