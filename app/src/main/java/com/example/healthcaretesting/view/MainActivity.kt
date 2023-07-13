package com.example.healthcaretesting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.model.ArticleDao
import com.example.healthcaretesting.model.HealthCareDatabase
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.model.UserDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var articleDao: ArticleDao
    private lateinit var userDao: UserDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //nav host fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment

        //nav controller
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        //toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)


        //navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        //nav view
        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)

        //bottom nav
        setSupportActionBar(toolbar)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(bottomNav, navController)


        //Database Setup
        // Get an instance of the HealthCareDatabase
        var healthCareDatabase = HealthCareDatabase(this)

        // Call the prepopulateData function to insert dummy data
        CoroutineScope(Dispatchers.IO).launch {
            prepopulateData()
        }

        // Get the DAOs from the database
        articleDao = healthCareDatabase.articleDao()
        userDao = healthCareDatabase.userDao()


    }


    public fun setComponentsVisibility(visible: Boolean) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navView = findViewById<NavigationView>(R.id.navView)

        if (visible) {
            toolbar.visibility = View.VISIBLE
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            bottomNav.visibility = View.VISIBLE
            navView.visibility = View.VISIBLE
        } else {
            toolbar.visibility = View.GONE
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            bottomNav.visibility = View.GONE
            navView.visibility = View.GONE
        }
    }

    private suspend fun prepopulateData() {
        val articles = listOf(
            Article(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Syringe2.jpg/1280px-Syringe2.jpg",
                "Artikel Jarum Suntik",
                "Dr Joe",
                "Jarum suntik adalah pompa bolak-balik sederhana yang terdiri dari pendorong (meskipun dalam jarum suntik modern, ini sebenarnya adalah piston) yang terpasang erat di dalam tabung silinder yang disebut tong.",
                "2023-07-02"),

            Article(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Rotavirus_Reconstruction.jpg/330px-Rotavirus_Reconstruction.jpg",
                "Virus Mutasi Baru",
                "Dr Alex",
                "Virus adalah mikroorganisme patogen yang hanya dapat bereplikasi di dalam sel karena mereka tidak memiliki perlengkapan seluler untuk bereproduksi sendiri.",
                "2023-06-12"),

            Article("https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Hospital-de-Bellvitge.jpg/390px-Hospital-de-Bellvitge.jpg",
                "Pembangunan Rumah Sakit UBAYA",
                "Christian Felix",
                "Pembangunan rumah sakit UBAYA berlangsung selama 1 tahun 2 bulan, berlokasi di dekat kampus utama tenggilis",
                "2023-06-01"),

            Article("https://t3.ftcdn.net/jpg/02/60/04/08/360_F_260040863_fYxB1SnrzgJ9AOkcT0hoe7IEFtsPiHAD.jpg",
                "Kelulusan 100% Fakultas Kedokteran UBAYA",
                "Eko Wahyu",
                "Angkatan 2020 Fakultas Kedokteran UBAYA memperoleh",
                "2023-02-02"),

            Article("https://hips.hearstapps.com/goodhousekeeping/assets/17/42/1508535101-drug-store.jpg",
                "Apotek UBAYA",
                "Sandi Purnawa",
                "Apotek UBAYA menerima BPJS dan buka selama 24 Jam untuk melayani mahasiswa",
                "2022-11-12"),

            Article("https://media.npr.org/assets/img/2019/07/01/gettyimages-826753434_custom-4b8d6dc04d2e72b759aae4abc32570d6c84f15b9.jpg",
                "Obat Herbal",
                "Johny Evan",
                "Obat herbal berbahan alami sedang dikembangkan oleh Fakultas Kedokteran UBAYA, obat ini berfungsi untuk meredakan rasa meriang dan mual-mual",
                "2023-10-02"),
        )

        val users = listOf(
            User("Arthur Jamie", "arthurjamie", "081233229012", "1", "arthur123"),
            User("Billy Hopkins", "billyhops12", "081299332210", "1", "billy123"),
            User("Jean Carlson", "jeancarlson99", "08218833201", "2", "jeancarlson123")
        )

        // Prepopulate articles
//        articleDao.insertAll(articles)

        //Prepopulate users
//        userDao.userRegisterAll(users)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }
}