/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;

/**
 *
 * @author anin7
 */
public class Connection {
    private static java.sql.Connection conn;
 
    public static java.sql.Connection getInstance() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:maso.db");
                conn.setAutoCommit(true);
                initDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
 
    private static void initDatabase() throws SQLException {
        Statement st = conn.createStatement();
 
        // Tabel users
        st.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id           INTEGER PRIMARY KEY AUTOINCREMENT,
                nama         TEXT NOT NULL,
                username     TEXT UNIQUE NOT NULL,
                password     TEXT NOT NULL,
                domisili     TEXT,
                umur         INTEGER,
                kelamin      TEXT,
                hobi         TEXT,
                tujuan       TEXT,
                deskripsi    TEXT,
                no_telepon   TEXT,
                foto_profil  TEXT
            )
        """);
 
        // Tabel swipes
        st.execute("""
            CREATE TABLE IF NOT EXISTS swipes (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id    INTEGER NOT NULL,
                target_id  INTEGER NOT NULL,
                interest   INTEGER NOT NULL DEFAULT 0,
                UNIQUE(user_id, target_id)
            )
        """);
 
        // Tabel matches
        st.execute("""
            CREATE TABLE IF NOT EXISTS matches (
                id        INTEGER PRIMARY KEY AUTOINCREMENT,
                user1_id  INTEGER NOT NULL,
                user2_id  INTEGER NOT NULL,
                UNIQUE(user1_id, user2_id)
            )
        """);
 
        // Tabel messages
        st.execute("""
            CREATE TABLE IF NOT EXISTS messages (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                sender_id   INTEGER NOT NULL,
                receiver_id INTEGER NOT NULL,
                pesan       TEXT NOT NULL,
                waktu       TEXT NOT NULL
            )
        """);
 
        // Seed data dari dataset_users.xlsx (97 user)
        seedUsers(st);
    }
 
    private static void seedUsers(Statement st) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT COUNT(*) as cnt FROM users");
        if (rs.next() && rs.getInt("cnt") > 0) return; // sudah ada data
 
        String[][] data = {
            {"Maya Cahya","maya.id","2$EjT3oB19","Jakarta","31","Perempuan","Bela Diri, Bersepeda","menikah","Orang bilang aku seru kalau udah kenal. Nama aku Maya, suka banget Bela Diri. Also into Bersepeda. Let's talk!"},
            {"Tari Surya","tari_12","X0d8LPsTZo","Bandung","24","Perempuan","Bersepeda, Bela Diri, Ngopi","menikah","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Tari! Hobby: Bersepeda & Bela Diri. DM open~"},
            {"Bagas Setiawan","xbagas770","@hZ9US!DUf","Manado","35","Laki-laki","Gaming","fwb","Hai, aku Bagas! Seorang pria yang gemar Gaming. Lagi cari teman baru yang seru diajak ngobrol soal Gaming."},
            {"Rina Prasetyo","_rina_3","#syk0Hrs0!","Padang","24","Perempuan","Nonton Film","fun date","Halo semuanya! Aku Rina dari Padang. Kalau nggak lagi Nonton Film, pasti lagi Nonton Film. Suka hal-hal baru!"},
            {"Rizky Saputra","_rizky_3","yxC6G6G4gj","Banjarmasin","20","Laki-laki","Musik","friends","Aku Rizky, seseorang yang percaya bahwa hidup harus dijalani dengan Musik. Juga suka Musik di waktu senggang."},
            {"Kevin Prasetyo","_kevin_7","CcSgac1KpS","Padang","29","Laki-laki","Musik, Nonton Film, Skateboard","fun date","Halo semuanya! Aku Kevin dari Padang. Kalau nggak lagi Musik, pasti lagi Nonton Film. Suka hal-hal baru!"},
            {"Desi Kurniawan","_desi8","Cc8yFQ87gc","Palembang","21","Perempuan","DIY Craft","fwb","Orang bilang aku seru kalau udah kenal. Nama aku Desi, suka banget DIY Craft. Also into DIY Craft. Let's talk!"},
            {"Dimas Permana","xdimas975","jOtUSkH$MV","Malang","31","Laki-laki","Melukis, Menulis, Membaca","fun date","Halo semuanya! Aku Dimas dari Malang. Kalau nggak lagi Melukis, pasti lagi Menulis. Suka hal-hal baru!"},
            {"Hani Kurniawan","xhani626","DOJmzkQyig","Bogor","31","Perempuan","Berkebun, Yoga, Nonton Film","friends","Simple person, Hani namaku. Hobi Berkebun, Yoga, dan kadang Nonton Film kalau mood lagi bagus. Hit me up!"},
            {"Maya Kurniawan","xmaya683","qn8!KyE!5s","Balikpapan","18","Perempuan","Olahraga, Membaca, Cosplay","fun date","Halo! Aku Maya, 18 tahun dari Balikpapan. Suka Olahraga dan Membaca, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Dewi Putri","dewi93","Fheh4MW61Y","Banjarmasin","32","Perempuan","Memasak","fun date","Orang bilang aku seru kalau udah kenal. Nama aku Dewi, suka banget Memasak. Also into Memasak. Let's talk!"},
            {"Raihan Pratama","_raihan3","OnKrxE57NO","Padang","23","Laki-laki","Ngopi, Nonton Film, Vlogging","fwb","Hey there! I'm Raihan, 23 y/o based in Padang. Passionate about Ngopi & Nonton Film. Let's vibe!"},
            {"Agus Saputra","agus_74","l#@uCzb#8C","Bogor","24","Laki-laki","Bersepeda, Ngopi","menikah","Hey there! I'm Agus, 24 y/o based in Bogor. Passionate about Bersepeda & Ngopi. Let's vibe!"},
            {"Rafi Hakim","xrafi644","sHh5jTmSHL","Padang","21","Laki-laki","Olahraga, Ngopi, Yoga","friends","Halo! Aku Rafi, 21 tahun dari Padang. Suka Olahraga dan Ngopi, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Galih Setiawan","galih92","p5aI1eQMO%","Bogor","33","Laki-laki","Yoga","fun date","Orang bilang aku seru kalau udah kenal. Nama aku Galih, suka banget Yoga. Also into Yoga. Let's talk!"},
            {"Putri Prasetyo","putri02","CKZS1OqLxp","Padang","23","Perempuan","Menulis","menikah","Hai, aku Putri! Seorang wanita yang gemar Menulis. Lagi cari teman baru yang seru diajak ngobrol soal Menulis."},
            {"Gilang Nugroho","gilang01","bCPWlG#757","Semarang","24","Laki-laki","Skateboard","fwb","Hey there! I'm Gilang, 24 y/o based in Semarang. Passionate about Skateboard & Skateboard. Let's vibe!"},
            {"Hafiz Putri","hafiz03","UuX9zX72th","Yogyakarta","22","Laki-laki","Menulis, Olahraga","fun date","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Hafiz! Hobby: Menulis & Olahraga. DM open~"},
            {"Tari Nugroho","xtari942","Ba$5B1ZWxo","Malang","23","Perempuan","Ngopi, Membaca","menikah","Orang bilang aku seru kalau udah kenal. Nama aku Tari, suka banget Ngopi. Also into Membaca. Let's talk!"},
            {"Putri Permana","_putri_5","3%f7O#VMD2","Bandung","19","Perempuan","Memasak, Gaming, Renang","fun date","Hey there! I'm Putri, 19 y/o based in Bandung. Passionate about Memasak & Gaming. Let's vibe!"},
            {"Ulfa Ramadhan","_ulfa3","VEPqrO0yM9","Bekasi","29","Perempuan","Berkebun, Membaca","menikah","Hey there! I'm Ulfa, 29 y/o based in Bekasi. Passionate about Berkebun & Membaca. Let's vibe!"},
            {"Nadia Wijaya","nadia_81","#ZI4fZM9qN","Balikpapan","32","Perempuan","Mendaki","fwb","Simple person, Nadia namaku. Hobi Mendaki, Mendaki, dan kadang Mendaki kalau mood lagi bagus. Hit me up!"},
            {"Salma Pratama","xsalma459","HsabEiRa42","Surabaya","22","Perempuan","Memancing","menikah","Halo semuanya! Aku Salma dari Surabaya. Kalau nggak lagi Memancing, pasti lagi Memancing. Suka hal-hal baru!"},
            {"Maya Saputra","maya04","V6n2KETQg%","Manado","21","Perempuan","Mendaki, Badminton","fwb","Halo semuanya! Aku Maya dari Manado. Kalau nggak lagi Mendaki, pasti lagi Badminton. Suka hal-hal baru!"},
            {"Taufik Surya","taufik90","D6%1PHpRPA","Samarinda","35","Laki-laki","Fotografi","fun date","Taufik, 35 tahun. Hidup itu singkat, jadi aku habiskan dengan Fotografi dan sesekali Fotografi. Yuk connect!"},
            {"Ivan Rahayu","ivan91","XnbUb7VHNc","Depok","34","Laki-laki","Memancing, Skateboard","friends","Ivan, 34 tahun. Hidup itu singkat, jadi aku habiskan dengan Memancing dan sesekali Skateboard. Yuk connect!"},
            {"Raihan Lestari","raihan_67","@$B2t5uvtD","Manado","30","Laki-laki","Tennis, Skateboard","fwb","Hai, aku Raihan! Seorang pria yang gemar Tennis. Lagi cari teman baru yang seru diajak ngobrol soal Skateboard."},
            {"Yuni Putra","yuni06","ZN4TmKwTq4","Depok","19","Perempuan","Mendaki, Melukis","fun date","Hai, aku Yuni! Seorang wanita yang gemar Mendaki. Lagi cari teman baru yang seru diajak ngobrol soal Melukis."},
            {"Bayu Prasetyo","bayu98","R59P#N392c","Denpasar","27","Laki-laki","DIY Craft, Gaming","fwb","Aku Bayu, seseorang yang percaya bahwa hidup harus dijalani dengan DIY Craft. Juga suka Gaming di waktu senggang."},
            {"Farhan Surya","xfarhan906","I4PbXuqiuq","Bekasi","33","Laki-laki","Mendaki, Olahraga","menikah","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Farhan! Hobby: Mendaki & Olahraga. DM open~"},
            {"Nadia Cahya","nadia98","vugl3PvUdq","Depok","27","Perempuan","Berkebun, Nonton Film","fwb","Nadia, 27 tahun. Hidup itu singkat, jadi aku habiskan dengan Berkebun dan sesekali Nonton Film. Yuk connect!"},
            {"Bagas Kusuma","bagas.id","vu2!IS6EFS","Tangerang","34","Laki-laki","Memancing","fun date","Halo! Aku Bagas, 34 tahun dari Tangerang. Suka Memancing dan Memancing, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Rafi Ramadhan","rafi.id","NUwYnisjRK","Yogyakarta","28","Laki-laki","Cosplay, Badminton","fun date","Halo! Aku Rafi, 28 tahun dari Yogyakarta. Suka Cosplay dan Badminton, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Dina Wijaya","dina.id","ya$sxCxaUz","Surabaya","20","Perempuan","Fotografi, Ngopi","menikah","Hai, aku Dina! Seorang wanita yang gemar Fotografi. Lagi cari teman baru yang seru diajak ngobrol soal Ngopi."},
            {"Yoga Susanto","_yoga1","YGrf4TAlcs","Samarinda","33","Laki-laki","Memancing, Skateboard, Vlogging","menikah","Halo semuanya! Aku Yoga dari Samarinda. Kalau nggak lagi Memancing, pasti lagi Skateboard. Suka hal-hal baru!"},
            {"Bagas Putra","bagas95","aOYHiDqHJc","Tangerang","30","Laki-laki","Tennis","menikah","Bagas, 30 tahun. Hidup itu singkat, jadi aku habiskan dengan Tennis dan sesekali Tennis. Yuk connect!"},
            {"Faisal Putra","faisal.id","l%ozqKHgSr","Bogor","19","Laki-laki","Olahraga, Menulis, Gaming","menikah","Hey there! I'm Faisal, 19 y/o based in Bogor. Passionate about Olahraga & Menulis. Let's vibe!"},
            {"Aulia Cahya","aulia01","D7odixrXMg","Yogyakarta","24","Perempuan","Membaca, Bersepeda, Memancing","fwb","Orang bilang aku seru kalau udah kenal. Nama aku Aulia, suka banget Membaca. Also into Bersepeda. Let's talk!"},
            {"Galih Fauzi","galih_74","v$Ccy$r4q8","Makassar","28","Laki-laki","Bersepeda, Memancing, Berkebun","fun date","Orang bilang aku seru kalau udah kenal. Nama aku Galih, suka banget Bersepeda. Also into Memancing. Let's talk!"},
            {"Zahra Surya","zahra06","GRPv3Ne8QE","Balikpapan","19","Perempuan","Olahraga","menikah","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Zahra! Hobby: Olahraga & Olahraga. DM open~"},
            {"Prisca Surya","prisca_56","SlgVYdo3BR","Jakarta","25","Perempuan","Vlogging","fwb","Halo semuanya! Aku Prisca dari Jakarta. Kalau nggak lagi Vlogging, pasti lagi Vlogging. Suka hal-hal baru!"},
            {"Faisal Setiawan","_faisal3","Ia%#MQOM3y","Balikpapan","32","Laki-laki","Renang, Nonton Film","fun date","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Faisal! Hobby: Renang & Nonton Film. DM open~"},
            {"Vina Saputra","_vina_3","RSl0M2FSjf","Pekanbaru","32","Perempuan","Traveling, Mendaki, Gaming","friends","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Vina! Hobby: Traveling & Mendaki. DM open~"},
            {"Zahra Lestari","zahra_61","LlaSkW!WPP","Bandung","22","Perempuan","Bela Diri, Berkebun","menikah","Hey there! I'm Zahra, 22 y/o based in Bandung. Passionate about Bela Diri & Berkebun. Let's vibe!"},
            {"Indah Surya","indah01","l7IMc67OnW","Padang","24","Perempuan","Olahraga, Ngopi","friends","Orang bilang aku seru kalau udah kenal. Nama aku Indah, suka banget Olahraga. Also into Ngopi. Let's talk!"},
            {"Daffa Maulana","xdaffa643","j7cwQYp$Fq","Surabaya","21","Laki-laki","Membaca, DIY Craft, Nonton Film","fwb","Aku Daffa, seseorang yang percaya bahwa hidup harus dijalani dengan Membaca. Juga suka DIY Craft di waktu senggang."},
            {"Naufal Hidayat","xnaufal793","5QP%7#dn6%","Depok","23","Laki-laki","Fotografi, Memasak","menikah","Hey there! I'm Naufal, 23 y/o based in Depok. Passionate about Fotografi & Memasak. Let's vibe!"},
            {"Daffa Wijaya","xdaffa725","KDXqhLeCpp","Jakarta","26","Laki-laki","Olahraga, Membaca, Gaming","fun date","Orang bilang aku seru kalau udah kenal. Nama aku Daffa, suka banget Olahraga. Also into Membaca. Let's talk!"},
            {"Gita Maulana","xgita200","e%g$K9gxBC","Jakarta","19","Perempuan","Melukis, Menulis","menikah","Halo! Aku Gita, 19 tahun dari Jakarta. Suka Melukis dan Menulis, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Ayu Surya","ayu_77","isBqjus6cF","Bandung","24","Perempuan","Bersepeda, Olahraga","menikah","Halo semuanya! Aku Ayu dari Bandung. Kalau nggak lagi Bersepeda, pasti lagi Olahraga. Suka hal-hal baru!"},
            {"Dimas Putra","dimas.id","H$O4U3G9BP","Semarang","35","Laki-laki","Cosplay","fun date","Hai, aku Dimas! Seorang pria yang gemar Cosplay. Lagi cari teman baru yang seru diajak ngobrol soal Cosplay."},
            {"Wulan Wibowo","xwulan626","SOwYMeJhlI","Denpasar","27","Perempuan","Musik, Renang, Badminton","menikah","Aku Wulan, seseorang yang percaya bahwa hidup harus dijalani dengan Musik. Juga suka Renang di waktu senggang."},
            {"Yoga Kusuma","yoga06","FG2$!ZlRYE","Bandung","19","Laki-laki","Badminton, Skateboard","friends","Orang bilang aku seru kalau udah kenal. Nama aku Yoga, suka banget Badminton. Also into Skateboard. Let's talk!"},
            {"Bella Putra","bella.id","oM5OeEBcu@","Bogor","29","Perempuan","Musik, Menulis, Ngopi","menikah","Orang bilang aku seru kalau udah kenal. Nama aku Bella, suka banget Musik. Also into Menulis. Let's talk!"},
            {"Ivan Hidayat","xivan620","KhFHxqK%CO","Yogyakarta","20","Laki-laki","Traveling","fun date","Ivan, 20 tahun. Hidup itu singkat, jadi aku habiskan dengan Traveling dan sesekali Traveling. Yuk connect!"},
            {"Zahra Kusuma","zahra.id","OsFSG8wkmt","Palembang","20","Perempuan","Vlogging, Menulis, Badminton","menikah","Orang bilang aku seru kalau udah kenal. Nama aku Zahra, suka banget Vlogging. Also into Menulis. Let's talk!"},
            {"Riko Surya","riko_16","nkknUn97FV","Bekasi","18","Laki-laki","Yoga, Bela Diri","friends","Riko, 18 tahun. Hidup itu singkat, jadi aku habiskan dengan Yoga dan sesekali Bela Diri. Yuk connect!"},
            {"Rina Ramadhan","xrina366","#PY8uyHEr5","Semarang","31","Perempuan","Yoga, Musik, Membaca","menikah","Rina, 31 tahun. Hidup itu singkat, jadi aku habiskan dengan Yoga dan sesekali Musik. Yuk connect!"},
            {"Vina Setiawan","vina.id","Oyul4C!OQO","Bekasi","30","Perempuan","Memancing, Nonton Film, Cosplay","menikah","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Vina! Hobby: Memancing & Nonton Film. DM open~"},
            {"Naufal Surya","naufal.id","Q8yk@ulCRj","Denpasar","32","Laki-laki","Melukis, Skateboard, Membaca","menikah","Naufal, 32 tahun. Hidup itu singkat, jadi aku habiskan dengan Melukis dan sesekali Skateboard. Yuk connect!"},
            {"Zaki Lestari","zaki.id","@roAgiJ#el","Manado","30","Laki-laki","Traveling, Memasak","friends","Orang bilang aku seru kalau udah kenal. Nama aku Zaki, suka banget Traveling. Also into Memasak. Let's talk!"},
            {"Agus Prabowo","agus96","9j8$XGJ4nR","Bekasi","29","Laki-laki","Renang, Yoga, Ngopi","fwb","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Agus! Hobby: Renang & Yoga. DM open~"},
            {"Rina Saputra","rina00","ysdnpGtGU!","Pekanbaru","25","Perempuan","Skateboard, Memasak","menikah","Nama aku Rina, tinggal di Pekanbaru. Weekendku biasanya diisi dengan Skateboard atau Memasak. Orangnya friendly dan nggak ribet!"},
            {"Aulia Setiawan","aulia91","jdx@g6UX!t","Malang","34","Perempuan","Musik","menikah","Simple person, Aulia namaku. Hobi Musik, Musik, dan kadang Musik kalau mood lagi bagus. Hit me up!"},
            {"Citra Putri","citra.id","3G%OI%LtcZ","Bandung","30","Perempuan","Traveling, Melukis","fwb","Halo! Aku Citra, 30 tahun dari Bandung. Suka Traveling dan Melukis, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Riko Hidayat","_riko_7","bOPvxVNRvB","Malang","23","Laki-laki","Memancing, Badminton, Bela Diri","fun date","Orang bilang aku seru kalau udah kenal. Nama aku Riko, suka banget Memancing. Also into Badminton. Let's talk!"},
            {"Fira Lestari","fira_82","nmTO@gYUYZ","Depok","22","Perempuan","Gaming, Traveling","fun date","Halo semuanya! Aku Fira dari Depok. Kalau nggak lagi Gaming, pasti lagi Traveling. Suka hal-hal baru!"},
            {"Ayu Saputra","ayu.id","r7FId9GLG7","Medan","19","Perempuan","Menulis, Mendaki","fwb","Halo! Aku Ayu, 19 tahun dari Medan. Suka Menulis dan Mendaki, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Raihan Permana","raihan90","6UalFWMRLP","Bogor","35","Laki-laki","Gaming, Tennis, Skateboard","menikah","Halo semuanya! Aku Raihan dari Bogor. Kalau nggak lagi Gaming, pasti lagi Tennis. Suka hal-hal baru!"},
            {"Yoga Hidayat","xyoga482","UhOYHzTKQy","Palembang","31","Laki-laki","Olahraga","friends","Yoga, 31 tahun. Hidup itu singkat, jadi aku habiskan dengan Olahraga dan sesekali Olahraga. Yuk connect!"},
            {"Faisal Hidayat","faisal_98","KTpDO0$4ou","Tangerang","30","Laki-laki","Cosplay, Mendaki","friends","Hai, aku Faisal! Seorang pria yang gemar Cosplay. Lagi cari teman baru yang seru diajak ngobrol soal Mendaki."},
            {"Hendra Saputra","_hendra_4","wOLYazPqTi","Tangerang","33","Laki-laki","Traveling, Bela Diri","friends","Hendra, 33 tahun. Hidup itu singkat, jadi aku habiskan dengan Traveling dan sesekali Bela Diri. Yuk connect!"},
            {"Zahra Kusuma","_zahra_7","Dq@t73MpjE","Depok","23","Perempuan","Menulis, Yoga","fwb","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Zahra! Hobby: Menulis & Yoga. DM open~"},
            {"Taufik Prasetyo","taufik.id","MH0dw2C81m","Palembang","35","Laki-laki","Tennis, Musik, Renang","friends","Taufik, 35 tahun. Hidup itu singkat, jadi aku habiskan dengan Tennis dan sesekali Musik. Yuk connect!"},
            {"Fira Prasetyo","fira_59","GEly$r%W@u","Semarang","34","Perempuan","DIY Craft, Membaca","fwb","Simple person, Fira namaku. Hobi DIY Craft, Membaca, dan kadang DIY Craft kalau mood lagi bagus. Hit me up!"},
            {"Naufal Maulana","_naufal5","FkCaTxib8Y","Pekanbaru","29","Laki-laki","Bersepeda, Badminton","fwb","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Naufal! Hobby: Bersepeda & Badminton. DM open~"},
            {"Zaki Putri","zaki_29","i99vaWBfSe","Depok","31","Laki-laki","Vlogging, Skateboard, Musik","fwb","Halo! Aku Zaki, 31 tahun dari Depok. Suka Vlogging dan Skateboard, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Hafiz Nugroho","hafiz.id","h5irtZToP5","Semarang","24","Laki-laki","Yoga, Skateboard, Traveling","friends","Aku Hafiz, seseorang yang percaya bahwa hidup harus dijalani dengan Yoga. Juga suka Skateboard di waktu senggang."},
            {"Zahra Kusuma","_zahra_8","YUTW!fP87R","Surabaya","32","Perempuan","Tennis","friends","Hai, aku Zahra! Seorang wanita yang gemar Tennis. Lagi cari teman baru yang seru diajak ngobrol soal Tennis."},
            {"Fira Maulana","xfira915","psSwD3fMda","Banjarmasin","19","Perempuan","Vlogging, Renang, Memancing","menikah","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Fira! Hobby: Vlogging & Renang. DM open~"},
            {"Vina Fauzi","vina_fauzi","$ru1SpFM71","Jakarta","20","Perempuan","DIY Craft, Menulis","fun date","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Vina! Hobby: DIY Craft & Menulis. DM open~"},
            {"Tari Setiawan","tari.id","T$Zh#$rBSR","Semarang","18","Perempuan","Memasak, Yoga","menikah","Aku Tari, seseorang yang percaya bahwa hidup harus dijalani dengan Memasak. Juga suka Yoga di waktu senggang."},
            {"Gilang Wijaya","_gilang_6","A4mqXJwwga","Semarang","27","Laki-laki","Memasak","menikah","Hai, aku Gilang! Seorang pria yang gemar Memasak. Lagi cari teman baru yang seru diajak ngobrol soal Memasak."},
            {"Kevin Wijaya","xkevin725","WxdAJSKeBW","Banjarmasin","26","Laki-laki","DIY Craft","fun date","Nama aku Kevin, tinggal di Banjarmasin. Weekendku biasanya diisi dengan DIY Craft atau DIY Craft. Orangnya friendly dan nggak ribet!"},
            {"Vina Cahya","vina_88","$D!uCIWORN","Makassar","23","Perempuan","Menulis, Bela Diri","friends","Simple person, Vina namaku. Hobi Menulis, Bela Diri, dan kadang Menulis kalau mood lagi bagus. Hit me up!"},
            {"Siti Putra","siti.id","Qj4rb2!NCq","Jakarta","21","Perempuan","Menulis","fun date","Aku Siti, seseorang yang percaya bahwa hidup harus dijalani dengan Menulis. Juga suka Menulis di waktu senggang."},
            {"Kevin Hakim","kevin91","K1iY@$OHYM","Bandung","34","Laki-laki","Bela Diri, Mendaki, Vlogging","friends","Simple person, Kevin namaku. Hobi Bela Diri, Mendaki, dan kadang Vlogging kalau mood lagi bagus. Hit me up!"},
            {"Siti Maulana","siti_73","b$2qw49eCp","Manado","28","Perempuan","Memancing, Melukis, Bersepeda","friends","Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Siti! Hobby: Memancing & Melukis. DM open~"},
            {"Citra Pratama","citra_pratama","SARy$eC!pg","Jakarta","23","Perempuan","Memancing, Musik, Bela Diri","friends","Simple person, Citra namaku. Hobi Memancing, Musik, dan kadang Bela Diri kalau mood lagi bagus. Hit me up!"},
            {"Irfan Susanto","irfan90","!DcScH224A","Tangerang","35","Laki-laki","Nonton Film, Renang","fwb","Halo semuanya! Aku Irfan dari Tangerang. Kalau nggak lagi Nonton Film, pasti lagi Renang. Suka hal-hal baru!"},
            {"Citra Surya","xcitra995","FVpwu59H6F","Depok","20","Perempuan","Berkebun, Badminton","fwb","Citra, 20 tahun. Hidup itu singkat, jadi aku habiskan dengan Berkebun dan sesekali Badminton. Yuk connect!"},
            {"Dani Prabowo","dani03","Mf$@nVYWS8","Bandung","22","Laki-laki","Badminton","fun date","Hey there! I'm Dani, 22 y/o based in Bandung. Passionate about Badminton & Badminton. Let's vibe!"},
            {"Hakim Hidayat","hakim91","tnZO0eVBRV","Padang","34","Laki-laki","Vlogging, Yoga, Gaming","friends","Hai, aku Hakim! Seorang pria yang gemar Vlogging. Lagi cari teman baru yang seru diajak ngobrol soal Yoga."},
            {"Ivan Prasetyo","_ivan_3","2qBTvPCZeZ","Padang","20","Laki-laki","Membaca, Yoga","fwb","Aku Ivan, seseorang yang percaya bahwa hidup harus dijalani dengan Membaca. Juga suka Yoga di waktu senggang."},
            {"Tari Wijaya","tari_50","nimsJ663SX","Pekanbaru","22","Perempuan","Menulis, Tennis","fwb","Aku Tari, seseorang yang percaya bahwa hidup harus dijalani dengan Menulis. Juga suka Tennis di waktu senggang."},
            {"Dina Rahayu","xdina902","p$eee#sB9!","Jakarta","18","Perempuan","DIY Craft","fun date","Hey there! I'm Dina, 18 y/o based in Jakarta. Passionate about DIY Craft & DIY Craft. Let's vibe!"},
            {"Prisca Rahayu","prisca_79","5Bw1mbiugT","Depok","30","Perempuan","Memasak, Badminton","friends","Halo! Aku Prisca, 30 tahun dari Depok. Suka Memasak dan Badminton, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Dimas Prasetyo","xdimas684","EzuyHTkduR","Surabaya","20","Laki-laki","Bersepeda","fun date","Hai, aku Dimas! Seorang pria yang gemar Bersepeda. Lagi cari teman baru yang seru diajak ngobrol soal Bersepeda."},
            {"Nadia Lestari","_nadia_7","AJPG59YXWp","Semarang","32","Perempuan","Ngopi, Olahraga","fun date","Halo! Aku Nadia, 32 tahun dari Semarang. Suka Ngopi dan Olahraga, orangnya easy-going dan open-minded. Yuk kenalan!"},
            {"Rizky Saputra","rizky.id","40cZqo6!e$","Surabaya","35","Laki-laki","Melukis, Renang","menikah","Orang bilang aku seru kalau udah kenal. Nama aku Rizky, suka banget Melukis. Also into Renang. Let's talk!"}
        };
 
        PreparedStatement ps = conn.prepareStatement(
            "INSERT OR IGNORE INTO users (nama,username,password,domisili,umur,kelamin,hobi,tujuan,deskripsi,no_telepon,foto_profil) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
        );
        for (String[] row : data) {
            ps.setString(1, row[0]);
            ps.setString(2, row[1]);
            ps.setString(3, row[2]);
            ps.setString(4, row[3]);
            ps.setInt(5, Integer.parseInt(row[4]));
            ps.setString(6, row[5]);
            ps.setString(7, row[6]);
            ps.setString(8, row[7]);
            ps.setString(9, row[8]);
            ps.setString(10, "");
            ps.setString(11, "");
            ps.addBatch();
        }
        ps.executeBatch();
    }
}
