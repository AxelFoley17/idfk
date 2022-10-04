package hu.bme.aut.android.simpledrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import hu.bme.aut.android.simpledrawer.databinding.ActivityDrawingBinding
import hu.bme.aut.android.simpledrawer.view.DrawingView

class DrawingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrawingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_style_line -> {
                binding.canvas.currentDrawingStyle = DrawingView.DRAWING_STYLE_LINE
                item.isChecked = true
                true
            }
            R.id.menu_style_point -> {
                binding.canvas.currentDrawingStyle = DrawingView.DRAWING_STYLE_POINT
                item.isChecked = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val toolbarMenu: Menu = binding.toolbar.menu
        menuInflater.inflate(R.menu.menu_toolbar, toolbarMenu)
        for (i in 0 until toolbarMenu.size()) {
            val menuItem: MenuItem = toolbarMenu.getItem(i)
            menuItem.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
            if (menuItem.hasSubMenu()) {
                val subMenu: SubMenu = menuItem.subMenu
                for (j in 0 until subMenu.size()) {
                    subMenu.getItem(j)
                        .setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
                }
            }
        }
        return super.onCreateOptionsMenu(menu)
    }


}