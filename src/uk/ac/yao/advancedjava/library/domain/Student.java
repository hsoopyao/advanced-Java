package uk.ac.yao.advancedjava.library.domain;

import uk.ac.yao.advancedjava.library.interfaces.IQuestion;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import static uk.ac.yao.advancedjava.library.constant.CommonConstant.*;

/**
 * @author yaojiawen
 * @since 2024/9/27 17:51
 */
public final class Student {

    /**
     * student ID
     * unique
     */
    private final int stuNo;

    /**
     * include first name and last name
     */
    private final String name;

    /**
     * student's birth
     */
    private final Calendar birth;

    /**
     * attempt regular quiz count
     */
    private int attemptCount;

    /**
     * take revision quiz count
     */
    private int revisionCount;

    /**
     * PASS, FAIL or TBD
     */
    private String verdict;

    /**
     * record correct question
     * to avoid appear the correct question in next quiz
     */
    private final HashSet<IQuestion> correctQuiz;

    /**
     * record every quiz score
     */
    private final HashMap<String, Float> recordScore;

    /**
     * constructor of student class
     *
     * @param stuNo student's ID
     * @param name  student's name
     * @param birth student's birth
     * @throws IllegalArgumentException lack of first name or last name throw exception
     */
    public Student(int stuNo, String name, Calendar birth) {
        this.stuNo = stuNo;
        if (!name.contains(" ")) throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_PROMPT_TEXT);
        this.name = name;
        this.birth = birth;
        this.verdict = FINAL_VERDICT_TBD;
        this.correctQuiz = new HashSet<>();
        this.recordScore = new HashMap<>();

    }

    /**
     * student have same name and same birth consider one student
     *
     * @param o object
     * @return true for the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(birth, student.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth);
    }

    public int getStuNo() {
        return stuNo;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirth() {
        return birth;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public int getRevisionCount() {
        return revisionCount;
    }

    public String getVerdict() {
        return verdict;
    }

    public HashSet<IQuestion> getCorrectQuiz() {
        return correctQuiz;
    }

    public HashMap<String, Float> getRecordScore() {
        return recordScore;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public void setRevisionCount(int revisionCount) {
        this.revisionCount = revisionCount;
    }

    /**
     * printout student take quiz situation
     *
     * @return format string
     */
    public String generateStatistics() {
        System.out.println("-".repeat(20));
        return "Student No" + ": " + stuNo
                + "\nname: "
                + this.name
                + "\nbirth: "
                + birth.getTime()
                + "\nfinal verdict: "
                + this.verdict
                + "\nquiz score: " + this.recordScore
                + "\nnumber of attempt: " + attemptCount
                + "\nnumber of revision:" + revisionCount;
    }
}
