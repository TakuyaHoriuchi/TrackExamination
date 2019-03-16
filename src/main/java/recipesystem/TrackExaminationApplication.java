package recipesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーション実行クラス.
 */
@SpringBootApplication
public class TrackExaminationApplication {

  /**
   * メインメソッド.
   * @param args 起動する際に設定可能な引数.
   */
  public static void main(String[] args) {
    SpringApplication.run(TrackExaminationApplication.class, args);
  }

}

// TODO DBをセットしてDEPENDENCYを注入
// TODO 不要Stubを４つ削除
// TODO ERRORレベルのLOGの埋込
// TODO INFOレベルのLOGの埋込







