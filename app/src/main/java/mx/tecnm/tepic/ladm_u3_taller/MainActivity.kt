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
import java.sql.SQLException

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    var bd = BDContext(this,"basedatos1",null,1)
    //var listaID=ArrayList<String>()
    var valores = ContentValues()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGuardar.setOnClickListener(){
            insertar()
        }
        btnRegistrar.setOnClickListener(){
            var intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("IDP",valores.get("IDP").toString())
            startActivity(intent)
        }
    }

    private fun insertar() {
        try{
            var trans =  bd.writableDatabase


            valores.put("IDP",edtIDP.text.toString().toInt())
            valores.put("nombre",edtNombre.text.toString())
            valores.put("celular",edtCelular.text.toString())
            valores.put("domicilio",edtDomicilio.text.toString())

            var r = trans.insert("propietario",null,valores)

            if(r==1L){
                mensaje("no se pudo insertar")
            }else{
                Toast.makeText(this,"Insertado",Toast.LENGTH_LONG).show()
/*                edtIDP.setText("")
                edtNombre.setText("")
                edtCelular.setText("")
                edtDomicilio.setText("")*/
            }
            trans.close()

        }catch(e:SQLException){
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