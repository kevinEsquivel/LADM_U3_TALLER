package mx.tecnm.tepic.ladm_u3_taller

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.sql.SQLException

class MainActivity2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    var bd = BDContext(this,"basedatos1",null,1)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var extra = intent.extras
        var idp = extra?.getString("IDP")!!
        edtIDP2.setText(edtIDP2.text.toString()+"IDP: ${idp}")


        btnRegisCoche.setOnClickListener {
            registrar(idp)
        }
        btnRegresar.setOnClickListener {
            var intent2 = Intent(this,MainActivity::class.java)
            startActivity(intent2)

        }
    }

    private fun registrar(idp: String) {
        try{
            var trans =  bd.writableDatabase

            var valores = ContentValues()
            valores.put("IDC",edtIDC.text.toString().toInt())
            valores.put("placa",edtPlaca.text.toString())
            valores.put("marca",edtMarca.text.toString())
            valores.put("modelo",edtModelo.text.toString())
            valores.put("IDP","${idp}".toString().toInt())

            var r = trans.insert("coche",null,valores)

            if(r==1L){
                mensaje("no se pudo insertar")
            }else{
                Toast.makeText(this,"Insertado", Toast.LENGTH_LONG).show()
/*                edtIDP.setText("")
                edtNombre.setText("")
                edtCelular.setText("")
                edtDomicilio.setText("")*/
            }
            trans.close()

        }catch(e: SQLException){
            mensaje(e.message)
        }

    }


    private fun mensaje(s: String?) {
        AlertDialog.Builder(this)
            .setTitle("ATENCION")
            .setMessage(s)
            .setPositiveButton("OK"){d,i->d.dismiss()}
            .show()
    }
}
