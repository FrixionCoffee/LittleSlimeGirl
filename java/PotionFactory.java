import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionFactory {

    /**
     * @param potion      Potion.getPotionById(n)で入れる
     * @param second      秒数を指定するTick変換はconvertSecondsAndTicks()がやるので問題なし
     * @param potionLevel ポーションのレベルをそのまま指定する
     * @return 引数にあったポーションエフェクトを作成し返す
     * <p>
     * Potion.getPotionById(n)List 1 速度上昇 2 速度低下 3 マイニング速度上昇 4 マイニング速度低下 5 攻撃力上昇 6 治癒 7 負傷 8 ジャンプ力上昇 9
     * 吐き気 10 再生 11 耐性 12 耐火 13 水中呼吸 14 透明 15 盲目 16 暗視 17 空腹 18 弱化 19 毒 20 ウィザー 21 赤色HPを増やすやつ 22
     * 黄色HPを増やすやつ 23 逆空腹？ 24 発光 25 浮遊 26 幸運 27 悪運
     */
    PotionEffect makePotionEffect(Potion potion, int second, int potionLevel) {
        if (potion == null) {
            throw new IllegalArgumentException("PotionFactory.makePotionEffect() potion is Null!");
        }
        if (potionLevel < 1) {
            throw new IllegalArgumentException(
                "PotionFactory.makePotionEffect() potionLevel is Minus Number!");
        }
        return new PotionEffect(potion, convertSecondsAndTicks(second), potionLevel - 1, false,
            false);
    }

    private int convertSecondsAndTicks(int second) {
        return second * 20;
    }
}
