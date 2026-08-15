package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvCorrect;
    private TextView tvIncorrect;

    private Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvIncorrect = findViewById(R.id.tvIncorrect);

        btnRestart = findViewById(R.id.btnRestart);

        Intent intent = getIntent();

        int score = intent.getIntExtra(
                "score",
                0
        );

        int correctAnswers = intent.getIntExtra(
                "correctAnswers",
                0
        );

        int incorrectAnswers = intent.getIntExtra(
                "incorrectAnswers",
                0
        );

        int totalQuestions = intent.getIntExtra(
                "totalQuestions",
                10
        );

        tvScore.setText(
                "Score: " + score + " / " + totalQuestions
        );

        tvCorrect.setText(
                "Correct Answers: " + correctAnswers
        );

        tvIncorrect.setText(
                "Incorrect Answers: " + incorrectAnswers
        );

        btnRestart.setOnClickListener(v -> {

            Intent restartIntent = new Intent(
                    ResultActivity.this,
                    QuizActivity.class
            );

            restartIntent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_NEW_TASK
            );

            startActivity(restartIntent);

            finish();
        });
    }
}