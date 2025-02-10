package uk.ac.yao.advancedjava.library.interfaces;

/**
 * @author yaojiawen
 * @since 2024/9/27 17:55
 */
public interface IQuestion {

    /**
     * check the input answer is correct
     *
     * @param answer input answer
     * @return boolean true or false
     */
    boolean isCorrect(String answer);

    /**
     * get question text
     *
     * @return question description
     */
    String getText();

    /**
     * get the answer of question
     *
     * @return standard answer
     */
    String getAnswer();

    /**
     * get the id of question
     *
     * @return question id
     */
    int getId();

}
