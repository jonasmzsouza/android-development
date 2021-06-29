package br.com.fiap.webservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var cep : EditText
    lateinit var pesquisaCEP : Button
    lateinit var progress_bar : ProgressBar
    lateinit var rua : EditText
    lateinit var cidade : EditText
    lateinit var uf : EditText
    lateinit var pesquisaRCE : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cep = findViewById(R.id.cep)
        pesquisaCEP = findViewById(R.id.pesquisaCEP)
        progress_bar = findViewById(R.id.progress_bar)
        rua = findViewById(R.id.rua)
        cidade = findViewById(R.id.cidade)
        uf = findViewById(R.id.uf)
        pesquisaRCE = findViewById(R.id.pesquisaRCE)

        // Ao clicar no botão número 1 será pesquisado o logradouro com o número do CEP
        pesquisaCEP.setOnClickListener {

            progress_bar.visibility = View.VISIBLE

            val call = RetrofitFactory().retrofitService().getCEP(cep.text.toString())

            call.enqueue(object : Callback<CEP> {

                override fun onResponse(call: Call<CEP>, response: Response<CEP>) {

                    response.body()?.let {
                        Log.i("CEP", it.toString())
                        Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_LONG).show()
                        progress_bar.visibility = View.INVISIBLE
                    } ?: Toast.makeText(this@MainActivity, "CEP não localizado", Toast.LENGTH_LONG)
                            .show()

                }

                override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                    t?.message?.let { it1 -> Log.e("Erro", it1) }
                    progress_bar.visibility = View.INVISIBLE
                }
            })
        }

        // Ao clicar no botão número 2 será pesquisado o logradouro com os dados:
        // RUA, CIDADE e ENDEREÇO
        pesquisaRCE.setOnClickListener {

            progress_bar.visibility = View.VISIBLE

            val call = RetrofitFactory().retrofitService().getRCE(
                    uf.text.toString(),
                    cidade.text.toString(),
                    rua.text.toString()
            )

            call.enqueue(object : Callback<List<CEP>> {

                override fun onResponse(call: Call<List<CEP>>?, response: Response<List<CEP>>?) {

                    response?.body()?.let {
                        Log.i("CEP", it.toString())
                        Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_LONG).show()
                        progress_bar.visibility = View.INVISIBLE
                    } ?: Toast.makeText(
                            this@MainActivity,
                            "Endereço não localizado ",
                            Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<List<CEP>>?, t: Throwable?) {
                    t?.message?.let { it1 -> Log.e("Erro", it1) }
                    progress_bar.visibility = View.INVISIBLE
                }
            })
        }
    }
}