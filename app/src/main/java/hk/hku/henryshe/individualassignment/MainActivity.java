package hk.hku.henryshe.individualassignment;
    //The project is created by Haoyu SHE (Henry) in 2017
    //Finished updating in 2017.11.3
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textview_myInfo;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_myInfo = (TextView)this.findViewById(R.id.myinfo);
        //Setting the software name
        setTitle("Problem Solving APP");
        String text_info = "Haoyu SHE\n\n";
        text_info += "佘昊宇\n\n";
        text_info += "UID:3035455149\n\n";
        text_info += "E-mail:henryshe@hku.hk\n\n";
        textview_myInfo.setText(text_info);

        //Setting the jumping action
        startBtn = (Button) findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainQuestionActivity.class);
                startActivity(intent) ;
            }
        });
    }




}
