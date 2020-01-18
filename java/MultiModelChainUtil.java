public interface MultiModelChainUtil extends MultiModelCacheUtil {

  /**
   * テスクチャのオフセットを1ずつずらす(1マス右下に移る)
   */
  default void chainBox(float axisX, float axisY, float axisZ,
      int width, int height, int depth) {
    cacheBox(axisX, axisY, axisZ, width, height, depth);
    cacheOffset(1, 1);
  }

  default void chainPlate(float axisX, float axisY, float axisZ,
      int width, int height,
      int facePlane) {
    cachePlate(axisX, axisY, axisZ, width, height, facePlane);
    cacheOffset(1, 1);
  }

}
