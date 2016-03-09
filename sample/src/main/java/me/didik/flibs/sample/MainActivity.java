package me.didik.flibs.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.didik.flibs.R;
import me.didik.futils.FUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView) findViewById(R.id.txt);

        // Mendapatkan tanggal dan jam sekarang.
        // Output: 2016-03-10 08:10:05
        // Selain getDateTime(), ada juga getDateNow() dan getTimeNow()
        String currTime = FUtils.getDateTime();

        // dateFormatter() digunakan untuk mengubah format tanggal ke beberapa bentuk
        // Parameter: dateFormatter(String waktu, String format)
        // Output tergantung dari format.
        // Format yang telah disediakan:
        // - DATE_FORMAT_FULL, output: 2016-03-10 08:10:05
        // - DATE_HUMAN_FORMAT, output: 10 March 2016
        // - DATE_HUMAN_FORMAT_FULL, output: 10 March 2016 08:10
        String humanFormat = FUtils.dateFormatter(currTime, FUtils.DATE_HUMAN_FORMAT_FULL);

        // Mengubah tanggal menjadi 08:10
        String timeFormat = FUtils.timeFormatter(currTime);

        // Mendapatkan waktu relatif, misal: 2 minutes ago
        String relativeTime = FUtils.convertToRelative(currTime);

        // Konversi tanggal dalam bentuk String menjadi tipe long
        long time = FUtils.convertStringToDate(currTime);

        txt.setText(humanFormat);

        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FUtils.showNotification(
                        MainActivity.this, // Context
                        new Intent(MainActivity.this, MainActivity.class), // Intent ketika notifikasi diklik
                        "Sample Title", // Judul notifikasi
                        "This is just sample message", // Pesan
                        R.drawable.ic_android, // Icon notifikasi
                        Color.parseColor("#009688") // Background color dari notifikasi (Opsional)
                );
            }
        });

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FUtils.intentShare(
                        MainActivity.this, // Context
                        "Cek github kita yuk sist, ada library keren! https://github.com/didikk/flibs"
                );
            }
        });
    }
}
