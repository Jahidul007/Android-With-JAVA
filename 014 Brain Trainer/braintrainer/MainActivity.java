package example.com.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;

    TextView resultTextView;
    TextView poinsTextView;
    TextView sumTextView;
    TextView timerTextView;
    TextView highTextView;
    RelativeLayout relativeLayout;
    GridLayout gridLayout;


    ArrayList<Integer> answers = new ArrayList<Integer>();
    ArrayList<Integer> previousScore = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;
    int big;

    public void playAgain(View view) {

        score = 0;
        numberOfQuestion = 0;

        timerTextView.setText("30s");
        poinsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        generateQuestion();

        new CountDownTimer(10100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                gridLayout.setVisibility(View.INVISIBLE);

                playAgainButton.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");

                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

                previousScore.add(score);

                big = previousScore.get(0);

                for (int i = 0; i < previousScore.size(); i++) {

                    if (big < previousScore.get(i)) {
                        big = previousScore.get(i);

                    }

                }


                highTextView.setText("High score: "+ Integer.toString(big));

            }
        }.start();

    }

    public void generateQuestion() {

        Random random = new Random();

        sumTextView = (TextView) findViewById(R.id.sumTextView);

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = random.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = random.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;

            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Incorrect!");

        }


        numberOfQuestion++;
        poinsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

        generateQuestion();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        poinsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        highTextView = (TextView) findViewById(R.id.highTextView);

        generateQuestion();
    }
}
