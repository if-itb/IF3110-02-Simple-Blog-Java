Spesifikasi tugas WBD
=====================
1. Harus pakai JSF
2. Autentikasi / Login
    a. login harus bisa memiliki beberapa level role:
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
    b. tetap bisa dipakai tanpa login (sebagai guest), dengan fitur:
        i. Dapat memberikan komentar terhadap suatu post yang sudah di publish
        ii. Dapat melihat post yang sudah di publish
    
3a. Sebuah Post mengandung
    a. Judul
    b. Tanggal
    c. Konten
    d. isPublished
3. List Post
    a. Berisi daftar post yang sudah pernah dibuat dengan status published
    b. terdapat menu untuk mengedit/menghapus post sesuai role
4. Add Post
    a. Halaman untuk menambahkan post baru
    b. memiliki form untuk mengisi atribut Post
    c. Lakukan validasi untuk tanggal dengan JAVASCRIPT agar tanggal yang
       dimasukkan >= tanggal saat menambahkan post
    d. hanya dapat digunakan oleh admin dan user reguler
    e. Setiap post yang baru ditambahkan memiliki status unpublished
5. Publish Post 
    a. Menampilkan seluruh post yagn berstatus <strong>unpublished</strong>
    b. Pada setiap post terdapat menu untuk meng-edit serta menerbitkan post tersebut
    c. Fitur ini hanya dapat digunakan oleh admin dan editor
    
6. Edit Post
    a. Mengedit post yang sudah pernah dibuat
    b. Form yang ditampilkan <strong>sama</strong> seperti menambahkan form baru
    c. Fitur ini hanya dapat digunakan oleh admin dan user reguler
    
7. Delete Post
    a. Menghapus post yang sudah pernah dibuat
    b. Lakukan <strong>konfirmasi</strong> dengan javascript untuk konfirmasi
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
    d. Lakukan <strong>Validasi</strong> email dengan javascript
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