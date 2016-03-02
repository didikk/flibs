package me.didik.flibs.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.didik.flibs.R;
import me.didik.futils.FUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView) findViewById(R.id.txt);

        String currTime = FUtils.getDateTime();
        String humanFormat = FUtils.dateFormatter(currTime, FUtils.DATE_HUMAN_FORMAT_FULL);
        txt.setText(humanFormat);
    }
}
