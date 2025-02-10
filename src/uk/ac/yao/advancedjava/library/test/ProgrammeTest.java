package uk.ac.yao.advancedjava.library.test;

import org.junit.jupiter.api.Test;
import uk.ac.yao.advancedjava.library.domain.Student;
import uk.ac.yao.advancedjava.library.interfaces.IQuestion;
import uk.ac.yao.advancedjava.library.domain.question.FreeResponseQuestion;
import uk.ac.yao.advancedjava.library.domain.question.MultiChoiceIQuestion;
import uk.ac.yao.advancedjava.library.domain.quiz.Quiz;
import uk.ac.yao.advancedjava.library.factory.QuizFactory;
import uk.ac.yao.advancedjava.library.domain.quiz.RegularQuiz;
import uk.ac.yao.advancedjava.library.domain.quiz.RevisionQuiz;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * @author yaojiawen
 * @since 2024/9/27 20:28
 */
public class ProgrammeTest {

    public static HashSet<IQuestion> createQuestionPool() {
        IQuestion firstQuestion = new FreeResponseQuestion(1, "1 + 1 = ?", "2");
        IQuestion secondQuestion = new MultiChoiceIQuestion(2, "1 + 1 <= ?", "A. 1\nB. 2\nC. 3\nD. 4", "B,C");
        IQuestion thirdQuestion = new FreeResponseQuestion(3, "2 + 2 = ?", "4");
        // use valueOf to create instance
        IQuestion fourthQuestion = MultiChoiceIQuestion.valueOf("4, 2 + 2 <= ?, A. 4\nB. 5\nC. 1\nD. 2, A,B");
        IQuestion fifthQuestion = new FreeResponseQuestion(5, "3 + 3 = ?", "6");
        // use valueOf to create instance
        IQuestion sixthQuestion = MultiChoiceIQuestion.valueOf("6, 4 + 4 >= ?, A. 5\nB. 10\n C. 100, B,C");
        IQuestion seventhQuestion = new MultiChoiceIQuestion(7, "5 + 5 <= ?", "A. 1\nB. 5\nC. 100", "10");
        IQuestion eightQuestion = new FreeResponseQuestion(8, "6 + 6 = ?", "12");
        // use valueOf to create instance
        IQuestion ninthQuestion = FreeResponseQuestion.valueOf("9, 7 + 7 = ?, 14");

        List<IQuestion> pool = new ArrayList<>();
        pool.add(firstQuestion);
        pool.add(secondQuestion);
        pool.add(thirdQuestion);
        pool.add(fourthQuestion);
        pool.add(fifthQuestion);
        pool.add(sixthQuestion);
        pool.add(seventhQuestion);
        pool.add(eightQuestion);
        pool.add(ninthQuestion);

        return new HashSet<>(pool);
    }

