package uk.ac.yao.advancedjava.library.constant;

/**
 * collection of constants
 *
 * @author yaojiawen
 * @since 2024/10/12 19:42
 */
public interface CommonConstant {

    /**
     * printout answer details flag
     */
    Boolean PRINTOUT = Boolean.TRUE;

    /**
     * not printout answers details flag
     */
    Boolean NO_PRINTOUT = Boolean.FALSE;

    /**
     * regular quiz flag
     */
    String REGULAR_QUIZ_FLAG = "Regular-";

    /**
     * revision quiz flag
     */
    String REVISION_QUIZ_FLAG = "Revision-";

    String QUIZ_TYPE_REGULAR = "regular";
    String QUIZ_TYPE_REVISION= "revision";

    /**
     * final verdict flag
     */
    String FINAL_VERDICT_PASS = "PASS";
    String FINAL_VERDICT_FALL = "FALL";
    String FINAL_VERDICT_TBD = "TBD";

    /**
     * the number of revision quiz can take
     */
    Integer REVISION_ATTEMPT_COUNT = 2;

    /**
     * the number of regular quiz can take
     */
    Integer REGULAR_ATTEMPT_COUNT = 2;

    /**
     * The score line to determine whether to pass
     */
    double PASS_SCORE = 0.5;

    /**
     * exception prompt text
     */
    String NOT_ALLOW_TAKE_QUIZ_PROMPT_TEXT = "You've passed the quiz or you've failure the quiz, please check your grade";
    String NOT_ALLOW_TAKE_REVISION_QUIZ_PROMPT_TEXT = "You don't take regular task";
    String ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT = "illegal input";

}
