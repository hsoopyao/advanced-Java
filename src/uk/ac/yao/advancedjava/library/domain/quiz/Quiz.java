package uk.ac.yao.advancedjava.library.domain.quiz;

import uk.ac.yao.advancedjava.library.domain.Student;
import uk.ac.yao.advancedjava.library.interfaces.IQuestion;

import java.util.HashMap;
import java.util.HashSet;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * @author yaojiawen
 * @since 2024/9/27 18:36
 */
public abstract class Quiz {

    private HashSet<IQuestion> questions;

    /**
     * constructor
     */
    public Quiz() {
        questions = new HashSet<>();
    }

    public abstract void takeQuiz(Student student, HashMap<Integer, String> answer, boolean isPrintRes);

    /**
     * get quiz score
     *
     * @param student who take quiz
     * @param answers student's answer sheet
     * @return score
     */
    public float getScore(Student student, HashMap<Integer, String> answers, boolean isPrintRes) {
        int correctCount = 0;
        // print out the quiz situation
        if (isPrintRes) {
            printQuiz(answers);
        }
        for (IQuestion question : questions) {
            int id = question.getId();
            if (answers.containsKey(id) && question.isCorrect(answers.get(id))) {
                correctCount++;
                student.getCorrectQuiz().add(question);
            }
        }
        return (float) correctCount / questions.size();
    }

    /**
     * update student's final verdict
     *
     * @param student who take the quiz
     * @param score   if score >= 0.5 is pass
     */
    public void updateVerdict(Student student, double score) {
        if (score >= PASS_SCORE) {
            student.setVerdict(FINAL_VERDICT_PASS);
        } else if (student.getRevisionCount() >= REVISION_ATTEMPT_COUNT) {
            student.setVerdict(FINAL_VERDICT_FALL);
        } else if (student.getAttemptCount() >= REGULAR_ATTEMPT_COUNT) {
            student.setVerdict(FINAL_VERDICT_FALL);
        }
    }

    /**
     * record quiz attempt count
     *
     * @param student who take the quiz
     * @param score   according to score to update student's final verdict
     * @throws IllegalArgumentException input not exist quiz type
     */
    public void recordQuizAttempt(Student student, double score) {
        if (this instanceof RegularQuiz) {
            student.setAttemptCount(student.getAttemptCount() + 1);
        } else if (this instanceof RevisionQuiz) {
            student.setRevisionCount(student.getRevisionCount() + 1);
        } else {
            throw new IllegalArgumentException("Illegal Quiz type");
        }
        updateVerdict(student, score);
    }

    /**
     * printout quiz details
     *
     * @param answerSheet student's answers
     */
    private void printQuiz(HashMap<Integer, String> answerSheet) {
        System.out.println("----------Quiz begin:----------");
        for (IQuestion question : questions) {
            int id = question.getId();
            String text = question.getText();
            String answer = question.getAnswer();
            String studentAnswer = answerSheet.get(id);
            String res = answer.equalsIgnoreCase(answerSheet.get(id)) ? "Correct" : "Wrong";
            String print = String.format("%d. %s \n student's answer: %s \n correct answer: %s \n result: %s", id, text, studentAnswer, answer, res);
            System.out.println(print);
        }
        System.out.println();
    }

    public HashSet<IQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(HashSet<IQuestion> questions) {
        this.questions = questions;
    }
}
