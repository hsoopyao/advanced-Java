package uk.ac.yao.advancedjava.library.domain.question;

import uk.ac.yao.advancedjava.library.interfaces.IQuestion;

import java.util.Objects;

/**
 * @author yaojiawen
 * @since 2024/10/10 13:51
 */
public abstract class Question implements IQuestion {

    /**
     * unique id of each question
     */
    private final int id;

    /**
     * text of question
     */
    private final String text;

    /**
     * standard answer of question
     */
    private final String answer;

    Question(int id, String text, String answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public abstract boolean isCorrect(String answer);

    /**
     * If the id of the problem is the same as the title,
     * the problem is considered to be the same
     *
     * @param obj another question
     * @return same or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Question)) return false;
        Question question = (Question) obj;
        return id == question.getId() && Objects.equals(text, question.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
