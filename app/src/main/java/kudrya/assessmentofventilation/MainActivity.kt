package kudrya.assessmentofventilation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textA: EditText? = null
    private var textB: EditText? = null
    private var textC: EditText? = null
    private var textCount: EditText? = null
    private var textF: EditText? = null
    private var textS: EditText? = null
    private var textH: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textA = findViewById(R.id.textA)
        textB = findViewById(R.id.textB)
        textC = findViewById(R.id.textC)
        textCount = findViewById(R.id.textCount)
        textF = findViewById(R.id.textF)
        textS = findViewById(R.id.textS)
        textH = findViewById(R.id.textH)
    }

    fun clickSolve(v: View?) {
        if (textA!!.text.isEmpty() || textB!!.text.isEmpty() || textC!!.text.isEmpty() ||
                textF!!.text.isEmpty() || textS!!.text.isEmpty() || textH!!.text.isEmpty() ||
                    textCount!!.text.isEmpty()) {
            Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("a", textA!!.text.toString().toDouble())
            intent.putExtra("b", textB!!.text.toString().toDouble())
            intent.putExtra("c", textC!!.text.toString().toDouble())
            intent.putExtra("count", textCount!!.text.toString().toDouble())
            intent.putExtra("f", textF!!.text.toString().toDouble())
            intent.putExtra("s", textS!!.text.toString().toDouble())
            intent.putExtra("h", textH!!.text.toString().toDouble())
            startActivity(intent)
        }
    }
}
