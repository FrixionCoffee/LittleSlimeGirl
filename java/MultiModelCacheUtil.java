import javax.annotation.Nonnull;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

/**
 * リトルメイドさんのマルチモデル作成用のメソッドを集めたインターフェース アノテーションは省略している モルチモデルの親クラスや子クラスでメソッド名の衝突が起きるといろいろ面倒なので注意
 * cacheを使うものと使わない基本的なメソッドでインターフェースを分けた どんどんインターフェースが肥大化している！
 * <p>
 * 実装例 public class ModelLittleMaid_example extends ModelLittleMaid_SR2 implements
 * MultiModelCacheUtil{ public ModelRenderer ribbon; private ModelRenderer cache;
 * <p>
 * コンストラクター(){};*3
 * <p>
 * イニットモデル(){};
 * <p>
 * public ArrayList<ModelRenderer> getOriginPartsList(){ ArrayList<ModelRenderer> partsList = new
 * ArrayList; partsList.add(ribbon);
 * <p>
 * return partsList; }
 * <p>
 * public ModelRenderer getCache(){ return this.cache; }
 * <p>
 * public void setCache(ModelRenderer cache){ this.cache = cache; }
 * <p>
 * public void endCache(){ this.cache = null; } }
 */

public interface MultiModelCacheUtil extends MultiModelUtil {

  /**
   * @return メイドさんのメンバ変数ModelRenderer型のcacheを返すように実装する
   * <p>
   * これはダメ(故意にnullを返却しない) public void getCache(){ return null; }
   */
  ModelRenderer getCache();

  void setCache(ModelRenderer cacheParts);

  void endCache();

  /*
  ここから下がキャッシュを使うメソッド
  このインターフェースを実装したマルチモデルクラスが使うか
  このインターフェースを継承したインターフェースがモデルパーツ記述に用いる
  どれもテスクチャの場所が正確に要求されるパーツには向かない
  リトルスライムガールのようなテクスチャマッピングを気にする必要がないモデル前提
   */

  default void cacheSquare(float axisX, float axisY, float axisZ, int commonSize) {
    addSquare(getCache(), axisX, axisY, axisZ, commonSize);
  }

  default void cacheTower(float axisX, float axisY, float axisZ, int height) {
    addTower(getCache(), axisX, axisY, axisZ, height);
  }

  default void cacheBox(float axisX, float axisY, float axisZ,
      int width, int height, int depth) {
    addBox(getCache(), axisX, axisY, axisZ, width, height, depth);
  }

  default void cacheChild(@Nonnull ModelRenderer parentParts) {
    addChild(parentParts, getCache());
  }

  default void cacheDeg(float angleX, float angleY, float angleZ) {
    rotateDeg(getCache(), angleX, angleY, angleZ);
  }

  default void cacheDegX(float angleX) {
    getCache().setRotateAngleDegX(angleX);
  }

  default void cacheDegY(float angleY) {
    getCache().setRotateAngleDegY(angleY);
  }

  default void cacheDegZ(float angleZ) {
    getCache().setRotateAngleDegZ(angleZ);
  }

  default void cachePlate(float axisX, float axisY, float axisZ,
      int width, int height,
      int facePlane) {
    addPlate(getCache(), axisX, axisY, axisZ, width, height, facePlane);
  }

  default void cacheOffset(int offsetX, int offsetY) {
    textureOffset(getCache(), offsetX, offsetY);
  }

}