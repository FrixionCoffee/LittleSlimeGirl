import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * このアノテーションに特に意味はない そのうちリフレクションを使って連携をとれるようにしてみたい
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface LittleMaidMobMultiModel {

  String modelName();

  String[] supportedVersion();

  String warning();

  String modelInformation();
}