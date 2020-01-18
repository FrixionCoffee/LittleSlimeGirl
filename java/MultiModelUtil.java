import java.util.ArrayList;
import javax.annotation.Nonnull;
import net.blacklab.lmr.entity.maidmodel.ModelRenderer;

public interface MultiModelUtil {

  /**
   * 追加するパーツがない場合はlist.addをせずにそのまま空っぽのArrayListを返却する 呼び出す側で空チェックをする
   *
   * @return 追加した独自のModelRendererだけが入ったArrayListを返却する cacheは入れない
   */
  ArrayList<ModelRenderer> getOriginPartsList();

  /**
   * このメソッドをオーバライドしたshowAllParts()で呼ぶだけ public void showAllParts() { super.showAllParts();
   * originPartsSetVisible(true); }
   *
   * @param visible trueを入れる falseを入れると追加パーツがshowAllParts()で非表示になる
   */
  default void originPartsSetVisible(boolean visible) {
    ArrayList<ModelRenderer> partsList = getOriginPartsList();

    if (!partsList.isEmpty()) {

      for (ModelRenderer value : partsList
      ) {
        value.setVisible(visible);
      }

    }
  }

  /**
   * 正方形のModelRender.AddBox()を行う
   *
   * @param childParts 対象パーツ
   * @param axisX      X軸のオフセット値
   * @param axisY      Y軸のオフセット値
   * @param axisZ      Z軸のオフセット値
   * @param commonSize 正方形のサイズ
   */
  default void addSquare(@Nonnull ModelRenderer childParts, float axisX, float axisY, float axisZ,
      int commonSize) {
    childParts.addBox(axisX, axisY, axisZ, commonSize, commonSize, commonSize);
  }

  default void addTower(@Nonnull ModelRenderer childParts, float axisX, float axisY, float axisZ,
      int height) {
    childParts.addBox(axisX, axisY, axisZ, 1, height, 1);
  }

  default void addBox(@Nonnull ModelRenderer childParts, float axisX, float axisY, float axisZ,
      int width, int height, int depth) {
    childParts.addBox(axisX, axisY, axisZ, width, height, depth);
  }

  default void addPlate(@Nonnull ModelRenderer childParts, float axisX, float axisY, float axisZ,
      int width, int height,
      int facePlane) {
    childParts.addPlate(axisX, axisY, axisZ, width, height, facePlane);
  }

  default void addChild(@Nonnull ModelRenderer parentParts, @Nonnull ModelRenderer childParts) {
    parentParts.addChild(childParts);
  }

  /**
   * @deprecated 使い勝手が悪いので使用しないほうが良い 座標を入力することで角度の付いたaddBoxを行うことができる。
   */
  @Deprecated
  default void addLine(@Nonnull ModelRenderer childParts, float startX, float endX, float startY,
      float endY, float axisZ) {
    if (startX > endX || startY > endY) {
      throw new IllegalArgumentException();
    }

    float distanceX = endX - startX;
    float distanceY = endY - startY;
    float b = distanceY / distanceX;
    float nowY = startY;

    for (float i = startX; i < endX; i++) {
      addSquare(childParts, i, nowY, axisZ, 1);
      nowY += b;

    }

  }

  default void rotateDeg(@Nonnull ModelRenderer childParts, float angleX, float angleY,
      float angleZ) {
    childParts.setRotateAngleDeg(angleX, angleY, angleZ);
  }

  default void textureOffset(@Nonnull ModelRenderer childParts, int offsetX, int offsetY) {
    childParts.setTextureOffset(offsetX, offsetY);
  }

  /**
   * 5マスごとに1pxの正方形ボックスを設置する モデル作成の目安用 合計125個のボックスを追加する
   *
   * @param parentParts 追従先　基本的にはbipedBodyでOK
   * @param childParts  適当なModelRenderer型のメンバ変数をインスタンス化してから入れる(ローカル変数NG) grid = new
   *                    ModelRenderer(this,0,0); initGrid(bipedBody,grid);
   */
  @SuppressWarnings("unused")
  default void initGrid(@Nonnull ModelRenderer parentParts, @Nonnull ModelRenderer childParts) {
    customInitGrid(parentParts, childParts, 15, 15, 15);
  }

  /**
   * @param parentParts 基本的にはbipedBodyを指定
   * @param childParts  適当なテストパーツを指定
   * @param endX        > 0
   * @param endY        > 0
   * @param endZ        > 0
   */
  default void customInitGrid(@Nonnull ModelRenderer parentParts, @Nonnull ModelRenderer childParts,
      float endX, float endY, float endZ) {

    if (endX < 0 || endY < 0 || endZ < 0) {
      throw new IllegalArgumentException(
          "MultiModelUtil.customInitGrid:endX < 0 || endY < 0 || endZ < 0");
    }

/*    if (endX % 5 != 0 || endY % 5  != 0|| endZ % 5 != 0){
      throw new IllegalArgumentException(
          "MultiModelUtil.customInitGrid:endX % 5 != 0 || endY % 5  != 0|| endZ % 5 != 0");
    }*/

    for (float x = -endX; x < endX; x += 5) {

      for (float y = -endY; y < endY; y += 5) {

        for (float z = -endZ; z < endZ; z += 5) {

          addSquare(childParts, x, y, z, 1);

        }

      }

    }
    addChild(parentParts, childParts);
  }

}
