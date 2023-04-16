package com.smq.puntoventa.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smq.puntoventa.R
import com.smq.puntoventa.data.models.Venta

class AdapterListVentas(private val mCtx: Context, private val mList: List<Venta>) :
    RecyclerView.Adapter<AdapterListVentas.ViewHolder>() {

    var onItemClick: ((Venta) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_venta, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val venta = mList[position]

        holder.textViewIdVenta.text = "Id Venta: " + venta.idVenta.toString()
        holder.textViewFecha.text =  "Fecha: " + venta.fecha
        holder.textViewIdCliente.text =  "Id Cliente: " + venta.idCliente.toString()
        if(venta.idTipoPago == 1){
            holder.textViewIdTipoPago.text =  "Tipo Pago: Efectivo"
        }else{
            holder.textViewIdTipoPago.text =  "Tipo Pago: Credito"
        }

    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewIdVenta: TextView = itemView.findViewById(R.id.textViewIdVenta)
        val textViewFecha: TextView = itemView.findViewById(R.id.textViewFecha)
        val textViewIdCliente: TextView = itemView.findViewById(R.id.textViewIdCliente)
        val textViewIdTipoPago: TextView = itemView.findViewById(R.id.textViewIdTipoPago)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }
    }
}