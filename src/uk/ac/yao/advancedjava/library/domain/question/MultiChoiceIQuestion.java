package uk.ac.yao.advancedjava.library.domain.question;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT;

/**
 * @author yaojiawen
 * @since 2024/9/27 18:06
 */
public class MultiChoiceIQuestion extends Question {

    private final String choices;

    public MultiChoiceIQuestion(int id, String text, String choices, String answer) {
        super(id, text, answer);
        this.choices = choices;
    }

    /**
     * check the input answer is correct
     *
     * @param answer input answer
     * @return true for correct false for incorrect
     */
    @Override
    public boolean isCorrect(String answer) {
        return answer.equalsIgnoreCase(getAnswer().trim());
    }

    /**
     * get choices
     *
     * @return choices
     */
    public String getChoices() {
        return choices;
    }

    /**
     * override to printout format string
     *
     * @return format string
     */
    @Override
    public String toString() {
        return getText() + "\n" + getChoices() + "\n";
    }

    /**
     * create instant from input text
     *
     * @param questionText question text format(id,text,choices,answer)
     * @return MultiChoiceIQuestion
     * @throws IllegalArgumentException input question text is blank and lack of question parts
     */
    public static Question valueOf(String questionText) {
        if (questionText.isBlank()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT);
        }
        String[] parts = questionText.split(", ");
        if (parts.length != 4) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT);
        }
        int id = Integer.parseInt(parts[0]);
        String text = parts[1];
        String choices = parts[2];
        String answer = parts[3];
        return new MultiChoiceIQuestion(id, text, choices, answer);
    }
}
