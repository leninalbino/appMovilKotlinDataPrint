package idat.dadmi.appmovilkotlindataprint.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import idat.dadmi.appmovilkotlindataprint.CaracterizticasActivity
import idat.dadmi.appmovilkotlindataprint.R
import idat.dadmi.appmovilkotlindataprint.databinding.ActivityMainBinding
import idat.dadmi.appmovilkotlindataprint.utilitarios.Constantes
import idat.dadmi.appmovilkotlindataprint.utilitarios.Metodos
import idat.dadmi.appmovilkotlindataprint.utilitarios.SharedPreferencesManager
import idat.dadmi.appmovilkotlindataprint.viewmodel.UsuarioViewModel
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        //inicializamos el ViewModel
        usuarioViewModel = ViewModelProvider(this)
                                .get(UsuarioViewModel::class.java)

        //var token = Constantes().PREF_RECORDAR
        //Toast.makeText(this@MainActivity, "Token es: " + token,Toast.LENGTH_LONG).show()

        /*binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.HomeFragment,
                R.id.CategoriaFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mostrarInformacionAuth()
    }

    private fun mostrarInformacionAuth() {
        val tvnomusuario : TextView = binding.navView.getHeaderView(0)
            .findViewById(R.id.tvnombreusuario)
        val tvemailusuario: TextView = binding.navView.getHeaderView(0)
            .findViewById(R.id.tvemailuser)
        usuarioViewModel.obtener().observe(this, Observer { usuario ->
            usuario?.let {
                tvnomusuario.setText(usuario.user)
               tvemailusuario.setText(usuario.correo)
            }
        })
    }

    //direccionar login y carrito
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val idItem=item.itemId

        if (idItem == R.id.actio_logout){
            startActivity(Intent(this,LoginActivity::class.java))
            usuarioViewModel.eliminartodo()
            SharedPreferencesManager().setSomeStringValue(Constantes().PREF_TOKEN,"")
            finish()

        }
        when(item.itemId){
            R.id.action_login -> {
                startActivity(Intent(this,LoginActivity::class.java))
                return true;
            }
            R.id.carrito ->{
                startActivity(Intent(this,CarritoActivity::class.java))
                //Toast.makeText(this,"Carrito",Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //fin direccionar login y carrito

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}