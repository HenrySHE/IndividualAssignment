package hk.hku.henryshe.individualassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainQuestionActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView problemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);
        int a,b,c;

        for(int i=0;i<10;i++){
            if(i<5){
                a = getRamVal();
                b = getRamVal();
                setQuestion((i+1),a,b);

            }else{
                a = getRamVal();
                b = getRamVal();
                c = getRamVal();
                setQuestion((i+1),a,b,c);
            }
        }
        setQuestion(1,getRamVal(),getRamVal());

//        problemText = (TextView)findViewById(R.id.problemQuestion);
//        problemText.setText(setQuestion(1,1,1));
    }

//    This method is used to generate random values
    public int getRamVal(){
        Random myRam;
        myRam = new Random();
        int ramNum = -99 + myRam.nextInt(199);
        return ramNum;
    }

    public void setQuestion(int type,int a,int b){
        problemText = (TextView)findViewById(R.id.problemType);
        problemText.setText("Linear equation No. "+ type +" :");
        if(b<0){
            problemText = (TextView)findViewById(R.id.problemQuestion);
            problemText.setText(a + "x - "+ (-b) +" = 0, what is x?");
        }else{
            problemText = (TextView)findViewById(R.id.problemQuestion);
            problemText.setText(a + "x + " +b +" = 0, what is x?");
        }
    }
    public void setQuestion(int type,int a,int b,int c){
        problemText = (TextView)findViewById(R.id.problemType);
        problemText.setText("Quadratic equation No."+ type +" :");

        problemText = (TextView)findViewById(R.id.problemQuestion);
        problemText.setText("a:"+a+" b:"+b+" c:"+c);
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
