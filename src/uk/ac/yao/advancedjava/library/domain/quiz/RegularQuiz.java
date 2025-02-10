package uk.ac.yao.advancedjava.library.domain.quiz;

import uk.ac.yao.advancedjava.library.domain.Student;

import java.util.HashMap;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * @author yaojiawen
 * @since 2024/9/27 18:39
 */
public class RegularQuiz extends Quiz {

    public RegularQuiz() {
    }


    /**
     * student take the quiz and record score
     *
     * @param student who take the quiz
     * @param answer  answer sheet
     * @throws IllegalArgumentException already pass the regular quiz and take it again
     */
    @Override
    public void takeQuiz(Student student, HashMap<Integer, String> answer, boolean isPrintRes) {
        // judge whether student can take the quiz
        if (canTakeRegularQuiz(student)) {
            // calculate student score
            float score = getScore(student, answer, isPrintRes);
            // record attempt count and attempt result
            recordQuizAttempt(student, score);
            student.getRecordScore().put(REGULAR_QUIZ_FLAG + student.getAttemptCount(), score);
        } else {
            throw new IllegalArgumentException(NOT_ALLOW_TAKE_QUIZ_PROMPT_TEXT);
        }
    }

    /**
     * judge whether student take quiz
     *
     * @param student who take the quiz
     * @return true/false
     */
    public boolean canTakeRegularQuiz(Student student) {
        String verdict = student.getVerdict();
        return student.getAttemptCount() < REVISION_ATTEMPT_COUNT
                && !FINAL_VERDICT_PASS.equals(verdict)
                && !FINAL_VERDICT_FALL.equals(verdict);
    }
}