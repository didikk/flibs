package me.didik.futils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by didik on 02/03/16.
 * Helper untuk fungsi-fungsi yang sering digunakan
 */
public class FUtils {
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_HUMAN_FORMAT = "dd MMMM yyyy";
    public static final String DATE_HUMAN_FORMAT_FULL = "dd MMMM yyyy - HH:mm";
    Locale locale = new Locale("in", "ID");

    //======================================================================
    // Network & Location Helper
    // 1. isNetworkConnected (Cek koneksi device)
    // 2. isLocationEnabled (Cek apakah GPS sudah aktif)
    //======================================================================

    /**
     * Check network connection
     *
     * @param context Context that you want to check the connection
     * @return true if device connected to the network
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }

    /**
     * Cek apakah GPS / Location Services sudah diaktifkan
     * Jika belum, maka user diarahkan ke Setting untuk mengaktifkannya.
     *
     * @param context Centext dimana kita ingin mengecek GPS
     */
   /* public static void isLocationEnabled(final Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            FLog.d("Location", ex.toString());
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            FLog.d("Location", ex.toString());
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(context.getString(R.string.location_404));
            dialog.setMessage(context.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(context.getResources().getString(R.string.gotit), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(context.getString(R.string.Cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    paramDialogInterface.dismiss();

                }
            });
            dialog.show();
        }
    }*/

    //======================================================================
    // Date Helper
    // 1. convertStringToDate (konversi string ke format Date)
    // 2. convertToRelative (Konversi waktu ke relative format (2 jam yang lalu))
    // 3. dateFormatter (Ubah format tanggal ke beberapa bentuk)
    // 4. timeFormatter (Ubah format waktu menjadi hh:mm)
    // 5. getDateTime (Mendapatkan tanggal dan jam sekarang)
    // 6. getDateNow (Mendapatkan tanggal sekarang)
    // 7. getTimeNow (Mendapatkan jam sekarang)
    //======================================================================

