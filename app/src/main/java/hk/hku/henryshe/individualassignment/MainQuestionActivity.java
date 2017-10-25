package hk.hku.henryshe.individualassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainQuestionActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView problemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);


//        Test the showing problem(Question)
        problemText = (TextView)findViewById(R.id.problemQuestion);
        problemText.setText(setQuestion(1,1,1));
        setQuestion(2,1,1);
    }

    public int getRamVal(){
        return 0;
    }

    public String setQuestion(int type,int a,int b){
        if(type == 1||type == 2 || type ==3 || type == 4 || type ==5){
//            problemText = (TextView)findViewById(R.id.problemQuestion);
//            problemText.setText("This is Linear Question:");
        }
        if(type == 6||type == 7 || type ==8 || type == 9 || type ==10){

        }
        return "Testing";
    }

    public void sendMessage(View view){
        //DisplayMessageActivity error.
        //Create a intent, which passing the class to the sub class
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //Start the "intent" activity
        startActivity(intent);

    }


    public void showMessage(){

    }
}