    @Test
    public void checkSameStudent() {
        Calendar birth = Calendar.getInstance();
        birth.set(1955, Calendar.JANUARY, 1);
        // illegal name input
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Student(1, "Rowan", birth);
        });
        Student rowan = new Student(1, "Rowan Atkinson", birth);
        Student stu = new Student(2, "Rowan Atkinson", birth);
        System.out.println(rowan.equals(stu));
        System.out.println(rowan == stu);
    }

    @Test
    public void oncePass() {

        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        Quiz regular = QuizFactory.generateRegularQuizByHardcoding(pools);

        Calendar birthday = Calendar.getInstance();
        birthday.set(2000, Calendar.JANUARY, 14);
        Student rowan = new Student(1, "Rowan Atkinson", Calendar.getInstance());
        HashMap<Integer, String> answerSheet = new HashMap<>();
        answerSheet.put(1, "2");
        answerSheet.put(3, "4");
        answerSheet.put(4, "A,B");
        answerSheet.put(5, "6");

        regular.takeQuiz(rowan, answerSheet, PRINTOUT);
        System.out.println(rowan.generateStatistics());

        Quiz revisionQuiz = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REGULAR,2, pools, rowan.getCorrectQuiz());
        answerSheet.put(3, "B,C");
        answerSheet.put(5, "6");
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            revisionQuiz.takeQuiz(rowan, answerSheet, PRINTOUT);
        });

    }

    @Test
    public void takeRegularTwiceAndFall() {
        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        Quiz regular = QuizFactory.generateRegularQuizByHardcoding(pools);

        Calendar birthday = Calendar.getInstance();
        birthday.set(2000, Calendar.JANUARY, 14);
        Student rowan = new Student(1, "Rowan Atkinson", Calendar.getInstance());
        HashMap<Integer, String> answerSheet = new HashMap<>();
        answerSheet.put(1, "2");
        answerSheet.put(3, "3");
        answerSheet.put(4, "A,B,C");
        answerSheet.put(5, "8");

        regular.takeQuiz(rowan, answerSheet, PRINTOUT);

        Quiz regular2 = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REGULAR, 2, pools, rowan.getCorrectQuiz());
        answerSheet.put(3, "B,C");
        answerSheet.put(5, "6");
        regular2.takeQuiz(rowan, answerSheet, PRINTOUT);

        System.out.println(rowan.generateStatistics());

        // can not take any quiz
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            regular2.takeQuiz(rowan, new HashMap<>(), NO_PRINTOUT);
        });

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            Quiz revisionQuiz = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 2, pools, rowan.getCorrectQuiz());
            revisionQuiz.takeQuiz(rowan, new HashMap<>(), NO_PRINTOUT);
        });
    }

    @Test
    public void takeRevisionOnce() {
        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        RegularQuiz regular = QuizFactory.generateRegularQuizByHardcoding(pools);

        Calendar birthday = Calendar.getInstance();
        birthday.set(2000, Calendar.JANUARY, 14);
        Student rowan = new Student(1, "Rowan Atkinson", Calendar.getInstance());
        HashMap<Integer, String> answerSheet = new HashMap<>();
        answerSheet.put(1, "1");
        answerSheet.put(3, "b,c");
        answerSheet.put(4, "A,c");
        answerSheet.put(5, "8");
        regular.takeQuiz(rowan, answerSheet, NO_PRINTOUT);

        Quiz revisionQuiz = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 2, pools, rowan.getCorrectQuiz());
        HashMap<Integer, String> revisionAnswerSheet = new HashMap<>();
        revisionAnswerSheet.put(1, "2");
        revisionAnswerSheet.put(2, "b,c");

        revisionQuiz.takeQuiz(rowan, revisionAnswerSheet, NO_PRINTOUT);

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            revisionQuiz.takeQuiz(rowan, answerSheet, NO_PRINTOUT);
        });

        System.out.println(rowan.generateStatistics());
    }

    @Test
    public void takeRevisionTwiceAndPass() {
        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        RegularQuiz regular = QuizFactory.generateRegularQuizByHardcoding(pools);

        Calendar birthday = Calendar.getInstance();
        birthday.set(2000, Calendar.JANUARY, 14);
        Student rowan = new Student(1, "Rowan Atkinson", Calendar.getInstance());
        HashMap<Integer, String> answerSheet = new HashMap<>();
        answerSheet.put(1, "1");
        answerSheet.put(3, "b,c");
        answerSheet.put(4, "A,c");
        answerSheet.put(5, "8");
        regular.takeQuiz(rowan, answerSheet, NO_PRINTOUT);

        Quiz revisionQuiz = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 3, pools, rowan.getCorrectQuiz());
        HashMap<Integer, String> revisionAnswerSheet = new HashMap<>();
        revisionAnswerSheet.put(1, "1");
        revisionAnswerSheet.put(2, "B,c");
        revisionAnswerSheet.put(4, "A,B,C");
        revisionQuiz.takeQuiz(rowan, revisionAnswerSheet, NO_PRINTOUT);

        Quiz revisionQuiz2 = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 3, pools, rowan.getCorrectQuiz());
        HashMap<Integer, String> revisionAnswerSheet2 = new HashMap<>();
        revisionAnswerSheet2.put(1, "2");
        revisionAnswerSheet2.put(4, "A,B");
        revisionQuiz2.takeQuiz(rowan, revisionAnswerSheet2, NO_PRINTOUT);

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            revisionQuiz.takeQuiz(rowan, answerSheet, NO_PRINTOUT);
        });

        System.out.println(rowan.generateStatistics());
    }

    @Test
    public void takeRevisionTwiceAndFall() {
        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        RegularQuiz regular = QuizFactory.generateRegularQuizByHardcoding(pools);

        Calendar birthday = Calendar.getInstance();
        birthday.set(2000, Calendar.JANUARY, 14);
        Student rowan = new Student(1, "Rowan Atkinson", Calendar.getInstance());
        HashMap<Integer, String> answerSheet = new HashMap<>();
        answerSheet.put(1, "1");
        answerSheet.put(3, "b,c");
        answerSheet.put(4, "A,c");
        answerSheet.put(5, "8");
        regular.takeQuiz(rowan, answerSheet, PRINTOUT);

        Quiz revisionQuiz = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 3, pools, rowan.getCorrectQuiz());
        HashMap<Integer, String> revisionAnswerSheet = new HashMap<>();
        revisionAnswerSheet.put(1, "2");
        revisionAnswerSheet.put(2, "5");
        revisionAnswerSheet.put(4, "A,B,C");
        revisionQuiz.takeQuiz(rowan, revisionAnswerSheet, PRINTOUT);

        Quiz revisionQuiz2 = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 3, pools, rowan.getCorrectQuiz());
        HashMap<Integer, String> revisionAnswerSheet2 = new HashMap<>();
        revisionAnswerSheet2.put(2, "5");
        revisionAnswerSheet2.put(4, "A,B,C");
        revisionQuiz2.takeQuiz(rowan, revisionAnswerSheet2, PRINTOUT);

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            revisionQuiz.takeQuiz(rowan, answerSheet, PRINTOUT);
        });

        System.out.println(rowan.generateStatistics());
    }

    @Test
    public void distinct() {
        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        HashSet<IQuestion> correct = new HashSet<>();
        IQuestion correct1 = new FreeResponseQuestion(1, "1 + 1 = ?", "2");
        correct.add(correct1);
        // not include question -> id: 1 text: 1 + 1 = ?
        Quiz revision = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 5, pools, correct);
        for (IQuestion question : revision.getQuestions()) {
            System.out.println(question.getText());
            // printout multiple question choices
            if (question instanceof MultiChoiceIQuestion) {
                MultiChoiceIQuestion multi = (MultiChoiceIQuestion) question;
                System.out.println(multi.getChoices());
            }
        }

        // check the same instance
        IQuestion correct2 = new FreeResponseQuestion(1, "1 + 1 = ?", "2");
        System.out.println(correct1.equals(correct2));
    }

    @Test
    public void defenceTest() {
        HashSet<IQuestion> pools = ProgrammeTest.createQuestionPool();
        Student rowan = new Student(1, "Rowan Atkinson", Calendar.getInstance());
        Quiz revisionQuiz = QuizFactory.generateQuizByRandom(QUIZ_TYPE_REVISION, 3, pools, rowan.getCorrectQuiz());
        // If have no take regular task, not allow to take revision quiz
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            revisionQuiz.takeQuiz(rowan, new HashMap<>(), NO_PRINTOUT);
        });
    }

}
