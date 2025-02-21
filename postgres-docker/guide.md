**Prerequisite Instalasi:**
- Git
- GitHub Desktop (opsional)
- Docker Desktop / Docker Engine

# Langkah 1: Instalasi Git & Docker Desktop
Pada langkah ini, tidak ada setelah khusus yang butuh diubah. Silakan mengikuti proses instalasi normal.

# Langkah 2: Clone Git Repository
Untuk mendapatkan file dari repository Git yang sudah disediakan, lakukan beberapa langkah berikut:

## Cloning Repository Pertama Kali
**(GitHub Desktop)**
File > Clone Repository
   Isi `URL` dengan `https://github.com/NathanAdhitya/praktikum-bd-resources`
   Ubah `Local path` sesuai keinginan (tempat menyimpan file yang telah diclone).

**(Git Bash)**
```bash
git clone https://github.com/NathanAdhitya/praktikum-bd-resources.git
```

## Melakukan Sync Local terhadap Repository Remote
Untuk memperbarui file local agar sama dengan isi repository pada web:

**(GitHub Desktop)**
Repository > Pull

**(Git Bash)**
```bash
git pull
```

# Langkah 3: Menjalankan File Docker Compose
1. Pastikan Docker Desktop sudah berjalan/menyala dengan normal.
2. Buka PowerShell / Git Bash pada folder letak `docker-compose.yml`
3. Ketikkan/copas `docker compose up -d` pada terminal. Tunggu beberapa saat agar aplikasi otomatis mendownload PostgreSQL 16.

Jika berhasil menyala, Docker Compose akan memberikan respons:
![[Pasted image 20240224172106.png]]

Selamat, Anda berhasil menjalankan PostgreSQL. Silakan mencermati bagian [Maintenance] untuk cara mematikan dan menghapus PostgreSQL.

# Maintenance
## Mematikan Docker Desktop
**(Windows)**
Cari icon Docker Desktop pada Taskbar > Klik Kanan > Quit Docker Desktop.
## Menyalakan Container PostgreSQL
**(Terminal / PowerShell / Git Bash)** (pastikan sudah di dalam directory letak `docker-compose.yml`)
```bash
docker compose up -d
```
## Merestart Container PostgreSQL
**(Terminal / PowerShell / Git Bash)** (pastikan sudah di dalam directory letak `docker-compose.yml`)
```bash
docker compose restart
```
## Menghapus Container PostgreSQL
**(Terminal / PowerShell / Git Bash)** (pastikan sudah di dalam directory letak `docker-compose.yml`)
```bash
docker compose down
```

## Menghapus Data PostgreSQL
**(Terminal / PowerShell / Git Bash)** (pastikan sudah di dalam directory letak `docker-compose.yml`)
```bash
docker compose down -v
```
*Arti `-v` adalah menghapus volume (letak data) container beserta container.*
