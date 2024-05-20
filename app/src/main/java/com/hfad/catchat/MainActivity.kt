package com.hfad.catchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<MaterialToolbar>(R.id.toolbar)
        // Получаем ссылку на панель инструментов
        setSupportActionBar(toolBar)
        //

        // --------Получаем ссылку на контроллер навигации из хоста навигации----------
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Мы используем метод supportFragmentManager, чтобы получить доступ изнутри активити
        // к ссылке фрагмента. Находим по ссылке держателя фрагмента(хоста навигации) из xml активити.
        // Так же используем небезопасное приведение типа (as) ибо метод findFragmentById может не вернуть
        // ссылку на используемый фрагмент. Хз по какой причине. Может быть фрагмент не надуется или еще че нидь.
        val navController = navHostFragment.navController
        // После того как мы достали ссылку на фрагмент, мы достаем из него контроллер

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)

        // --------Строим конфигурацию, связывающую панель инструментов с графом навигации-------
        val builder = AppBarConfiguration.Builder(navController.graph)
        //
        val appBarConfiguration = builder.build()
        //
        toolBar.setupWithNavController(navController, appBarConfiguration)
        //
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        // Для того чтобы в панели инструментов появились наши элементы, нам нужно показать андроил
        // От куда следует надувать элементы добавленные в menu_toolbar.xml
        return super.onCreateOptionsMenu(menu)
    }
    // Добавление элементов на меню инструментов. В данном случае элемент Help (определен в xml файле)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        // Опять получаем ссылку на контроллер через ссылку на хост навигации
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
    // Переопределяем метод обрабатывающий нажатие по элементу. Метод распознает пункт, выбранный
    // пользователем, через MenuItem. Но мы здесь его не используем. Просто задаем действие смены
    // фрагмента к цели
}