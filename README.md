<h1 align="center">Minpro-4-PBO-SistemPengelolaanTatto</h1>

<p align="center">
  <b>Nama:</b> Awang Rifky Muhadzib &nbsp;|&nbsp; <b>NIM:</b> 2509116059
</p>

<hr>

<h2 align="center">Daftar Isi</h2>

1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Penjelasan Struktur Package](#2-penjelasan-struktur-package)
3. [Penjelasan Alur Program](#3-penjelasan-alur-program)
4. [Penerapan Encapsulation dan Inheritance](#4-penerapan-encapsulation-dan-inheritance)
5. [Penerapan Polymorphism dan Abstraction](#5-penerapan-polymorphism-dan-abstraction)
6. [Penerapan JDBC](#6-penerapan-jdbc)
7. [Cara Menjalankan Program](#7-cara-menjalankan-program)

<hr>

<h2 align="center">1. Deskripsi Singkat Program</h2>

<p align="justify">
Program ini adalah aplikasi <b>CRUD berbasis console (Java)</b> untuk mengelola <b>booking pada studio tattoo</b>. Setiap booking mencatat data pelanggan, tattoo artist yang mengerjakan, nama desain, harga, tanggal, dan status pengerjaan (<code>Menunggu</code>, <code>Dikerjakan</code>, atau <code>Selesai</code>).
</p>

<p align="justify">
Program ini merupakan pengembangan dari Mini Project 3. Pada <b>Mini Project 4</b>, penyimpanan data yang sebelumnya berupa <code>ArrayList</code> di memori (hilang saat program ditutup) diganti dengan <b>database MySQL melalui JDBC</b>, sehingga data tersimpan permanen. Akses database dipisahkan ke dalam package <code>database</code> (koneksi) dan <code>dao</code> (query), sementara konsep <b>OOP</b> dari sebelumnya (encapsulation, inheritance, polymorphism, abstraction, interface) dan struktur <b>MVC</b> tetap dipertahankan. Fitur baru yang ditambahkan adalah <b>cari booking berdasarkan ID</b> (menu 7).
</p>

<hr>

<h2 align="center">2. Penjelasan Struktur Package</h2>

<p align="center">
  <img width="331" alt="Struktur Package" src="https://github.com/user-attachments/assets/a98a5f06-847c-43b9-9e6c-cdff8b29bdcf" />
</p>
<p align="center"><i>Gambar 1. Struktur Package</i></p>

<div align="center">

| Package | Class | Peran |
|---|---|---|
| `main` | `Main` | Entry point, hanya memanggil `new BookingController().jalankan()` |
| `model` | `Orang` | Abstract class induk (`nama`, `noHp`) dengan abstract method `getInfo()` |
| `model` | `Pelanggan`, `TattooArtist` | Subclass dari `Orang` (menambah `alamat` / `spesialisasi`) |
| `model` | `Booking` | Data booking, menyimpan objek `Pelanggan` dan `TattooArtist` |
| `view` | `BookingView` | Menampilkan header, menu, daftar artist, detail booking, dan pesan |
| `controller` | `BookingController` | Menjalankan alur menu, memproses input CRUD booking, dan mengimplementasikan interface `Crud` |
| `controller` | `ArtistController` | Perantara antara program dan `ArtistDAO` untuk data artist |
| `dao` | `BookingDAO` | Berisi seluruh query SQL tabel `booking` (SELECT, INSERT, UPDATE, DELETE) |
| `dao` | `ArtistDAO` | Berisi query SQL tabel `tattoo_artist` (SELECT dan INSERT) |
| `database` | `DBConnection` | Membuat koneksi JDBC ke database MySQL |
| `interfaces` | `Crud` | Interface kontrak `getAll`, `cariById`, dan `hapus` |
| `util` | `InputValidator` | Membaca input dari pengguna dan menyimpan aturan validasi |

</div>

<p align="justify">
Pemisahan tanggung jawab: <code>view</code> hanya mencetak, <code>controller</code> mengatur alur, <code>dao</code> hanya berurusan dengan SQL, dan <code>model</code> hanya menyimpan data beserta validasinya.
</p>

<hr>

<h2 align="center">3. Penjelasan Alur Program</h2>

<p align="justify">
<code>Main</code> hanya memanggil <code>new BookingController().jalankan()</code>. Menu ditampilkan berulang dengan <code>do-while</code> dan <code>switch</code> sampai pengguna memilih <code>0</code>. Setiap operasi data berjalan dengan alur berikut:
</p>

```
Main → BookingController → (BookingDAO / ArtistController → ArtistDAO) → DBConnection → MySQL
                ↓
          BookingView (menampilkan hasil)
```

<h3>3.1 Menu Utama</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/8fc082b2-4163-4063-8684-86d1abdf5291" />
</p>
<p align="center"><i>Gambar 2. Menu utama</i></p>

<p align="justify">
<code>BookingView.tampilkanMenuUtama()</code> menampilkan menu 1-7 dan 0 (keluar). Pilihan dibaca oleh <code>InputValidator.bacaInt()</code>. Jika yang dimasukkan bukan angka, atau angka di luar menu, program menampilkan pesan kesalahan lalu kembali ke menu.
</p>

<div align="center">

| Menu | Fungsi | Method yang dipanggil |
|---|---|---|
| 1 | Tambah Artist | `inputArtist()` → `ArtistDAO.insert()` |
| 2 | Tambah Booking | `inputBooking()` → `BookingDAO.insert()` |
| 3 | Lihat Semua Booking | `getAll()` → `BookingDAO.getAll()` |
| 4 | Update Booking | `inputUpdateBooking()` → `BookingDAO.update()` |
| 5 | Ubah Status Booking | `inputUbahStatus()` → `BookingDAO.updateStatus()` |
| 6 | Hapus Booking | `inputHapusBooking()` → `hapus()` → `BookingDAO.delete()` |
| 7 | Cari Booking berdasarkan ID | `cariBooking()` → `cariById()` → `BookingDAO.findById()` |
| 0 | Keluar | Menghentikan perulangan menu |

</div>

<h3>3.2 Tambah Artist (Menu 1)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/eaa2cd9a-7e1c-43f1-99dc-f94139c3e890" />
</p>
<p align="center"><i>Gambar 3. Tambah artist</i></p>

<p align="justify">
Pengguna mengisi nama, no HP, dan spesialisasi. <code>ArtistController.tambahArtist()</code> membuat objek <code>TattooArtist</code> (sehingga lolos validasi setter) lalu <code>ArtistDAO.insert()</code> menyimpannya ke tabel <code>tattoo_artist</code>. Jika berhasil tampil "Artist berhasil ditambahkan.", jika gagal tampil "Artist gagal ditambahkan.".
</p>

<h3>3.3 Tambah Booking (Menu 2)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/6e7e86a3-8791-4592-9bd0-2362e2a1d931" />
</p>
<p align="center"><i>Gambar 4. Tambah booking dengan memilih artist dari daftar</i></p>

<p align="justify">
Pengguna mengisi data pelanggan (nama, no HP, alamat). Program lalu mengambil daftar artist dari database dan menampilkannya <b>bernomor</b>; pengguna cukup memilih nomornya. Jika belum ada artist, program meminta artist ditambahkan lebih dulu. Setelah itu pengguna mengisi nama desain, harga, dan tanggal. Nomor pilihan diubah menjadi <code>id_artist</code> asli di database melalui <code>getIdByIndex()</code>, kemudian <code>BookingDAO.insert()</code> menyimpan data dengan status awal <code>Menunggu</code>.
</p>

<h3>3.4 Lihat Semua Booking (Menu 3)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/5bc18b99-11d5-4256-a77a-c4c214a58da6" />
</p>
<p align="center"><i>Gambar 5. Lihat semua booking (data dari database)</i></p>

<p align="justify">
<code>BookingDAO.getAll()</code> menjalankan <code>SELECT ... JOIN</code> antara tabel <code>booking</code> dan <code>tattoo_artist</code>, lalu setiap baris diubah menjadi objek <code>Booking</code>. <code>BookingView</code> menampilkan ID, info pelanggan, info artist, desain, harga, tanggal, dan status. Info pelanggan dan artist diambil dari <code>getInfo()</code> masing-masing class (polymorphism). Jika tabel kosong, tampil "(Belum ada data)".
</p>

<h3>3.5 Validasi Input</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/02d0a12f-bf89-4d71-8813-2e0df22f68e6" />
</p>
<p align="center"><i>Gambar 6. Validasi input</i></p>

<p align="justify">
Input dicek di <code>InputValidator</code> dan diulang sampai benar: nomor artist harus sesuai daftar, harga harus angka dan lebih dari 0, tanggal harus berformat <code>dd-mm-yyyy</code> dan nyata (misalnya <code>31-02-2026</code> ditolak, tahun kabisat diperhitungkan), serta teks tidak boleh kosong. Data kemudian dicek lagi di setter model sebagai pengaman terakhir.
</p>

<h3>3.6 Update Booking (Menu 4)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/44c7520d-b13e-41c4-b2c3-5634e6e16571" />
</p>
<p align="center"><i>Gambar 7. Update booking, Enter mempertahankan data lama</i></p>

<p align="justify">
Program menampilkan seluruh booking, pengguna memasukkan ID, lalu data saat ini ditampilkan lewat <code>cariById()</code>. Field yang dapat diubah adalah nama desain, harga, dan tanggal. Setiap field menampilkan nilai lama, dan <b>menekan Enter mempertahankan nilai lama</b> (<code>bacaStringOpsional</code>, <code>bacaHargaOpsional</code>, <code>bacaTanggalOpsional</code>). Perubahan disimpan oleh <code>BookingDAO.update()</code>. Jika ID tidak ditemukan, tampil "ID tidak ditemukan.".
</p>

<h3>3.7 Ubah Status Booking (Menu 5)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/c9dae2c7-8c99-4136-bf11-df6f8b89e186" />
</p>
<p align="center"><i>Gambar 8. Ubah status booking</i></p>

<p align="justify">
Pengguna memilih ID dari daftar, lalu mengisi status baru. Status hanya boleh <code>Menunggu</code>, <code>Dikerjakan</code>, atau <code>Selesai</code>, dan input diulang sampai sesuai. Perubahan disimpan oleh <code>BookingDAO.updateStatus()</code>.
</p>

<h3>3.8 Hapus Booking (Menu 6)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/710eaf1f-730d-40a4-b9ea-fc827664eac8" />
</p>
<p align="center"><i>Gambar 9. Hapus booking</i></p>

<p align="justify">
Pengguna memilih ID dari daftar yang ditampilkan, lalu <code>hapus()</code> (dari interface <code>Crud</code>) memanggil <code>BookingDAO.delete()</code>. Jika tidak ada baris yang terhapus, tampil "ID tidak ditemukan.".
</p>

<h3>3.9 Cari Booking berdasarkan ID (Menu 7)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/7fb7b1ce-f2fb-4ea8-a776-8301700bd286" />
</p>
<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/466c11b6-f2d2-4c42-9d8c-0b9f8b3b36bd" />
</p>
<p align="center"><i>Gambar 10. Cari booking berdasarkan ID</i></p>

<p align="justify">
Pengguna memasukkan ID, lalu <code>cariById()</code> memanggil <code>BookingDAO.findById()</code> yang menjalankan query dengan <code>WHERE id_booking = ?</code>. Jika ditemukan, detail booking ditampilkan oleh <code>BookingView.tampilkanDetailBooking()</code>; jika tidak, tampil "Booking dengan ID tersebut tidak ditemukan.".
</p>

<h3>3.10 Keluar (Menu 0)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/52396d50-4066-43ed-a38e-0e4ff06e5c51" />
</p>
<p align="center"><i>Gambar 11. Keluar dari program</i></p>

<p align="justify">
Program menampilkan "Terima kasih, program selesai." dan perulangan menu berhenti.
</p>

<hr>

<h2 align="center">4. Penerapan Encapsulation dan Inheritance</h2>

<h3>4.1 Encapsulation</h3>

<p align="justify">
Semua atribut di <code>Orang</code>, <code>Pelanggan</code>, <code>TattooArtist</code>, dan <code>Booking</code> bersifat <code>private</code> dan hanya diakses melalui getter dan setter. Setiap setter memanggil method validasi di <code>InputValidator</code>, dan nilai baru hanya disimpan jika lolos validasi.
</p>

<div align="center">

| Setter | Validasi |
|---|---|
| `Orang.setNama()` | tidak boleh kosong |
| `Orang.setNoHp()` | tidak boleh kosong |
| `Pelanggan.setAlamat()` | tidak boleh kosong |
| `TattooArtist.setSpesialisasi()` | tidak boleh kosong |
| `Booking.setNamaDesain()` | tidak boleh kosong |
| `Booking.setHarga()` | harus lebih dari 0 |
| `Booking.setTanggal()` | format `dd-mm-yyyy` dan tanggal harus nyata, termasuk tahun kabisat |
| `Booking.setStatus()` | hanya `Menunggu`, `Dikerjakan`, atau `Selesai` |

</div>

<p align="justify">
Contoh penerapan pada <code>model/Booking.java</code>:
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/0f53e044-5d3c-4867-a43d-28fcce8a6082" />
</p>
<p align="center"><i>Gambar 12. Encapsulation pada class Booking</i></p>

<p align="justify">
Constructor juga memanggil setter, sehingga data yang masuk ke objek, termasuk data yang dibaca dari database, selalu melewati validasi yang sama.
</p>

<p align="center"><b>Penggunaan <code>final</code></b></p>

<div align="center">

| Lokasi | Penggunaan `final` |
|---|---|
| `Booking` | `id`, `pelanggan`, dan `artist` bersifat `final` (tidak boleh berubah setelah objek dibuat) |
| `Orang` | `setNama()` dan `setNoHp()` bersifat `final` karena dipanggil di constructor |
| `InputValidator` | `final class`, dengan `Scanner sc` bersifat `private static final` |
| `BookingController` | `bookingDAO` dan `artistController` bersifat `final` |
| `ArtistController` | `artistDAO` bersifat `final` |
| `DBConnection` | `URL`, `USER`, dan `PASSWORD` bersifat `private static final` |
| `BookingDAO` | `SELECT_ALL` bersifat `private static final` |

</div>

<h3>4.2 Inheritance</h3>

<p align="justify">
<code>Orang</code> adalah superclass, dengan dua subclass yaitu <code>Pelanggan</code> dan <code>TattooArtist</code>. Kedua subclass mewarisi <code>nama</code>, <code>noHp</code>, beserta getter dan setter-nya dari <code>Orang</code>, lalu memanggil <code>super(nama, noHp)</code> pada constructor.
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/7152d7d8-7ede-4961-aa16-367ea00d3f45" />
</p>
<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/33438336-65c3-4346-8e66-b9aab3b5d843" />
</p>
<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/de3587f4-0c4d-449f-9015-86b2f46945b4" />
</p>
<p align="center"><i>Gambar 13. Inheritance</i></p>

<hr>

<h2 align="center">5. Penerapan Polymorphism dan Abstraction</h2>

<h3>5.1 Abstraction</h3>

<p align="justify">
<code>Orang</code> adalah <b>abstract class</b> dengan <b>abstract method</b> <code>getInfo()</code> (<code>model/Orang.java</code>). Class ini tidak dapat dibuat objeknya secara langsung, dan setiap subclass wajib mengimplementasikan <code>getInfo()</code>. Selain itu, interface <code>Crud</code> (<code>interfaces/Crud.java</code>) juga merupakan abstraksi yang berisi kontrak operasi data tanpa isi.
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/368962ab-d1e0-4f27-a202-48951f7ecf05" />
</p>
<p align="center"><i>Gambar 14. Abstraction</i></p>

<p align="justify">
<code>BookingController implements Crud</code> dan mengisi ketiga method dengan <code>@Override</code>, yang masing-masing meneruskan pemanggilan ke <code>BookingDAO</code>:
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/6e4b701b-99e5-48f3-9d68-425cced8d76e" />
</p>
<p align="center">
<img width="600" alt="image" src="https://github.com/user-attachments/assets/d28a3c0e-2580-4e08-b253-12911b328dca" />
</p>
<p align="center"><i>Gambar 15. Implementasi interface Crud pada BookingController</i></p>

<h3>5.2 Polymorphism - Overriding</h3>

<p align="justify">
<code>Pelanggan</code> dan <code>TattooArtist</code> meng-override <code>getInfo()</code> dengan isi yang berbeda.
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/a5499514-cb35-46a1-be42-f1b6b436e7a3" />
</p>
<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/7f194baa-7529-4351-a073-d966a2c5e622" />
</p>
<p align="center"><i>Gambar 16. Overriding</i></p>

<p align="justify">
<code>BookingView</code> memanggil method tersebut melalui reference bertipe <code>Orang</code>. Method yang dijalankan ditentukan oleh objek aslinya saat runtime.
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/02a8fe38-ce96-4d36-b5f7-e57b12270c07" />
</p>
<p align="center"><i>Gambar 17. Pemanggilan method hasil overriding</i></p>

<h3>5.3 Polymorphism - Overloading</h3>

<p align="justify">
<code>util/InputValidator.java</code> memiliki beberapa method dengan nama sama tetapi parameter berbeda.
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/d34444f9-4bfa-42e9-ae4e-92e1720f7d7b" />
</p>
<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/77e000f4-6272-4334-bc3c-7364d10021bb" />
</p>
<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/42d9bc7c-83de-405a-8097-543981bbba5f" />
</p>
<p align="center"><i>Gambar 18. Overloading</i></p>

<hr>

<h2 align="center">6. Penerapan JDBC</h2>

<p align="justify">
JDBC (Java Database Connectivity) dipakai agar program dapat menyimpan dan membaca data dari database <b>MySQL</b> bernama <code>db_studio_tattoo</code>. Driver yang dipakai adalah <code>mysql-connector-j</code> versi 8.3.0 yang dideklarasikan di <code>pom.xml</code>.
</p>

<h3>6.1 Dependency Driver (pom.xml)</h3>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/0fd17773-4362-4a62-b134-fc5d34f413e9" />
</p>
<p align="center"><i>Gambar 19. Dependency MySQL Connector/J pada pom.xml</i></p>

<h3>6.2 Struktur Database</h3>

<p align="justify">
Database terdiri dari dua tabel yang berelasi: <code>booking.id_artist</code> adalah foreign key ke <code>tattoo_artist.id_artist</code> (<code>ON UPDATE CASCADE ON DELETE RESTRICT</code>, sehingga artist yang masih dipakai booking tidak dapat dihapus).
</p>

<h3>6.3 Koneksi Database (<code>database/DBConnection.java</code>)</h3>

<p align="justify">
Class <code>DBConnection</code> menyimpan URL, user, dan password sebagai konstanta, lalu menyediakan method <code>getConnection()</code> yang memanggil <code>DriverManager.getConnection()</code>. Constructor-nya <code>private</code> sehingga class ini tidak bisa diinstansiasi dan hanya dipakai lewat method static.
</p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/85c4ee3f-0461-4538-8550-6aee44b27cef" />
</p>
<p align="center"><i>Gambar 21. Koneksi database pada DBConnection</i></p>

<h3>6.4 Data Access Object (<code>dao</code>)</h3>

<p align="justify">
Seluruh query SQL dikumpulkan di package <code>dao</code> (<code>BookingDAO</code> dan <code>ArtistDAO</code>) sehingga <code>controller</code> tidak berisi SQL sama sekali. Setiap operasi memakai tiga komponen JDBC: <code>Connection</code> untuk koneksi, <code>PreparedStatement</code> untuk mengirim query, dan <code>ResultSet</code> untuk membaca hasil SELECT.
</p>

<div align="center">

| Operasi | Method | SQL | Cara eksekusi |
|---|---|---|---|
| Create | `BookingDAO.insert()` | `INSERT INTO booking ...` | `executeUpdate()` |
| Create | `ArtistDAO.insert()` | `INSERT INTO tattoo_artist ...` | `executeUpdate()` |
| Read (semua) | `BookingDAO.getAll()` | `SELECT ... FROM booking JOIN tattoo_artist` | `executeQuery()` |
| Read (satu) | `BookingDAO.findById()` | `... WHERE id_booking = ?` | `executeQuery()` |
| Read | `ArtistDAO.getAll()` | `SELECT ... FROM tattoo_artist` | `executeQuery()` |
| Read | `ArtistDAO.getIdByIndex()` | `SELECT id_artist ... LIMIT 1 OFFSET ?` | `executeQuery()` |
| Update | `BookingDAO.update()` | `UPDATE booking SET nama_desain, harga, tanggal` | `executeUpdate()` |
| Update | `BookingDAO.updateStatus()` | `UPDATE booking SET status` | `executeUpdate()` |
| Delete | `BookingDAO.delete()` | `DELETE FROM booking WHERE id_booking = ?` | `executeUpdate()` |

</div>

<p align="center"><b>Contoh Create (INSERT)</b></p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/275f2b42-0e23-473e-bd48-932a0e415384" />
</p>
<p align="center"><i>Gambar 22. Contoh Create (INSERT) pada BookingDAO</i></p>

<p align="center"><b>Contoh Read (SELECT) dan pemetaan ResultSet ke objek</b></p>

<p align="center">
  <img width="600" alt="image" src="https://github.com/user-attachments/assets/16fadf9e-f3c1-4536-a292-2ad8e73e09c8" />
</p>
<p align="center"><i>Gambar 23. Contoh Read (SELECT) dan pemetaan ResultSet pada BookingDAO</i></p>

<p align="justify">
Method <code>map()</code> mengubah satu baris <code>ResultSet</code> menjadi objek <code>Booking</code> lengkap dengan <code>Pelanggan</code> dan <code>TattooArtist</code>, sehingga data dari database tetap melewati constructor dan setter (encapsulation) serta dapat ditampilkan lewat <code>getInfo()</code> (polymorphism).
</p>


<h2 align="center">7. Cara Menjalankan Program</h2>

1. Pastikan **MySQL** berjalan di `localhost:3306`.
2. Buat database, tabel, dan data awal dengan mengimpor file `db_studio_tattoo.sql`
3. Sesuaikan `USER` dan `PASSWORD` di `database/DBConnection.java` dengan akun MySQL masing-masing.
4. Build dengan Maven, lalu jalankan class `main.Main`.

```bash
mvn compile
mvn exec:java -Dexec.mainClass=main.Main
```

<p align="justify">
Catatan: <code>pom.xml</code> memakai <code>maven.compiler.release</code> 26, jadi dibutuhkan JDK yang mendukung versi tersebut (atau turunkan nilainya sesuai JDK yang terpasang).
</p>
