package uk.ac.yao.advancedjava.library.factory;

import uk.ac.yao.advancedjava.library.domain.quiz.Quiz;
import uk.ac.yao.advancedjava.library.domain.quiz.RegularQuiz;
import uk.ac.yao.advancedjava.library.domain.quiz.RevisionQuiz;
import uk.ac.yao.advancedjava.library.interfaces.IQuestion;

import java.util.*;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * quiz factory class
 *
 * @author yaojiawen
 * @since 2024/10/10 15:00
 */
public class QuizFactory {

    /**
     * generate regular quiz
     *
     * @param pool questions pool
     * @return regular quiz (include generation question by hardcoding)
     */
    public static RegularQuiz generateRegularQuizByHardcoding(HashSet<IQuestion> pool) {
        RegularQuiz regularQuiz = new RegularQuiz();
        HashSet<IQuestion> questions = new HashSet<>();
        // hardcoding
        List<Integer> ids = Arrays.asList(1, 3, 4, 5);
        for (IQuestion question : pool) {
            int id = question.getId();
            if (ids.contains(id)) {
                questions.add(question);
            }
        }
        regularQuiz.setQuestions(questions);
        return regularQuiz;
    }


    /**
     * generate revision quiz
     * (shouldn't appear the correct questions which student take)
     *
     * @param type             quiz type (regular or revision)
     * @param numberOfQuestion how many question will be generated
     * @param pool             questions pool
     * @param correct          questions that student answer correctly
     * @return abstract class Quiz
     * @throws IllegalArgumentException input quiz type except regular and revision
     */
    public static Quiz generateQuizByRandom(String type, int numberOfQuestion, HashSet<IQuestion> pool, HashSet<IQuestion> correct) {
        HashSet<IQuestion> questions = new HashSet<>();
        List<IQuestion> questionList = new ArrayList<>(pool);

        Quiz quiz;
        if (QUIZ_TYPE_REGULAR.equals(type)) {
            quiz = new RegularQuiz();
        } else if (QUIZ_TYPE_REVISION.equals(type)) {
            quiz = new RevisionQuiz();
        } else {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT);
        }
        // sort it by question's id
        questionList.sort(Comparator.comparingInt(IQuestion::getId));
        for (IQuestion question : questionList) {
            if (questions.size() == numberOfQuestion) {
                quiz.setQuestions(questions);
                return quiz;
            }
            // do not show up the correct question which student answer correctly
            if (!correct.contains(question)) {
                questions.add(question);
            }
        }
        quiz.setQuestions(questions);
        return quiz;
    }


}
