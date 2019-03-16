package recipesystem.common;

/**
 * 情報不足でレシピの作成に失敗した時に発生するException.
 */
public class FailToCreateRecipeException extends RuntimeException {

  private static final long serialVersionUID = 4164016421475751965L;
  
  /**
   * 新しい例外を構築.
   */
  public FailToCreateRecipeException() {
    super();
  }
  
  /**
   * 指定された原因を使用して新しい例外を構築.
   * 
   * @param cause 原因
   */
  public FailToCreateRecipeException(Throwable cause) {
    super(cause);
  }
}
