## About Project

You have been tasked with implementing a library for generating and taking quizzes for a local school which will be used within a larger application for student assessment.

- A student is considered to have passed the assessment if they score 50% or more on a regular quiz.
- If the student fails two regular quizzes, they are considered to have failed the assessment.
- Each student has an option to take up to two revision quizzes unless they have already received a PASS or FAIL verdict. Also, a student should not be able to take another regular quiz if they have received their verdict (e.g., a student who has passed on the first attempt should not be able to take another regular or revision quiz).
- Each quiz should contain questions of two different types: free response questions and multiple-choice questions. A regular quiz should be generated randomly from the pool of available questions. 
- A quiz must not have any duplicate questions, and it must contain questions of both types. A revision quiz must contain only questions that the given student has not seen before or answered incorrectly in the past. Similarly to the regular quiz, it must also not have any duplicates, and it must contain questions of both types (but only if there are still unseen or incorrectly answered questions of both types in the question pool).

The aim of this coursework is to practice the design principles covered in lectures. You will develop interfaces and classes to demonstrate that you have learned and understood module material, including:
- appropriate overriding of Object class methods, including overriding toString and providing a static valueOf method when appropriate
- design of interface-based hierarchies, programming through interfaces and late binding
- the use of factories to control instantiation of objects, including guaranteeing the instantiation of unique instances
- defensive programming including the use of immutability
- the use of appropriate interfaces and classes from the Collections framework
- appropriate use of Javadocs to document your interfaces and classes
- unit testing via JUni
