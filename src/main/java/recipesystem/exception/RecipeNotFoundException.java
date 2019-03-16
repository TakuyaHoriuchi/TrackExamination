package recipesystem.exception;

/**
 * 対象のレシピが見つからない時に発生するException.
 */
public class RecipeNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -1111859640249694851L;

  /**
   * 新しい例外を構築.
   */
  public RecipeNotFoundException() {
    super();
  }

  /**
   * 指定された原因を使用して新しい例外を構築.
   * 
   * @param cause 原因
   */
  public RecipeNotFoundException(Throwable cause) {
    super(cause);
  }

  
}
