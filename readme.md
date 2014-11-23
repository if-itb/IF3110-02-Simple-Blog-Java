Spesifikasi tugas WBD
=====================
1. Harus pakai JSF
2. Autentikasi / Login<br>
    a. login harus bisa memiliki beberapa level role:<br>
        i. Regular, dengan fitur<br>
            -Dapat melihat post yang sudah di publish<br>
            -dapat memberikan komentar terhadap suatu post yang sudah di publish<br>
            -Menambahkan post baru<br>
            -Mengedit post yang sudah di-publish<br>
        ii. Editor, dengan fitur<br>
            -Mengedit seluruh jenis post<br>
            -Melihat seluruh jenis post<br>
            -Dapat memberikan komentar terhadap post yang sudah di publish<br>
            -Mengubah status post dari unpublish menjadi published<br>
        iii. Admin, dengan fitur<br>
            -dapat melakukan Create, Read, Update, Delete terhadap user<br>
             yang ada pada basis data<br>
            -Melihat seluruh jenis post<br>
            -Mengedit seluruh jenis post<br>
            -dapat memberikan komentar terhadap suatu post yang sudah di publish<br>
            -Menambahkan post baru<br>
            -Mengubah status post dari unpublish menjadi published<br>
    b. tetap bisa dipakai tanpa login (sebagai guest), dengan fitur:<br>
        i. Dapat memberikan komentar terhadap suatu post yang sudah di publish<br>
        ii. Dapat melihat post yang sudah di publish
    
3a. Sebuah Post mengandung<br>
    a. Judul<br>
    b. Tanggal<br>
    c. Konten<br>
    d. isPublished
3. List Post<br>
    a. Berisi daftar post yang sudah pernah dibuat dengan status published<br>
    b. terdapat menu untuk mengedit/menghapus post sesuai role
4. Add Post<br>
    a. Halaman untuk menambahkan post baru<br>
    b. memiliki form untuk mengisi atribut Post<br>
    c. Lakukan validasi untuk tanggal dengan JAVASCRIPT agar tanggal yang<br>
       dimasukkan >= tanggal saat menambahkan post<br>
    d. hanya dapat digunakan oleh admin dan user reguler<br>
    e. Setiap post yang baru ditambahkan memiliki status unpublished
5. Publish Post <br>
    a. Menampilkan seluruh post yagn berstatus <strong>unpublished</strong><br>
    b. Pada setiap post terdapat menu untuk meng-edit serta menerbitkan post tersebut<br>
    c. Fitur ini hanya dapat digunakan oleh admin dan editor
    
6. Edit Post<br>
    a. Mengedit post yang sudah pernah dibuat<br>
    b. Form yang ditampilkan <strong>sama</strong> seperti menambahkan form baru<br>
    c. Fitur ini hanya dapat digunakan oleh admin dan user reguler
    
7. Delete Post<br>
    a. Menghapus post yang sudah pernah dibuat<br>
    b. Lakukan <strong>konfirmasi</strong> dengan javascript untuk konfirmasi<br>
       terhadap penghapusan post tersebut<br>
    c. Pesannya adalah "Apakah Anda yakin menghapus post ini?"<br>
    d. Jika yes maka post dihapus, jika no maka post tidak jadi dihapus
8. View Post<br>
    a. Halaman untuk melihat suatu post secara detail<br>
    b. Terdapat informasi post seperti Judul, Tanggal, dan Konten.<br>
    c. Pada halaman ini juga terdapat Komentar
9a. Atribut Komentar<br>
    a. Nama orang yang komentar<br>
    b. Email orang yang komentar<br>
    c. Isi<br>
    d. Tanggal komentar dibuat
9. Komentar<br>
    a. Komentar berisi daftar komentar yang ditulis untuk post tertentu<br>
    b. Form komentar berisi Nama, Email, dan Komentar.<br>
    c. Untuk user yang sudah login, field nama dan email tidak perlu diisi lagi,<br>
       nilainya langsung diambil dari basis data user yang bersangkutan.<br>
    d. Lakukan <strong>Validasi</strong> email dengan javascript<br>
    e. Komentar dibuat dengan ajax, ajax dipanggil saat loading View Post,<br>
       dan Menambahkan komentar baru

10. Manajemen User<br>
    a. Menampilkan seluruh user yang terdapat pada basis data<br>
    b. Terdapat menu untuk dapat melakukan CRUD<br>
    c. Fitur hanya dapat diakses oleh admin


Bonus
=====
1. Upload Gambar
2. Soft Delete
3. WYSIWYG untuk Form Post
4. Tampilan