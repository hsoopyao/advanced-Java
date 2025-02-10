package uk.ac.yao.advancedjava.library.domain.quiz;

import uk.ac.yao.advancedjava.library.domain.Student;

import java.util.HashMap;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * @author yaojiawen
 * @since 2024/9/27 18:39
 */
public class RevisionQuiz extends Quiz {

    public RevisionQuiz() {
    }

    /**
     * student take quiz
     *
     * @param student who attend the quiz
     * @param answer  answer sheet
     * @throws IllegalArgumentException if student final verdict is PASS or FALL
     */
    @Override
    public void takeQuiz(Student student, HashMap<Integer, String> answer, boolean isPrintRes) {
        if (canTakeRevisionQuiz(student)) {
            float score = getScore(student, answer, isPrintRes);
            recordQuizAttempt(student, score);
            student.getRecordScore().put(REVISION_QUIZ_FLAG + student.getRevisionCount(), score);
        } else {
            throw new IllegalArgumentException(NOT_ALLOW_TAKE_QUIZ_PROMPT_TEXT);
        }
    }

    /**
     * judge the student can take the quiz
     *
     * @param student who attend the quiz
     * @return ture means can take, false means can not take
     * @throws IllegalArgumentException doesn't take regular quiz first
     */
    public boolean canTakeRevisionQuiz(Student student) {
        String verdict = student.getVerdict();
        int attemptCount = student.getAttemptCount();
        if (attemptCount < 1) {
            throw new IllegalArgumentException(NOT_ALLOW_TAKE_REVISION_QUIZ_PROMPT_TEXT);
        }
        return student.getRevisionCount() < REVISION_ATTEMPT_COUNT
                && !FINAL_VERDICT_PASS.equals(verdict)
                && !FINAL_VERDICT_FALL.equals(verdict);
    }
}
