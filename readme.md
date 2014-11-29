Spesifikasi tugas WBD
=====================
1. Harus pakai **JSF**

1. Autentikasi / Login
    1. login harus bisa memiliki beberapa level role:
        1. Regular, dengan fitur
            - Dapat melihat post yang sudah di publish **checked**
            - dapat memberikan komentar terhadap suatu post yang sudah di 
              publish **checked**
            - Menambahkan post baru
            - Mengedit post yang sudah di publish
            - Menghapus post sendiri **checked**
        2. Editor, dengan fitur
            - Mengedit post yang belum di publish
            - Melihat post yang sudah di publish **checked**
            - Dapat memberikan komentar terhadap post yang sudah di publish **checked**
            - Mengubah status post dari unpublish menjadi published **checked**
        3. Admin, dengan fitur
            - dapat melakukan Create, Read, Update, Delete terhadap user yang 
              ada pada basis data
            - Melihat seluruh jenis post **checked**
            - Mengedit seluruh jenis post
            - Menghapus seluruh jenis post **checked**
            - dapat memberikan komentar terhadap suatu post yang sudah di
              publish **checked**
            - Menambahkan post baru
            - Mengubah status post dari unpublish menjadi published **checked**
    2. tetap bisa dipakai tanpa login (sebagai guest), dengan fitur:
        - Dapat memberikan komentar terhadap suatu post yang sudah di publish **checked**
        - Dapat melihat post yang sudah di publish **checked**
    3. Menggunakan **cookies** sehingga beberapa waktu setelah browser ditutup 
       data login user masih tetap ada
    
2. Sebuah Post mengandung
    - Judul
    - Tanggal
    - Konten
    - isPublished
    - isDeleted

3. List Post
    * Berisi daftar post yang sudah pernah dibuat dengan status **published**
    * terdapat menu untuk mengedit/menghapus post sesuai role
    
4. Add Post
    * Halaman untuk menambahkan post baru
    * memiliki form untuk mengisi atribut Post (Judul, Tanggal, Konten)
    * Lakukan **validasi** untuk tanggal dengan *javascript** agar tanggal yang
      dimasukkan >= tanggal saat menambahkan post
    * hanya dapat digunakan oleh **admin** dan **user reguler**
    * Setiap post yang baru ditambahkan memiliki status **unpublished**

5. Publish Post 
    * Menampilkan seluruh post yagn berstatus **unpublished**
    * Pada setiap post terdapat menu untuk meng-**edit** serta menerbitkan post 
      tersebut
    * Fitur ini hanya dapat digunakan oleh **admin** dan **editor**
    
6. Edit Post
    * Mengedit post yang sudah pernah dibuat
    * Form yang ditampilkan **sama** seperti menambahkan form baru
    * Fitur ini hanya dapat digunakan oleh **admin** dan **user reguler**
    
7. Delete Post
    * Menghapus post yang sudah pernah dibuat
    * Lakukan **konfirmasi** dengan **javascript** untuk konfirmasi terhadap 
      penghapusan post tersebut
    * Pesannya adalah "Apakah Anda yakin menghapus post ini?"
    * Jika yes maka post dihapus, jika no maka post tidak jadi dihapus

8. View Post
    * Halaman untuk melihat suatu post secara detail
    * Terdapat informasi post seperti Judul, Tanggal, dan Konten.
    * Pada halaman ini juga terdapat Komentar

9. Atribut Komentar
    * Nama orang yang komentar
    * Email orang yang komentar
    * Isi
    * Tanggal komentar dibuat

0. Komentar
    * Komentar berisi daftar komentar yang ditulis untuk post tertentu
    * Form komentar berisi Nama, Email, dan Komentar.
    * Untuk user yang sudah login, field nama dan email tidak perlu diisi lagi,
      nilainya langsung diambil dari basis data user yang bersangkutan.
    * Lakukan **Validasi** email dengan javascript
    * Komentar dibuat dengan ajax, ajax dipanggil saat loading View Post, dan 
      Menambahkan komentar baru

1. Manajemen User
    * Menampilkan seluruh user yang terdapat pada basis data
    * Terdapat menu untuk dapat melakukan CRUD
    * Fitur hanya dapat diakses oleh **admin**


Bonus
=====
1. Upload Gambar
1. Soft Delete
1. WYSIWYG untuk Form Post
1. Tampilan

Todo
====
- delete ada konfirmasi <- **Kevin** simple javascript

user:
- edit published post bermasalah
    - jadi ada karakter aneh <- **Eric**: fix dengan prepared statement *(1)*
    - jika waktu invalid setelah ditekan ga melakukan apa2 <- **Winson**
- add post beramasalah
    - jadi ada karakter aneh <- *(1)*
    - kemungkinan memiliki kesalahan jika waktu invalid setelah ditekan ga melakukan apa2
      
editor: 
- edit unpublished post bermasalah
    - jadi ada karakter aneh <- (1)
    - jika waktu invalid setelah ditekan ga melakukan apa2 
    - waktu jadi mundur 1 hari <- solved dengan mengubah getDate() menjadi return string
    
admin:
- crud bermasalah
    - habis add user kok gak kosong? <- **Eric**: fix dengan edit kode java Viewpost
- edit harusnya bermasalah seperti yang di atas <- masih ada?
- hard delete <- **Kevin** simple MySQL
      
page list: 
- view_post
- edit_post
- crud
- index
- editor_menu
