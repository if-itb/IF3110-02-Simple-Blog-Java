Spesifikasi tugas WBD
=====================
1. Harus pakai JSF
2. Autentikasi / Login
    * login harus bisa memiliki beberapa level role:
        i. Regular, dengan fitur
            -Dapat melihat post yang sudah di publish
            -dapat memberikan komentar terhadap suatu post yang sudah di publish
            -Menambahkan post baru
            -Mengedit post yang sudah di-publish
        ii. Editor, dengan fitur
            -Mengedit seluruh jenis post
            -Melihat seluruh jenis post
            -Dapat memberikan komentar terhadap post yang sudah di publish
            -Mengubah status post dari unpublish menjadi published
        iii. Admin, dengan fitur
            -dapat melakukan Create, Read, Update, Delete terhadap user
             yang ada pada basis data
            -Melihat seluruh jenis post
            -Mengedit seluruh jenis post
            -dapat memberikan komentar terhadap suatu post yang sudah di publish
            -Menambahkan post baru
            -Mengubah status post dari unpublish menjadi published
    * tetap bisa dipakai tanpa login (sebagai guest), dengan fitur:
        * Dapat memberikan komentar terhadap suatu post yang sudah di publish
        * Dapat melihat post yang sudah di publish
    
3a. Sebuah Post mengandung
    a. Judul
    b. Tanggal
    c. Konten
    d. isPublished

3. List Post
    * Berisi daftar post yang sudah pernah dibuat dengan status published
    * terdapat menu untuk mengedit/menghapus post sesuai role
    
4. Add Post
    * Halaman untuk menambahkan post baru
    * memiliki form untuk mengisi atribut Post
    * Lakukan validasi untuk tanggal dengan JAVASCRIPT agar tanggal yang
       dimasukkan >= tanggal saat menambahkan post
    * hanya dapat digunakan oleh admin dan user reguler
    * Setiap post yang baru ditambahkan memiliki status unpublished

5. Publish Post 
    * Menampilkan seluruh post yagn berstatus **unpublished**>
    * Pada setiap post terdapat menu untuk meng-edit serta menerbitkan post tersebut
    * Fitur ini hanya dapat digunakan oleh admin dan editor
    
6. Edit Post
    a. Mengedit post yang sudah pernah dibuat
    b. Form yang ditampilkan **sama**> seperti menambahkan form baru
    c. Fitur ini hanya dapat digunakan oleh admin dan user reguler
    
7. Delete Post
    a. Menghapus post yang sudah pernah dibuat
    b. Lakukan **konfirmasi**> dengan javascript untuk konfirmasi
       terhadap penghapusan post tersebut
    c. Pesannya adalah "Apakah Anda yakin menghapus post ini?"
    d. Jika yes maka post dihapus, jika no maka post tidak jadi dihapus
8. View Post
    a. Halaman untuk melihat suatu post secara detail
    b. Terdapat informasi post seperti Judul, Tanggal, dan Konten.
    c. Pada halaman ini juga terdapat Komentar
9a. Atribut Komentar
    a. Nama orang yang komentar
    b. Email orang yang komentar
    c. Isi
    d. Tanggal komentar dibuat
9. Komentar
    a. Komentar berisi daftar komentar yang ditulis untuk post tertentu
    b. Form komentar berisi Nama, Email, dan Komentar.
    c. Untuk user yang sudah login, field nama dan email tidak perlu diisi lagi,
       nilainya langsung diambil dari basis data user yang bersangkutan.
    d. Lakukan **Validasi**> email dengan javascript
    e. Komentar dibuat dengan ajax, ajax dipanggil saat loading View Post,
       dan Menambahkan komentar baru

10. Manajemen User
    a. Menampilkan seluruh user yang terdapat pada basis data
    b. Terdapat menu untuk dapat melakukan CRUD
    c. Fitur hanya dapat diakses oleh admin


Bonus
=====
1. Upload Gambar
2. Soft Delete
3. WYSIWYG untuk Form Post
4. Tampilan