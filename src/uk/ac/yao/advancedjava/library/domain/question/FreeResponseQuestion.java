package uk.ac.yao.advancedjava.library.domain.question;

import org.junit.platform.commons.util.StringUtils;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * @author yaojiawen
 * @since 2024/9/27 18:05
 */
public class FreeResponseQuestion extends Question {

    public FreeResponseQuestion(int id, String text, String answer) {
        super(id, text, answer);
    }

    /**
     * check the input answer is correct
     *
     * @param answer input answer
     * @return true for correct false for incorrect
     */
    @Override
    public boolean isCorrect(String answer) {
        if (StringUtils.isBlank(answer)) {
            return false;
        }
        return answer.equals(getAnswer().trim());
    }

    /**
     * override to printout format string
     *
     * @return format string
     */
    @Override
    public String toString() {
        return getText() + "\n";
    }

    /**
     * create instant from input text
     *
     * @param questionText question text format(id,text,answer)
     * @return FreeResponseQuestion
     * @throws IllegalArgumentException input question text is blank and lack of question parts
     */
    public static Question valueOf(String questionText) {
        if (questionText.isBlank()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT);
        }
        String[] parts = questionText.split(", ");
        if (parts.length != 3) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT);
        }
        int id = Integer.parseInt(parts[0]);
        String text = parts[1];
        String answer = parts[2];
        return new FreeResponseQuestion(id, text, answer);
    }
}
