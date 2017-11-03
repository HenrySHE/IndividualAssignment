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
import java.text.DecimalFormat;
import java.util.Random;

public class MainQuestionActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView problemText;
    private TextView answerText;
    private EditText userAnswer1,userAnswer2;
    private int a,b,c;
    private int count = 0;
    private Button nextBtn;
    private Button btn;
    private double solution;
    private double solution1,solution2;
    private int rightCount = 0;
    private int wrongCount = 0;
    private int inComplete = 0;
    private long duration = 0;
    private int giveUpCount = 0;
    private long timeA;
    private long timeB;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);
//        mainFunc(3);
        userAnswer1 = (EditText)findViewById(R.id.editText);
//        userAnswer1.setText("");
        userAnswer1.setVisibility(View.GONE);
        userAnswer2 = (EditText)findViewById(R.id.editText2);
        userAnswer2.setVisibility(View.GONE);
//        userAnswer2.setVisibility(View.VISIBLE);
        btn = (Button)findViewById(R.id.button) ;
        btn.setVisibility(View.GONE);
        nextBtn = (Button) findViewById(R.id.button2);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerText = (TextView)findViewById(R.id.answer);
                answerText.setText("");
                userAnswer1 = (EditText)findViewById(R.id.editText);
                userAnswer1.setVisibility(View.VISIBLE);
                userAnswer1.setText("");
                btn = (Button)findViewById(R.id.button) ;
                btn.setVisibility(View.VISIBLE);
                if(count<10){
                    timeA = 0;
                    timeB = 0;
                    nextBtn.setText("Next");
                    mainFunc((count));
                    count = count +1;
                }else{
                    timeA = 0;
                    timeB = 0;
                    problemText = (TextView)findViewById(R.id.problemType);
                    problemText.setText("The End");
                    answerText = (TextView)findViewById(R.id.problemQuestion);
                    answerText.setText("That's all,Click Finish!");
                    userAnswer1 = (EditText)findViewById(R.id.editText);
                    userAnswer1.setVisibility(View.GONE);
                    userAnswer2 = (EditText)findViewById(R.id.editText2);
                    userAnswer2.setVisibility(View.GONE);
                    btn = (Button)findViewById(R.id.button) ;
                    btn.setVisibility(View.GONE);
                    nextBtn.setText("Finish!");
                    giveUpCount = 10 - rightCount - wrongCount - inComplete;
                    // Right/Worng/Incomplete/GiveUp
                    // Duration(Time Used)
                    Intent intent = new Intent(MainQuestionActivity.this,DisplayMessageActivity.class);
//                    intent.putExtra("right",rightCount);
//                    intent.putExtra("wrong",wrongCount);
//                    intent.putExtra("giveUp",giveUpCount);
//                    intent.putExtra("incomplete",inComplete);
//                    intent.putExtra("du",duration);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_RIGHT",String.valueOf(rightCount));
                    extras.putString("EXTRA_WRONG",String.valueOf(wrongCount));
                    extras.putString("EXTRA_GIVEUP",String.valueOf(giveUpCount));
                    extras.putString("EXTRA_INCOMPLETE",String.valueOf(inComplete));
                    extras.putString("EXTRA_DU",String.valueOf(duration));
                    //This should be putExtra(s)!!
                    intent.putExtras(extras);
                    startActivity(intent);
                }

            }
        });

    }

    public void mainFunc(int type){
        timeA = System.currentTimeMillis();
        if((type+1)<6){
            a = getRamVal();
            b = getRamVal();
            setQuestion((type+1),a,b);
            //you need to convert a and b into double,otherwise it will not have correct answer.
            solution = -((double)b/(double)a);
            btn = (Button) findViewById(R.id.button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userAnswer1 = (EditText)findViewById(R.id.editText);
                    String testEmpty = userAnswer1.getText().toString();
                    if(testEmpty.matches("")){
                        Toast.makeText(MainQuestionActivity.this,"You give up this question",Toast.LENGTH_SHORT).show();
                        btn.setClickable(false);
//                        btn.setBackgroundColor(Color.GRAY);
                    }
                    else{
                        String valDec = userAnswer1.getText().toString();
                        int indexOfDec = valDec.indexOf(".");
                        if(indexOfDec >= 0){
                            if(valDec.substring(indexOfDec).length() >3){
                                Toast.makeText(MainQuestionActivity.this,"Please round answer to 2 decimals",Toast.LENGTH_SHORT).show();
                            }else{
                                double temp = Double.valueOf(userAnswer1.getText().toString());
                                // For testing
                                // /double temptemp = -3.14;
                                Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                                answerText = (TextView)findViewById(R.id.answer);
                                if(areEqual(temp,solution)){
                                    answerText.setTextColor(Color.parseColor("#0B6623"));
                                    answerText.setText("Right answer!");
                                    rightCount = rightCount + 1;
                                }else{
                                    answerText.setTextColor(Color.parseColor("#FF0000"));
//                                    answerText.setText("Wrong answer!"+userAnswer1.getText().toString()+ "/" + Double.toString(solution));
                                    DecimalFormat df = new DecimalFormat("####0.00");
//                                    answerText.setText("Wrong answer! The correct answer is: " + Double.toString(solution));
                                    answerText.setText("Wrong answer! The correct answer is: " + df.format(solution));
                                    wrongCount = wrongCount + 1;
                                }
                                btn.setClickable(false);
//                        btn.setBackgroundColor(Color.CYAN);
                            }
                        }else{
//                            Toast.makeText(MainQuestionActivity.this,"Wrong Input!",Toast.LENGTH_SHORT).show();
                            double temp = Double.valueOf(userAnswer1.getText().toString());
                            // For testing
                            // /double temptemp = -3.14;
                            Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                            answerText = (TextView)findViewById(R.id.answer);
                            if(areEqual(temp,solution)){
                                answerText.setTextColor(Color.parseColor("#0B6623"));
                                answerText.setText("Right answer!");
                                rightCount = rightCount + 1;
                            }else{
                                answerText.setTextColor(Color.parseColor("#FF0000"));
//                                    answerText.setText("Wrong answer!"+userAnswer1.getText().toString()+ "/" + Double.toString(solution));
                                DecimalFormat df = new DecimalFormat("####0.00");
//                                    answerText.setText("Wrong answer! The correct answer is: " + Double.toString(solution));
                                answerText.setText("Wrong answer! The correct answer is: " + df.format(solution));
                                wrongCount = wrongCount + 1;
                            }
                            btn.setClickable(false);
                        }
                        timeB = System.currentTimeMillis();
                        setTime(timeA,timeB);
//                        Toast.makeText(MainQuestionActivity.this,"Haha!" + duration,Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        //Here are the Quadratic Questions.
        else{
            do{
                a = getRamVal();
                b = getRamVal();
                c = getRamVal();
            }while (notHasRoot(a,b,c));
            setQuestion((type+1),a,b,c);
            int det = b*b - 4*a*c;
            //----------------Start doing the calculation------------------
            //If there is only one correct root..
            if (det == 0) {
                solution1 = (-b)/(2*a);
                btn = (Button) findViewById(R.id.button);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAnswer1 = (EditText)findViewById(R.id.editText);
                        String testEmpty = userAnswer1.getText().toString();
                        if(testEmpty.matches("")){
                            Toast.makeText(MainQuestionActivity.this,"You give up this question",Toast.LENGTH_SHORT).show();
                            btn.setClickable(false);
//                        btn.setBackgroundColor(Color.GRAY);
                        }
                        else{
                            String valDec = userAnswer1.getText().toString();
                            int indexOfDec = valDec.indexOf(".");
                            if(indexOfDec >= 0){
                                if(valDec.substring(indexOfDec).length() >3){
                                    Toast.makeText(MainQuestionActivity.this,"Please round answer to 2 decimals",Toast.LENGTH_SHORT).show();
                                }else{
                                    double temp = Double.valueOf(userAnswer1.getText().toString());
                                    // For testing
                                    // /double temptemp = -3.14;
                                    Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                                    answerText = (TextView)findViewById(R.id.answer);
                                    if(areEqual(temp,solution1)){
                                        answerText.setTextColor(Color.parseColor("#0B6623"));
                                        answerText.setText("Right answer!");
                                        rightCount = rightCount + 1;
                                    }else{
                                        answerText.setTextColor(Color.parseColor("#FF0000"));
//                                    answerText.setText("Wrong answer!"+userAnswer1.getText().toString()+ "/" + Double.toString(solution));
                                        DecimalFormat df = new DecimalFormat("####0.00");
//                                    answerText.setText("Wrong answer! The correct answer is: " + Double.toString(solution));
                                        answerText.setText("Wrong answer! The correct answer is: " + df.format(solution1)+ " and "+ df.format(solution2));
                                        wrongCount = wrongCount + 1;
                                    }
                                    btn.setClickable(false);
//                        btn.setBackgroundColor(Color.CYAN);
                                }
                            }else{
//                            Toast.makeText(MainQuestionActivity.this,"Wrong Input!",Toast.LENGTH_SHORT).show();
                                double temp = Double.valueOf(userAnswer1.getText().toString());
                                // For testing
                                // /double temptemp = -3.14;
                                Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                                answerText = (TextView)findViewById(R.id.answer);
                                if(areEqual(temp,solution)){
                                    answerText.setTextColor(Color.parseColor("#0B6623"));
                                    answerText.setText("Right answer!");
                                    rightCount = rightCount + 1;
                                }else{
                                    answerText.setTextColor(Color.parseColor("#FF0000"));
//                                    answerText.setText("Wrong answer!"+userAnswer1.getText().toString()+ "/" + Double.toString(solution));
                                    DecimalFormat df = new DecimalFormat("####0.00");
//                                    answerText.setText("Wrong answer! The correct answer is: " + Double.toString(solution));
                                    answerText.setText("Wrong answer! The correct answer is: " + df.format(solution1)+ " and "+ df.format(solution2));
                                    wrongCount = wrongCount + 1;
                                }
                                btn.setClickable(false);
                            }
                            timeB = System.currentTimeMillis();
                            setTime(timeA,timeB);
//                            Toast.makeText(MainQuestionActivity.this,"Time Dur:"+duration,Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }
            //If there is two correct roots..
            else{
                //Change the EditText into visible
                userAnswer2 = (EditText)findViewById(R.id.editText2);
                userAnswer2.setVisibility(View.VISIBLE);
                userAnswer2.setText("");
                //First calculate solution1 and solution2
                solution1 = (-b + Math.sqrt( b*b - 4*a*c))/(2*a);
                solution2 = (-b - Math.sqrt( b*b - 4*a*c))/(2*a);
                btn = (Button) findViewById(R.id.button);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAnswer1 = (EditText)findViewById(R.id.editText);
                        String testEmpty = userAnswer1.getText().toString();
                        String testEmpty2 = userAnswer2.getText().toString();
                        //If all of the answer is empty
                        if(testEmpty.matches("") && testEmpty2.matches("")){
                            Toast.makeText(MainQuestionActivity.this,"You give up this question",Toast.LENGTH_SHORT).show();
                            btn.setClickable(false);
                        }
                        //If user leave one blank -> treat as incomplete
                        if(testEmpty.matches("") || testEmpty2.matches("")){
                            Toast.makeText(MainQuestionActivity.this,"Your answer is incomplete",Toast.LENGTH_SHORT).show();
                            inComplete = inComplete + 1;
                            btn.setClickable(false);

                        }
                        else{
                            String valDec = userAnswer1.getText().toString();
                            String valDec2 = userAnswer2.getText().toString();
                            int indexOfDec = valDec.indexOf(".");
                            if(indexOfDec >= 0){
                                if(valDec.substring(indexOfDec).length() >3){
                                    Toast.makeText(MainQuestionActivity.this,"Please round answer to 2 decimals",Toast.LENGTH_SHORT).show();
                                }else{
                                    //Convert two user intpu into double digits
                                    double temp1 = Double.valueOf(userAnswer1.getText().toString());
                                    double temp2 = Double.valueOf(userAnswer2.getText().toString());
                                    Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                                    answerText = (TextView)findViewById(R.id.answer);
                                    if(testMatch(temp1,temp2,solution1,solution2)){
                                        answerText.setTextColor(Color.parseColor("#0B6623"));
                                        answerText.setText("Right answer!");
                                        rightCount = rightCount + 1;
                                    }else{
                                        answerText.setTextColor(Color.parseColor("#FF0000"));
                                        DecimalFormat df = new DecimalFormat("####0.00");
                                        answerText.setText("Wrong answer! The correct answer is: " + df.format(solution1)+ " and "+ df.format(solution2));
                                    }
                                    btn.setClickable(false);
                                }
                            }else{
                                double temp = Double.valueOf(userAnswer1.getText().toString());
                                Toast.makeText(MainQuestionActivity.this,"Submit Successfully.",Toast.LENGTH_LONG).show();
                                answerText = (TextView)findViewById(R.id.answer);
                                if(areEqual(temp,solution)){
                                    answerText.setTextColor(Color.parseColor("#0B6623"));
                                    answerText.setText("Right answer!");
                                    rightCount = rightCount + 1;
                                }else{
                                    answerText.setTextColor(Color.parseColor("#FF0000"));
                                    DecimalFormat df = new DecimalFormat("####0.00");
                                    answerText.setText("Wrong answer! The correct answer is: " + df.format(solution1)+ " and "+ df.format(solution2));
                                }
                                btn.setClickable(false);
                            }
                            timeB = System.currentTimeMillis();
                            setTime(timeA,timeB);
//                            Toast.makeText(MainQuestionActivity.this,"Time Dur:"+duration,Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        }
    }

    public boolean testMatch(double a,double b,double A,double B){
        if(areEqual(a,A)){
            if(areEqual(b,B)){
                return true;
            }
        }if(areEqual(a,B)){
            if(areEqual(b,A)){
                return true;
            }
        }
        return false;
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
    }

    public boolean notHasRoot(int a,int b,int c){
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

    public void setTime(long timeA,long timeB){
        duration = duration + (timeB - timeA);
    }
}
