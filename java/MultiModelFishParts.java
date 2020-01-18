import javax.annotation.Nonnull;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

/**
 * リトルシャークガール用のメソッド インターフェースを実装して処理を丸投げする 呼び出し元がaddChild()とsetRotate()すること
 * いつか作るリトルハンマーヘッドシャークガールとか海系のマルチモデルのパーツ処理をすべてこのインターフェースに丸投げする予定
 */

public interface MultiModelFishParts extends MultiModelChainUtil {

  /**
   * 大きめの背びれを追加する 先頭にsetCache()がないがhandFin()でsetCache()が呼ばれているので問題なし
   */
  default void dorsalFin(@Nonnull ModelRenderer targetParts,
      float axisX, float axisY, float axisZ) {
    handFin(targetParts, axisX, axisY, axisZ);
    chainBox(axisX - 15.0F, axisY - 6.0F, axisZ - 0.0F, 4, 1, 1);
    chainBox(axisX - 15.0F, axisY - 7.0F, axisZ - 0.0F, 3, 1, 1);
    chainBox(axisX - 15.0F, axisY - 8.0F, axisZ - 0.0F, 1, 1, 1);
  }


  /**
   * 手にくっつける用のヒレを追加する
   */
  default void handFin(@Nonnull ModelRenderer targetParts,
      float axisX, float axisY, float axisZ) {
    setCache(targetParts);
    chainBox(axisX - 9.0F, axisY - 1.0F, axisZ - 0.0F, 9, 1, 1);
    chainBox(axisX - 10.0F, axisY - 2.0F, axisZ - 0.0F, 8, 1, 1);
    chainBox(axisX - 12.0F, axisY - 3.0F, axisZ - 0.0F, 7, 1, 1);
    chainBox(axisX - 14.0F, axisY - 4.0F, axisZ - 0.0F, 7, 1, 1);
    chainBox(axisX - 14.0F, axisY - 5.0F, axisZ - 0.0F, 6, 1, 1);
  }

}
