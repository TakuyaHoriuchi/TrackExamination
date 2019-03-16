package recipesystem.common;

/**
 * RecipeSystem 共通の定数クラス.
 */
public final class Constants {

  /** レシピ新規作成成功時のメッセージ. */
  public static final String CREATE_SUCCESS = "Recipe successfully created!";
  
  /** レシピ新規作成失敗時のメッセージ. */
  public static final String CREATE_FAILED = "Recipe creation failed!";
  
  /** レシピ作成に必要な項目. */
  public static final String CREATE_REQUIRED = "title, making_time, serves, ingredients, cost";

  /** レシピ新規作成失敗時のログメッセージ. */
  public static final String LOG_CREATE_FAILED = "レシピの新規作成に失敗しました。";
  
  /** レシピ削除成功時のメッセージ. */
  public static final String DELETE_SUCCESS = "Recipe successfully removed!";
  
  /** レシピが見つけられなかった時のメッセージ. */
  public static final String NOT_FOUND_RECIPE = "No Recipe found";
  
  /** レシピ削除に失敗した時のログメッセージ. */
  public static final String LOG_DELETE_FAIL = "レシピの削除に失敗しました。";
  
  /** 指定IDのレシピ取得時のメッセージ. */
  public static final String READ_SUCCESS_BY_ID = "Recipe details by id";
  
  /** 指定IDのレシピ取得に失敗した時のログメッセージ. */
  public static final String LOG_RECIPE_IS_NOT_FOUND_AT_ID = "指定されたIDのレシピが見つかりませんでした。";
  
  /** レシピ全件取得に失敗した時のログメッセージ. */
  public static final String LOG_READALL_FAIL = "レシピ情報取得に失敗しました。";
  
  /** 想定外のERRORが起きた時のメッセージ. */
  public static final String UNEXPECTED_ERROR_IS_OCCURED = "Unexpected error is occured.";

  /** 更新に失敗した時のログメッセージ. */
  public static final String LOG_UPDATE_FAIL = "レシピの更新に失敗しました。";
  
  /** 更新に成功した時のメッセージ. */
  public static final String UPDATE_SUCCESS = "Recipe successfully updated!";

  /** システムエラーログメッセージ. */
  public static final String LOG_UNEXPECTED_ERROR = "意図しないシステムエラーが発生しました。";
  
  /** システムエラーメッセージ. */
  public static final String UNEXPECTED_ERROR = "システムエラーが発生しました.";
  
  /** レシピ作成の情報不足であることのログメッセージ. */
  public static final String LOG_BE_SHORT_OF_CREATE_DATA = "レシピ作成に必要な情報が不足しています。";
  
  /** 意図しないDBアクセスエラーのログメッセージ. */
  public static final String LOG_UNEXPECTED_DB_ACCESS_ERROR = "意図しないDBアクセスエラーが発生しました。";

  /** IDがNULLのため処理が終了することのログメッセージ. */
  public static final String LOG_DELETE_FAIL_IS_CAUSED_BY_NULL_ID = "idがNullのため処理を終了します。";
  
  /** 更新処理のレシピ取得で失敗した時のログメッセージ. */
  public static final String LOG_DB_ACCESS_ERROR_AT_UPDATE_GETTING_RECIPE 
      = "指定IDのレシピを取得中に、意図しないDBアクセスエラーが発生しました。";
  
  /** 更新処理のレシピ更新で失敗した時のログメッセージ. */
  public static final String LOG_DB_ACCESS_ERROR_AT_UPDATE_UPDATING_RECIPE 
      = "レシピ更新処理中に、意図しないDBアクセスエラーが発生しました。";

  
  private Constants() {}
}
