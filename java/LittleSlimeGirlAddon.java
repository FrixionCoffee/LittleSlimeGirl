import java.util.NoSuchElementException;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * minecraft1.10用は@Mod()をコメントアウトする minecraft1.12.2のみ
 */
@Mod(
    modid = LittleSlimeGirlAddon.MOD_ID,
    name = LittleSlimeGirlAddon.MOD_NAME,
    version = LittleSlimeGirlAddon.VERSION
)
public class LittleSlimeGirlAddon {

  public static final String MOD_ID = "little_slime_girl_addon";
  public static final String MOD_NAME = MOD_ID;
  public static final String VERSION = "2019.2-1.3.1";
  public static Item.ToolMaterial toolMaterial;

  @Mod.Instance(MOD_ID)
  public static LittleSlimeGirlAddon INSTANCE;

  @Mod.EventHandler
  public void preinit(FMLPreInitializationEvent event) {
    try {
      toolMaterial = EnumHelper.addToolMaterial("SlimeType", 1, 256, 0.5f, 1F, 15);
    } catch (LoaderException | NoSuchMethodError | NoSuchElementException | NullPointerException ignored) {

    }

  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event) {
  }

  @Mod.EventHandler
  public void postinit(FMLPostInitializationEvent event) {
  }

  @GameRegistry.ObjectHolder(MOD_ID)
  public static class Blocks {

  }

  @GameRegistry.ObjectHolder(MOD_ID)
  public static class Items {

    public static final SlimeSword SLIME_SWORD = null;
  }

  @Mod.EventBusSubscriber
  public static class ObjectRegistryHandler {

    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> event) {
      try {
        event.getRegistry().register(
            new SlimeSword(toolMaterial, "slime_sword").setRegistryName(MOD_ID, "slime_sword"));
      } catch (LoaderException | NoSuchMethodError | NoSuchElementException | NullPointerException ignored) {
      }
    }

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
      try {
        final String INVENTORY = "inventory";
        ModelLoader.setCustomModelResourceLocation(Items.SLIME_SWORD, 0,
            new ModelResourceLocation(Items.SLIME_SWORD.getRegistryName(), INVENTORY));
      } catch (LoaderException | NoSuchMethodError | NoSuchElementException | NullPointerException ignored) {

      }

    }
  }
}

class SlimeSword extends ItemSword {

  /**
   * forge2768でItem.setUnlocalizedName()が動く理由がわからない 2768ではItem.setTranslationKey()だったはず
   * setUnlocalizedName()を呼べないときNoSuchMethodErrorが投げられるはずなので握りつぶす 握りつぶしたときはitemの名前がitem.null.nameになるはずなのでlangファイルに記述して無理やり対応する
   *
   * @param material 材質
   * @param transfer 名前
   */
  public SlimeSword(ToolMaterial material, String transfer) {
    super(material);

    try {
      this.setUnlocalizedName(transfer);
    } catch (NoSuchMethodError | NoSuchElementException | NullPointerException ignored) {

    }

  }

  /**
   * 攻撃した対象に2秒間移動速度低下Lv32のポーションエフェクトを付与する またattackerがEntityPlayerを継承していればリトルスライムガールのstaticメソッドを通して
   * リトルスライムガールに伝える
   * <p>
   * targetは無条件にリトルスライムガールに伝える
   *
   * @param stack    不明
   * @param target   攻撃されたエンティンティ
   * @param attacker 攻撃をしたエンティンティ
   * @return 不明
   */
  @Override
  public boolean hitEntity(ItemStack stack, EntityLivingBase target,
      @Nonnull EntityLivingBase attacker) {
    try {
      PotionFactory potionFactory = new PotionFactory();
      PotionEffect potionEffect = potionFactory.makePotionEffect(Potion.getPotionById(18), 2, 32);
      target.addPotionEffect(potionEffect);
    } catch (NullPointerException | NoSuchElementException ignored) {

    }

    try {
      if (attacker instanceof EntityPlayer) {
        ModelLittleMaid_LittleSlimeGirl.setMyMaster((EntityPlayer) attacker);
      }

      ModelLittleMaid_LittleSlimeGirl.setEffectTarget(target);

    } catch (NoSuchMethodError | NoSuchElementException | NullPointerException ignored) {
      /*なにかしらの理由でプレイヤーが取得できなくなっている場合は処理自体行わない*/
    }
    return super.hitEntity(stack, target, attacker);
  }


}
