package hk.hku.henryshe.individualassignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.Random;

public class MainQuestionActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView problemText;
    private TextView answerText;
    private EditText userAnswer1;
    private int a,b,c,solution;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);
        mainFunc(3);


    }

    public void mainFunc(int type){

        if(type<5){
            a = getRamVal();
            b = getRamVal();
            setQuestion((type+1),a,b);
            //you need to convert a and b into double,otherwise it will not have correct answer.
            final double solution = -((double)b/(double)a);
            final Button btn = (Button) findViewById(R.id.button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userAnswer1 = (EditText)findViewById(R.id.editText);
                    double temp = Double.valueOf(userAnswer1.getText().toString());
                    // For testing
                    // /double temptemp = -3.14;
                    Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                    answerText = (TextView)findViewById(R.id.answer);
                    if(areEqual(temp,solution)){
                        answerText.setTextColor(Color.parseColor("#0B6623"));
                        answerText.setText("Right answer!");
                    }else{
                        answerText.setTextColor(Color.parseColor("#FF0000"));
                        answerText.setText("Wrong answer!"+userAnswer1.getText().toString()+ "/" + Double.toString(solution));
                    }
                    btn.setClickable(false);
                }
            });
            Button btnNext = (Button) findViewById(R.id.button2);
        }else{
            do{
                a = getRamVal();
                b = getRamVal();
                c = getRamVal();
            }while (hasRoot(a,b,c));
            setQuestion((type+1),a,b,c);
        }


    }

    public double getSolution(int a,int b,int c){
        return 3.14159;
    }

//    This method is used to generate random values
    public int getRamVal(){
        Random myRam;
        myRam = new Random();
        int ramNum = -99 + myRam.nextInt(199);
        if(ramNum == 0){
            return getRamVal();
        }
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

        if(b<0){
            if(c<0){
                problemText = (TextView)findViewById(R.id.problemQuestion);
                problemText.setText(a + "x^2 - "+ (-b) +"x - "+ (-c) + " = 0, what is x?");
            }else{
                problemText = (TextView)findViewById(R.id.problemQuestion);
                problemText.setText(a + "x^2 - "+ (-b) +"x + "+ (c) + " = 0, what is x?");
            }
        }else{
            if(c<0){
                problemText = (TextView)findViewById(R.id.problemQuestion);
                problemText.setText(a + "x^2 + "+ (b) +"x - "+ (-c) + " = 0, what is x?");
            }else{
                problemText = (TextView)findViewById(R.id.problemQuestion);
                problemText.setText(a + "x^2 + "+ (b) +"x + "+ (c) + " = 0, what is x?");
            }
        }
//        problemText = (TextView)findViewById(R.id.problemQuestion);
//        problemText.setText("a:"+a+" b:"+b+" c:"+c);
    }

    public boolean hasRoot(int a,int b,int c){
        int det = b*b - 4*a*c;
        if(det < 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean areEqual(Double A,Double B){
        if (Math.abs(A - B) < 1e-2)
            return true;
        return false;
    }



//    public void sendMessage(View view){
//        //DisplayMessageActivity error.
//        //Create a intent, which passing the class to the sub class
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        //Start the "intent" activity
//        startActivity(intent);
//
//    }


    public void showMessage(){

    }
}
