package recipesystem.application.exceptionmapper;

import static recipesystem.common.Constants.LOG_UNEXPECTED_ERROR;
import static recipesystem.common.Constants.UNEXPECTED_ERROR;

import java.util.Collections;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 意図しないRuntimeExceptionが発生した時、指定のメッセージを返すHandlerクラス.
 */
@RestControllerAdvice
public class RuntimeExceptionHandler {

  private Log log = LogFactory.getLog(RuntimeExceptionHandler.class);
  
  /**
   * ControllerでCatch出来なかったエラーをハンドリングしResponseを作成して返却するクラス.
   * 
   * @param e 意図せず発生したRuntimeException.
   * @return レスポンスメッセージ.
   */
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, String> handleException(RuntimeException e) {
    log.error(LOG_UNEXPECTED_ERROR, e);
    return Collections.singletonMap(
        "message", UNEXPECTED_ERROR);
  }
}
