package com.example.quizapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestionCounter;
    private TextView tvQuestion;

    private RadioGroup radioGroup;

    private RadioButton radioOption1;
    private RadioButton radioOption2;
    private RadioButton radioOption3;
    private RadioButton radioOption4;

    private Button btnNext;

    private List<Question> questionList;

    private int currentQuestion = 0;
    private int score = 0;

    private boolean answerSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        // Connect XML views
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter);
        tvQuestion = findViewById(R.id.tvQuestion);

        radioGroup = findViewById(R.id.radioGroup);

        radioOption1 = findViewById(R.id.radioOption1);
        radioOption2 = findViewById(R.id.radioOption2);
        radioOption3 = findViewById(R.id.radioOption3);
        radioOption4 = findViewById(R.id.radioOption4);

        btnNext = findViewById(R.id.btnNext);

        // Create questions
        createQuestions();

        // Shuffle only the question order
        Collections.shuffle(questionList);

        // Show first question
        showQuestion();

        /*
         * IMPORTANT:
         * We are NOT using RadioGroup.setOnCheckedChangeListener().
         *
         * Each RadioButton has its own click listener.
         */

        radioOption1.setOnClickListener(v -> checkAnswer(0));

        radioOption2.setOnClickListener(v -> checkAnswer(1));

        radioOption3.setOnClickListener(v -> checkAnswer(2));

        radioOption4.setOnClickListener(v -> checkAnswer(3));

        // Next button
        btnNext.setOnClickListener(v -> {

            // User must select an answer first
            if (!answerSelected) {
                return;
            }

            if (currentQuestion < questionList.size() - 1) {

                currentQuestion++;

                showQuestion();

            } else {

                openResultScreen();
            }
        });
    }

    private void createQuestions() {

        questionList = new ArrayList<>();

        questionList.add(new Question(
                "What is the capital of India?",
                new String[]{
                        "Mumbai",
                        "New Delhi",
                        "Hyderabad",
                        "Chennai"
                },
                1
        ));

        questionList.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{
                        "Earth",
                        "Venus",
                        "Mars",
                        "Jupiter"
                },
                2
        ));

        questionList.add(new Question(
                "What is the largest ocean in the world?",
                new String[]{
                        "Atlantic Ocean",
                        "Indian Ocean",
                        "Arctic Ocean",
                        "Pacific Ocean"
                },
                3
        ));

        questionList.add(new Question(
                "Which programming language is used for Android development in this project?",
                new String[]{
                        "Java",
                        "HTML",
                        "SQL",
                        "PHP"
                },
                0
        ));

        questionList.add(new Question(
                "How many continents are there in the world?",
                new String[]{
                        "5",
                        "6",
                        "7",
                        "8"
                },
                2
        ));

        questionList.add(new Question(
                "Which is the largest planet in our solar system?",
                new String[]{
                        "Earth",
                        "Jupiter",
                        "Saturn",
                        "Mars"
                },
                1
        ));

        questionList.add(new Question(
                "Who is known as the Father of Computers?",
                new String[]{
                        "Charles Babbage",
                        "Bill Gates",
                        "Steve Jobs",
                        "Alan Turing"
                },
                0
        ));

        questionList.add(new Question(
                "What does CPU stand for?",
                new String[]{
                        "Central Processing Unit",
                        "Computer Personal Unit",
                        "Central Program Utility",
                        "Computer Processing Utility"
                },
                0
        ));

        questionList.add(new Question(
                "Which language is mainly used to style web pages?",
                new String[]{
                        "Java",
                        "CSS",
                        "Python",
                        "SQL"
                },
                1
        ));

        questionList.add(new Question(
                "How many days are there in a leap year?",
                new String[]{
                        "365",
                        "366",
                        "364",
                        "367"
                },
                1
        ));
    }

    private void showQuestion() {

        Question question = questionList.get(currentQuestion);

        // New question
        answerSelected = false;

        /*
         * First completely clear the RadioGroup.
         */
        radioGroup.clearCheck();

        /*
         * Explicitly uncheck every RadioButton.
         * This prevents old selections from appearing.
         */
        radioOption1.setChecked(false);
        radioOption2.setChecked(false);
        radioOption3.setChecked(false);
        radioOption4.setChecked(false);

        // Reset text colors
        resetColors();

        // Enable all options
        radioOption1.setEnabled(true);
        radioOption2.setEnabled(true);
        radioOption3.setEnabled(true);
        radioOption4.setEnabled(true);

        // Question counter
        tvQuestionCounter.setText(
                "Question " + (currentQuestion + 1)
                        + " of " + questionList.size()
        );

        // Question text
        tvQuestion.setText(question.getQuestion());

        // Options
        String[] options = question.getOptions();

        radioOption1.setText(options[0]);
        radioOption2.setText(options[1]);
        radioOption3.setText(options[2]);
        radioOption4.setText(options[3]);
    }

    private void checkAnswer(int selectedAnswer) {

        // Don't allow multiple answers
        if (answerSelected) {
            return;
        }

        answerSelected = true;

        Question question = questionList.get(currentQuestion);

        int correctAnswer = question.getCorrectAnswer();

        // Highlight correct answer
        RadioButton correctButton =
                getRadioButton(correctAnswer);

        correctButton.setTextColor(
                Color.rgb(0, 150, 0)
        );

        // Check user's answer
        if (selectedAnswer == correctAnswer) {

            // CORRECT
            score++;

            getRadioButton(selectedAnswer)
                    .setTextColor(
                            Color.rgb(0, 150, 0)
                    );

        } else {

            // WRONG
            getRadioButton(selectedAnswer)
                    .setTextColor(Color.RED);
        }

        // Disable all answers
        radioOption1.setEnabled(false);
        radioOption2.setEnabled(false);
        radioOption3.setEnabled(false);
        radioOption4.setEnabled(false);
    }

    private RadioButton getRadioButton(int position) {

        switch (position) {

            case 0:
                return radioOption1;

            case 1:
                return radioOption2;

            case 2:
                return radioOption3;

            default:
                return radioOption4;
        }
    }

    private void resetColors() {

        radioOption1.setTextColor(Color.BLACK);
        radioOption2.setTextColor(Color.BLACK);
        radioOption3.setTextColor(Color.BLACK);
        radioOption4.setTextColor(Color.BLACK);
    }

    private void openResultScreen() {

        int totalQuestions = questionList.size();

        int correctAnswers = score;

        int incorrectAnswers =
                totalQuestions - correctAnswers;

        Intent intent = new Intent(
                QuizActivity.this,
                ResultActivity.class
        );

        intent.putExtra(
                "score",
                score
        );

        intent.putExtra(
                "correctAnswers",
                correctAnswers
        );

        intent.putExtra(
                "incorrectAnswers",
                incorrectAnswers
        );

        intent.putExtra(
                "totalQuestions",
                totalQuestions
        );

        startActivity(intent);

        finish();
    }
}