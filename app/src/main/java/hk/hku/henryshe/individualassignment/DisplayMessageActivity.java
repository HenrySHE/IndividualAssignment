package hk.hku.henryshe.individualassignment;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    private TextView right_tv;
    private TextView wrong_tv;
    private TextView giveUp_tv;
    private TextView incomplete_tv;
    private TextView du_tv;
    private String right_string,wrong_string,giveUp_string,incomplete_string,du_string;
    private double used_time,right_num,incomplete_num,wrong_num;
    private double do_num,giveUp_num,avg_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


//        String message = intent.getStringExtra(MainQuestionActivity.EXTRA_MESSAGE);

//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(message);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        right_string = extras.getString("EXTRA_RIGHT");
        wrong_string = extras.getString("EXTRA_WRONG");
        giveUp_string = extras.getString("EXTRA_GIVEUP");
        incomplete_string = extras.getString("EXTRA_INCOMPLETE");
        du_string = extras.getString("EXTRA_DU");

        right_tv = (TextView) findViewById(R.id.right);
        right_tv.setText("Right:\t\t"+ right_string);
        wrong_tv = (TextView) findViewById(R.id.wrong);
        wrong_tv.setText("Wrong:\t\t" + wrong_string);
        giveUp_tv = (TextView) findViewById(R.id.giveUp);
        giveUp_tv.setText("Give Up:\t\t"+ giveUp_string);
        incomplete_tv = (TextView) findViewById(R.id.incomplete);
        incomplete_tv.setText("Incomplete:\t\t"+ incomplete_string);
        du_tv = (TextView) findViewById(R.id.at);
//        du_tv.setText("Time used:\t"+du_string);
        used_time = Double.parseDouble(du_string);
//        right_num = Double.parseDouble(right_string);
//        incomplete_num = Double.parseDouble(incomplete_string);
//        wrong_num = Double.parseDouble(wrong_string);
        giveUp_num = Double.parseDouble(giveUp_string);
        do_num = 10 - giveUp_num;
        if(do_num != 0){
            avg_time = (used_time/do_num)/1000;
            du_tv.setText("Average Time:"+String.valueOf(avg_time)+"\n(second/question)");
        }else{
            du_tv.setText("Time used: 0 sec.");
        }


        //Toast.makeText(DisplayMessageActivity.this,right_string,Toast.LENGTH_SHORT).show();

    }
}