    /**
     * Konversi String ke dalam bentuk objek Date
     *
     * @param date string yang akan dikonversi
     * @return Date object
     */
    public static long convertStringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convertedDate.getTime();

    }


    /**
     * Konversi waktu ke relative format (2 jam yang lalu)
     *
     * @param date Waktu yang akan dikonversi
     * @return waktu yang sudah diformat
     */
    public static String convertToRelative(String date){
        long now = System.currentTimeMillis();
        long time = convertStringToDate(date);

        String relativeFormat = (String) DateUtils.getRelativeTimeSpanString(time, now, DateUtils.SECOND_IN_MILLIS);

        return relativeFormat;
    }

    /**
     * Ubah format string ke dalam bentuk waktu yang diinginkan
     * Tipe :
     * 1. Full format (2016-01-15 22:00:00) -  DATE_FORMAT_FULL
     * 2. Human format (15 Januari 2016) - DATE_HUMAN_FORMAT
     * 3. Human format full (15 Januari 2016 22:00) - DATE_HUMAN_FORMAT_FULL
     *
     * @param date String Date yang akan diformat
     *
     * @return tanggal yang sudah diformat (i.e. 2016-01-05 05:16:20)
     */
    public static String dateFormatter(String date, String formatType) {
        Date convertedDate = new Date(convertStringToDate(date));

        SimpleDateFormat format = new SimpleDateFormat(formatType, Locale.getDefault());

        return format.format(convertedDate);
    }

    /**
     * Convert date ke format jam
     * @param date tanggal yang akan dikonversi
     * @return jam setelah dikonversi (i.e. 14:10)
     */
    public static String timeFormatter(String date) {
        Date convertedDate = new Date(convertStringToDate(date));

        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());

        return format.format(convertedDate);
    }

    /**
     * Get current date time
     *
     * @return date time format (i.e. 2015-06-04 14:20:22)
     */
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Get current date
     *
     * @return current date (i.e. 2015-06-05)
     */
    public static String getDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Get current time
     *
     * @return time format (i.e. 14:20)
     */
    public static String getTimeNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    //======================================================================
    // Notification Helper
    // 1. showNotification (Membuat notifikasi)
    //======================================================================

    /**
     * Show notification with color defined
     * @param context
     *          Intent source
     * @param resultIntent
     *          Where will you go after user click the notification
     * @param title
     *          Notification title
     * @param text
     *          Text that you want to inform
     * @param smallIcon
     *          Image for small icon
     * @param color
     *          Color background for small Icon
     */
    public static void showNotification(Context context, Intent resultIntent, String title, String text, int smallIcon, int color) {
        long[] vibrate = {1000, 2000};

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(smallIcon)
                        .setContentTitle(title)
                        .setVibrate(vibrate)
                        .setColor(color)
                        .setAutoCancel(true)
                        .setSound(uri)
                        .setContentText(text);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId = 1;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    /**
     * Show notification with default color
     * @param context
     *          Intent source
     * @param resultIntent
     *          Where will you go after user click the notification
     * @param title
     *          Notification title
     * @param text
     *          Text that you want to inform
     * @param smallIcon
     *          Image for small icon
     */
    public static void showNotification(Context context, Intent resultIntent, String title, String text, int smallIcon) {
        long[] vibrate = {1000, 2000};

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(smallIcon)
                        .setContentTitle(title)
                        .setVibrate(vibrate)
                        .setAutoCancel(true)
                        .setSound(uri)
                        .setContentText(text);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId = 1;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    //======================================================================
    // Validation Helper
    //======================================================================

    /**
     * Validasi input nama, tidak boleh kosong dan minimal 3 karakter
     *
     * @param nama input
     * @return true jika sesuai dengan kondisi
     */
    public boolean validateName(EditText editText, String nama) {
        if (TextUtils.isEmpty(nama)) {
            setError(editText, "Mohon isi nama Anda!");
            return false;
        } else if (nama.length() < 3) {
            setError(editText, "Nama minimal 3 karakter.");
            return false;
        }

        return true;
    }

    /**
     * Validasi email, tidak boleh kosong dan harus email yang valid
     *
     * @param email input
     * @return true jika sesuai aturan
     */
    public boolean validateEmail(EditText editText, String email) {
        if (TextUtils.isEmpty(email)) {
            setError(editText, "Mohon isi email Anda");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setError(editText, "Mohon isi email Anda yang valid");
            return false;
        }
        return true;
    }

    /**
     * Validasi password, tidak boleh kosong dan minimal 6 karakter
     *
     * @param password input
     * @return true, jika sesuai aturan
     */
    public boolean validatePassword(EditText editText, String password) {
        if (TextUtils.isEmpty(password)) {
            setError(editText, "Mohon isi password Anda!");
            return false;
        } else if (password.length() < 6) {
            setError(editText, "Password minimal 6 karakter.");
            return false;
        }
        return true;
    }

    /**
     * Validasi password, tidak boleh kosong dan harus sama dengan password sebelumnya
     *
     * @param confirmPassword input
     * @return true, jika sesuai aturan
     */
    public boolean validateConfirmPassword(EditText editText, String confirmPassword, String password) {
        if (TextUtils.isEmpty(confirmPassword)) {
            setError(editText, "Mohon isi password Anda!");
            return false;
        } else if (!confirmPassword.equals(password)) {
            setError(editText, "Password harus sama.");
            return false;
        }
        return true;
    }

    /**
     * Validasi no hp, tidak boleh kosong
     *
     * @param nohp input
     * @return true jika tidak kosong
     */
    public boolean validateNohp(EditText editText, String nohp) {
        if (TextUtils.isEmpty(nohp)) {
            setError(editText, "Mohon isi No HP Anda!");
            return false;
        }
        return true;
    }

    private void setError(EditText editText, String errorMessage){
        editText.setError(errorMessage);
        editText.requestFocus();
    }

    /**
     * Share ke aplikasi lain
     *
     * @param context Context
     * @param text kalimat yang ingin dishare
     */
    public static void intentShare(Context context, String text) {
        Intent share = new Intent();
        share.setType("text/plain");
        share.setAction(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_TEXT, text);

        context.startActivity(Intent.createChooser(share, "Berbagi melalui"));
    }
}
