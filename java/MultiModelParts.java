import javax.annotation.Nonnull;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

/**
 * このインターフェースはテスクチャの場所にほとんど意味がないモデルでなければならない もしくはテスクチャを極端に大きくしてUVの衝突が起きないようにするか
 */
public interface MultiModelParts extends MultiModelChainUtil {

  /**
   * テスト用 相対位置を渡す 呼び出し元がaddChild()とsetRotate()すること こっち側でcacheChild()をするとオーバロードするメソッドが多くなるので実装しない
   * <p>
   */
  default ModelRenderer foolHair1(@Nonnull ModelRenderer targetParts,
      float axisX, float axisY, float axisZ) {
    setCache(targetParts);
    chainPlate(axisX - 1.0F, axisY - 2.0F, axisZ - 0.0F, 1, 2, 5);
    chainPlate(axisX - 1.0F, axisY - 3.0F, axisZ - 1.0F, 1, 1, 5);
    chainPlate(axisX - 1.0F, axisY - 4.0F, axisZ - 2.0F, 1, 1, 5);
    chainPlate(axisX - 1.0F, axisY - 3.0F, axisZ - 3.0F, 1, 1, 5);
    return getCache();
  }

}
