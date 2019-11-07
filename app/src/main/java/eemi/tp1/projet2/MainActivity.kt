package eemi.tp1.projet2

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.content.ContextCompat.getSystemService
import android.view.LayoutInflater

class MainActivity : AppCompatActivity() {
    var adapter: MyListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MyListAdapter(this, this)

        for(i in 1..100) {
            adapter?.add("Yo")
        }

        findViewById<ListView>(R.id.testlist).adapter = adapter
        findViewById<ListView>(R.id.testlist).setOnItemClickListener { adapterView, view, i, l ->
            Log.e("TEST", "tapped on $i")
        }
    }
}

class MyListAdapter(context: Context, activity: Activity) : ArrayAdapter<String>(context, R.layout.line) {
    var parentActivity = activity
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var result : View
        if(convertView != null) {
            result = convertView
        } else {
            // method 1: get the system inflater
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflater2 = parentActivity.layoutInflater

            result = inflater2.inflate(R.layout.line, null)
        }

        result.findViewById<TextView>(R.id.main).text = this.getItem(position)
        result.findViewById<TextView>(R.id.counter).text = "$position"

        result.minimumHeight = 100

        return result
    }
}
