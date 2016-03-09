# flibs
Beberapa helper untuk membuat pengembangan Android Apps lebih cepat

## Cara Instal
Tambahkan repository di build.gradle
```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```

Dan tambahkan dependency
```gradle
dependencies {
    compile 'com.github.didikk:flibs:0.0.1'
}
```
## Penggunaan

### Cek koneksi internet
```java
if (!FUtils.isNetworkConnected(this))
    Toast.makeText(this, "You are now offline!", Toast.LENGTH_SHORT).show();
```

### Date Helper
Beberapa method yang bisa digunakan untuk membantu mengolah tanggal dan waktu
```java
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
```

### Notifikasi
Menampilkan notifikasi
```java
FUtils.showNotification(
    MainActivity.this, // Context
    new Intent(MainActivity.this, MainActivity.class), // Intent ketika notifikasi diklik
    "Sample Title", // Judul notifikasi
    "This is just sample message", // Pesan
    R.drawable.ic_android, // Icon notifikasi
    Color.parseColor("#009688") // Background color dari notifikasi (Opsional)
);
```
### Intent Share
Sharing text melalui default Intent Share Android
```java
FUtils.intentShare(
    MainActivity.this, 
    "Cek github kita yuk sist, ada library keren! https://github.com/didikk/flibs"
);
```
