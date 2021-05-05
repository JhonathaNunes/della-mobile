package com.della.della_mobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.della.della_mobile.R
import com.della.della_mobile.models.Client

class ClientAdapter (val clients: List<Client>, val onClick: (Client) -> Unit):
    RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    class ClientViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardName: TextView
        val cardEmail: TextView

        init {
            cardName = view.findViewById<TextView>(R.id.card_name)
            cardEmail = view.findViewById<TextView>(R.id.card_email)
        }
    }

    override fun getItemCount() = this.clients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_adapter, parent, false)

        val holder = ClientViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.itemView.context

        val client = clients[position]

        holder.cardName.text = client.fullName
        holder.cardEmail.text = client.email

        holder.itemView.setOnClickListener {onClick(client)}
    }
}