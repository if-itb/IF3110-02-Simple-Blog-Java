Spesifikasi tugas WBD
=====================
1. Harus pakai **JSF**

1. Autentikasi / Login
    1. login harus bisa memiliki beberapa level role:
        1. Regular, dengan fitur
            * Dapat melihat post yang sudah di publish
            * dapat memberikan komentar terhadap suatu post yang sudah di 
              publish
            * Menambahkan post baru
            * Mengedit post yang sudah di-publish
            * Menghapus post sendiri
        1. Editor, dengan fitur
            * Mengedit seluruh jenis post
            * Melihat seluruh jenis post
            * Dapat memberikan komentar terhadap post yang sudah di publish
            * Mengubah status post dari unpublish menjadi published
        1. Admin, dengan fitur
            * dapat melakukan Create, Read, Update, Delete terhadap user yang 
              ada pada basis data
            * Melihat seluruh jenis post
            * Mengedit seluruh jenis post
            * Menghapus seluruh jenis post
            * dapat memberikan komentar terhadap suatu post yang sudah di
              publish
            * Menambahkan post baru
            * Mengubah status post dari unpublish menjadi published
    1. tetap bisa dipakai tanpa login (sebagai guest), dengan fitur:
        * Dapat memberikan komentar terhadap suatu post yang sudah di publish
        * Dapat melihat post yang sudah di publish
    1. Menggunakan **cookies** sehingga beberapa waktu setelah browser ditutup 
       data login user masih tetap ada
    
1. Sebuah Post mengandung
    * Judul
    * Tanggal
    * Konten
    * isPublished
    * isDeleted

1. List Post
    * Berisi daftar post yang sudah pernah dibuat dengan status **published**
    * terdapat menu untuk mengedit/menghapus post sesuai role
    
1. Add Post
    * Halaman untuk menambahkan post baru
    * memiliki form untuk mengisi atribut Post (Judul, Tanggal, Konten)
    * Lakukan **validasi** untuk tanggal dengan *javascript** agar tanggal yang
      dimasukkan >= tanggal saat menambahkan post
    * hanya dapat digunakan oleh **admin** dan **user reguler**
    * Setiap post yang baru ditambahkan memiliki status **unpublished**

1. Publish Post 
    * Menampilkan seluruh post yagn berstatus **unpublished**
    * Pada setiap post terdapat menu untuk meng-**edit** serta menerbitkan post 
      tersebut
    * Fitur ini hanya dapat digunakan oleh **admin** dan **editor**
    
1. Edit Post
    * Mengedit post yang sudah pernah dibuat
    * Form yang ditampilkan **sama** seperti menambahkan form baru
    * Fitur ini hanya dapat digunakan oleh **admin** dan **user reguler**
    
1. Delete Post
    * Menghapus post yang sudah pernah dibuat
    * Lakukan **konfirmasi** dengan **javascript** untuk konfirmasi terhadap 
      penghapusan post tersebut
    * Pesannya adalah "Apakah Anda yakin menghapus post ini?"
    * Jika yes maka post dihapus, jika no maka post tidak jadi dihapus

1. View Post
    * Halaman untuk melihat suatu post secara detail
    * Terdapat informasi post seperti Judul, Tanggal, dan Konten.
    * Pada halaman ini juga terdapat Komentar

1. Atribut Komentar
    * Nama orang yang komentar
    * Email orang yang komentar
    * Isi
    * Tanggal komentar dibuat

1. Komentar
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