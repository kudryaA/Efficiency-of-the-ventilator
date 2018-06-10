package kudrya.assessmentofventilation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.round
import kotlin.math.sqrt

class ResultActivity : AppCompatActivity() {

    private fun y(t: Double): Double = 0.465 * 750 / t
    private fun h2(h: Double, s: Double, f: Double): Double
        = h*s*s / (f*f + s*s)
    private fun v(dh2: Double, yv: Double): Double = sqrt(2*9.8*dh2/yv)

    private fun solve(builder: StringBuilder, h2: Double, tC: Double, tH: Double, label: String) {
        builder.append("\n")
        builder.append("***Для приміщення в $label період року\n")
        val yz = y(tC)
        builder.append("Об'ємна вага повітря ззовні(yз):\n")
        builder.append("yz=$yz\n")
        val yv = y(tH)
        builder.append("Об'ємна вага повітря всередині(yв):\n")
        builder.append("yв=$yv\n")
        val dh2 = h2 * (yz - yv)
        builder.append("Тепловий тиск, під дією якого буде виходити повітря з кватирки(ΔH2), кг/м²\n")
        builder.append("ΔH2=$dh2\n")
        val v = v(dh2, yv)
        builder.append("Швидкість виходу повітря через кватирку(Vп,м/с):\n")
        builder.append("Vп=$v\n")
        val f = intent.getDoubleExtra("f", 0.0)
        val Lf = f * v * 0.6 * 3600
        builder.append("Фактичний повітрообмін(Lф, м3/год):\n")
        builder.append("Lф=$Lf\n")
        builder.append("Висновок:\n")
        val t = round(60 * Lh / Lf).toInt()
        builder.append("Необхідно кожну годину провітрювати по $t хв\n")
    }

    private var Lh: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val builder = StringBuilder()
        val textViewResult: TextView = findViewById(R.id.textViewResult)
        val a = intent.getDoubleExtra("a",0.0)
        val b = intent.getDoubleExtra("b",0.0)
        val c = intent.getDoubleExtra("c", 0.0)
        val n = intent.getDoubleExtra("count", 0.0)
        val f = intent.getDoubleExtra("f", 0.0)
        val s = intent.getDoubleExtra("s", 0.0)
        val h = intent.getDoubleExtra("h", 0.0)
        val V = a*b*c
        builder.append("Об'єм приміщення(V, м³):\n")
        builder.append("V=$a*$b*$c=$V\n")
        val l = when {
            V/n < 20 -> 30
            V/n < 40 -> 20
            else -> 1
        }
        builder.append("$V/$n=${V/n}->L'=$l\n")
        Lh = l * n
        builder.append("Необхідний повітрообмін(Lн, м³/год):\n")
        builder.append("Lн=$l*$n=$Lh\n")
        val h2 = h2(h, s, f)
        builder.append("Висота від площини рівних тисків до центру кватирки:\n")
        builder.append("h2=$h2\n")
        solve(builder, h2, 297.0, 301.0, "теплий")
        solve(builder, h2, 262.0, 298.0, "холодний")
        textViewResult.text = builder.toString()
    }
}
