package com.smq.puntoventa.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smq.puntoventa.R
import com.smq.puntoventa.data.models.ProductoAdd

class AdapterListProductosAdd(private val mCtx: Context, private val mList: List<ProductoAdd>) :
    RecyclerView.Adapter<AdapterListProductosAdd.ViewHolder>() {

    var onItemClick: ((ProductoAdd) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto_add, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productoAdd = mList[position]

        holder.textViewDescripcionProducto.text = productoAdd.descripcion
        holder.textViewCantidad.text =  productoAdd.cantidad
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val textViewDescripcionProducto: TextView = itemView.findViewById(R.id.textViewDescripcionProducto)
        val textViewCantidad: TextView = itemView.findViewById(R.id.textViewCantidad)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }
    }
}