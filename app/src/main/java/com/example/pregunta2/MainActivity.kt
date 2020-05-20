package com.example.pregunta2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var Costo:Double=0.0
    var Cantidad:Double=0.0
    var Total:Double=0.0
    var Descuento:Double=0.0
    var CostoDelivery:Double=0.0
    var TotalPagar:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun CalcularTotales():Boolean
    {
      if (EdtCosto.text.isEmpty() || EdtCosto.text.equals("0"))
      {
          Toast.makeText(baseContext,"por favor ingresar la cantidad",Toast.LENGTH_LONG).show()
          return false
      }
      Costo=EdtCosto.text.toString().toDouble()
      Cantidad=EdtCantidad.text.toString().toDouble()
      Total=Costo*Cantidad

        if (Total>200) {
            Descuento=Total*0.05
        }else if(Total <=500)
        {
            Descuento=Total*0.05
        }


        if (Total>=500) {
            Descuento=Total*0.10
        } else if (Total >1000) {
            Descuento=Total*0.12
        }

        if (chkDelivery.isChecked)
        {
            CostoDelivery=12.0
        }
        TotalPagar=(Total-Descuento)+CostoDelivery
        return true;
    }
    public  fun btnCalcularClick(v:View)
    {
        if (CalcularTotales())
        {
            EdtTotal.setText(Total.toString())
            EdtDescuento.setText(Descuento.toString())
            EdtTotalPagar.setText(TotalPagar.toString())
        }
    }
    public  fun btnImprimir_OnClick(v:View)
    {
        EdtTotal.setText(Total.toString())
        EdtDescuento.setText(Descuento.toString())
        EdtTotalPagar.setText(TotalPagar.toString())

        var oVentanaImprimir:Intent= Intent(this,ConfirmarMenu::class.java)
        oVentanaImprimir.putExtra("DetallePedido",EdtDetallePedido.text)
        oVentanaImprimir.putExtra("Costo",EdtCosto.text)
        oVentanaImprimir.putExtra("Cantidad",EdtCantidad.text)
        oVentanaImprimir.putExtra("Total",EdtTotal.text)
        oVentanaImprimir.putExtra("Descuento",EdtDescuento.text)
        oVentanaImprimir.putExtra("Delivery",CostoDelivery.toString())
        oVentanaImprimir.putExtra("TotalPagar",EdtTotalPagar.toString())
        startActivity(oVentanaImprimir)
    }

    public fun btnllamarProveedor_OnClick(v:View)
    {
        var oActivity:Intent= Intent(this,ListadoProveedor::class.java)
        startActivity(oActivity)
    }
}
