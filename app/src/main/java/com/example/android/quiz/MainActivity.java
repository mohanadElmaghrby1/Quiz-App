package com.example.android.quiz;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    the quiz questions
     */
    private String [] questions={"what is the result of 1 + 2 ?",
            "what is the result of 8 / 2 ?"
            ,"what is the result of 2 * 2 ?"
            ,"what is the result of 1 + 1 ?"
            ,"what is the result of 20 / 5 ?"};

    /*
    all possible choices for the questions
     */
    private String[][]choises={ {"1","2" , "3" , "4"} , {"1","2" , "3" , "4"} , {"1","2" , "3" , "4"}
            ,{"1","2" , "3" , "4"} , {"1","2" , "3" , "4"}};

    /*
    the correct answers index  for the questions
     */
    private int correcrAnswers[]={2 ,3,3,1 , 3};

    /**
     * store the current answer for each question
     */
    private int currentAnswers[][]=new int [5][5];

    /**
     * the current question index
     */
    private int current_question=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuestion(current_question);
        displayChoices(current_question);
    }


    /**
     * display the next question
     * @param view
     */
    public void nextQuestion(View view) {

        if (current_question<4) {
            ++current_question;
            updateQuestionNumber(current_question+1);
            displayQuestion(current_question);
            displayChoices(current_question);
        }
    }

    /**
     * display the previous question
     * @param view
     */
    public void prevQuestion(View view) {
        if (current_question>0) {
            --current_question;
            updateQuestionNumber(current_question+1);
            displayQuestion(current_question);
            displayChoices(current_question);
        }
    }

    /**
     * display the question on the screen
     * @param questionIndex the index of the question to display
     */
    private void displayQuestion(int questionIndex){
        TextView question_text = (TextView)findViewById(R.id.question_text);
        question_text.setText(questions[questionIndex]);
    }

    /**
     * display the choice on the screen
     * @param questionIndex
     */
    private void displayChoices(int questionIndex){
        CheckBox choice_A = (CheckBox) findViewById(R.id.choice_A);
        CheckBox choice_B = (CheckBox) findViewById(R.id.choice_B);
        CheckBox choice_C = (CheckBox) findViewById(R.id.choice_C);
        CheckBox choice_D = (CheckBox) findViewById(R.id.choice_D);

        clrearChoices(-1);
        Log.e(" A - " , currentAnswers[questionIndex][0]+"");
        Log.e(" B - " , currentAnswers[questionIndex][1]+"");
        Log.e(" C - " , currentAnswers[questionIndex][2]+"");
        Log.e(" D - " , currentAnswers[questionIndex][3]+"");
        if (currentAnswers[questionIndex][0]==1)
        choice_A.setChecked(true);


        if (currentAnswers[questionIndex][1]==2)
        choice_B.setChecked(true);

        if (currentAnswers[questionIndex][2]==3)
        choice_C.setChecked(true);

        if (currentAnswers[questionIndex][3]==4)
        choice_D.setChecked(true);

        choice_A.setText(choises[questionIndex][0]);
        choice_B.setText(choises[questionIndex][1]);
        choice_C.setText(choises[questionIndex][2]);
        choice_D.setText(choises[questionIndex][3]);

    }


    /**
     * mark choice A
     * @param view
     */
    public void selectChoiceA(View view) {
        currentAnswers[current_question][0]=1;
        clrearChoices(1);
        clearAnswers(current_question , 0);
    }

    /**
     * mark choice B
     * @param view
     */
    public void selectChoiceB(View view) {
        currentAnswers[current_question][1]=2;
        clrearChoices(2);
        clearAnswers(current_question , 1);
    }

    /**
     * mark choice C
     * @param view
     */
    public void selectChoiceC(View view) {
        currentAnswers[current_question][2]=3;
        clrearChoices(3);
        clearAnswers(current_question , 2);
    }

    /**
     * mark the choice d
     * @param view
     */
    public void selectChoiceD(View view) {
        //set the answer for the current question to be 4
        currentAnswers[current_question][3]=4;

        //clear the other choices expect 4
        clrearChoices(4);

        //clear the other choices expect index 3
        clearAnswers(current_question , 3);
    }

    /**
     * clear the CheckBox choices expect a given choice
     * @param notCleared
     */
    private void clrearChoices(int notCleared){
        CheckBox choice_A = (CheckBox) findViewById(R.id.choice_A);
        CheckBox choice_B = (CheckBox) findViewById(R.id.choice_B);
        CheckBox choice_C = (CheckBox) findViewById(R.id.choice_C);
        CheckBox choice_D = (CheckBox) findViewById(R.id.choice_D);

        if (notCleared!=1)
            choice_A.setChecked(false);

        if (notCleared!=2)
            choice_B.setChecked(false);

        if (notCleared!=3)
            choice_C.setChecked(false);

        if (notCleared!=4)
            choice_D.setChecked(false);
    }

    /**
     * clear the answers for a given question expect a given chice index
     * @param question
     * @param index
     */
    private void clearAnswers(int question ,int index){
        for (int i = 0 ; i < 5 ; ++i)
            if (i!=index)
                currentAnswers[question][i]=0;
    }

    /**
     * update the question number text
     * @param num
     */
    private void updateQuestionNumber (int num){
        TextView question_number_text = (TextView)findViewById(R.id.question_number_text);
        question_number_text.setText((num)+"/5");
    }


    /**
     * compute the result and print it to the user
     * @param view
     */
    public void submit(View view) {
        int ans=0;
        for (int i = 0 ; i <5 ; ++i){
            if (currentAnswers[i][correcrAnswers[i]]!=0)
                ++ans;
        }
        Toast.makeText(this,ans+" questions correct from 5 questions" , Toast.LENGTH_LONG).show();

    }
}

