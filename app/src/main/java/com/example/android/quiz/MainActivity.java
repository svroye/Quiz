package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.checked;
import static android.R.attr.id;
import static android.R.attr.right;
import static com.example.android.quiz.R.string.score;

public class MainActivity extends AppCompatActivity {

    // number of questions of the quiz
    int numberOfQuestions = 10;

    /* array containing the correct answers of the quiz
    the index of the array + 1 equals the number of the question
    (i.e. index 2 --> question 3)
    if the corresponding question is not a radiobutton, then the value in the
    array is set to 0 (this is the case for the Editable text question and
    for the checkbox question)
    */
    int [] correctAnswers = {2 , 0 , 3 , 2 , 4 , 4, 2 , 1 , 3, 0};
    // correct answers of the checkboxes
    int[] rightAnswersCheckBox = {1,4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     code to be performed when the finish button is pressed.
     All of the answers should be checked, the score needs to be calculated and at the end, a Toast
     message should appear with the total score of the player
    * */
    public void submitAnswers(View v){
        // keeps track of the total score
        int score=0;

        // for-loop to check all the questions and their right answers
       for(int i=1;i<=numberOfQuestions;i++) {

           //second question is the editable question
           if (i == 2) {
               //find the EditText view
               EditText answer = (EditText) findViewById(R.id.edit_answer);
               // get Text (=answer) from the EditText view, convert it to a String
               // and compare this with the right answer
               String givenAnswer = answer.getText().toString();
               String rightAnswer = getResources().getString(R.string.Q12A4);
               if (givenAnswer.equals(rightAnswer)) {
                   //right answer --> score + 1
                   score++;
               }
           }
           // 10th question is the CheckBox question
           else if (i == 10) {
            // to check if both options were checked ; if question = 2, both correct options were checked
               int correctAnswersGiven=0;
               int checkBoxeschecked = 0;
               //check answers. For this, we first make a for-loop for all the possible answers,i.e.
               // for all the checkboxes, 4 in total
               for (int j = 1; j <= 4; j++) {
                   //first find the CheckBox correspondig with the value of j and
                   // see whether it is checked or not
                   String nameCheckBox = "cb" + j;
                   int id = getResources().getIdentifier(nameCheckBox, "id", getPackageName());
                   CheckBox checkBox = (CheckBox) findViewById(id);
                   if (checkBox.isChecked()){
                       checkBoxeschecked++;
                   }
                   // Next,perform a loop to see whether the index of the question (1, 2, 3 or 4)
                   // corresponds with the right answers of the checkbox question, given before
                   //as a global variable
                   for(int k=0;k<rightAnswersCheckBox.length;k++){
                       //if the current question index corresponds with the right answer, proceed
                       //to see whether the box was checked
                       if(j == rightAnswersCheckBox[k]){
                           //find checkbox and see whether it is checked or not. If checked,
                           //add point to correctAnswersGiven
                           if (checkBox.isChecked()) correctAnswersGiven++;
                       }
                   }
               }
               //only if the two correct boxes were checked, a point is given.
               // this eliminates the fact that one can check all buttons and
               //receives a point
               if(correctAnswersGiven == 2 && checkBoxeschecked == 2) score++;
           }
           // for all other questions
           else {
               //get the name of the radiogroup which is currently needed
               String radioGroupName = "radiogroup_" + i;
               // get the integer id of this radiogroup
               int radioGroupInt = getResources().getIdentifier(radioGroupName, "id", getPackageName());
               //find the radiogroup in the xml file
               RadioGroup radiogroup = (RadioGroup) findViewById(radioGroupInt);
               //get the integer id of the checked radiobutton given by the user
               int givenAnswer = radiogroup.getCheckedRadioButtonId();
               // get the number of the right radiobutton from the array of right answers
               int rightAnswer = correctAnswers[i - 1];
               // use this number to get the name of the radiobutton
               String radioButtonRightAnswer = "radio_button_" + i + "_" + rightAnswer;
               // get the integer id of this radiobutton
               int radioButtonIntRight = getResources().getIdentifier(radioButtonRightAnswer, "id", getPackageName());
               // check if the given answer and correct answer radio button ids are the same
               if (radioButtonIntRight == givenAnswer){
                   score++;
               }
           }
       }
        //make a toast to display the score and show it
        Toast.makeText(getApplicationContext(),getString(R.string.score,score,numberOfQuestions),Toast.LENGTH_LONG).show();
    }
}
