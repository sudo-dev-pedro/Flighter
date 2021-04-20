package br.com.raywenderlich.flighter.ui.flight.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.flighter.R

class FlightSearchViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val fromInitials: TextView = itemView.findViewById(R.id.fromInitialsResult)
    val toInitials: TextView = itemView.findViewById(R.id.toInitialsResult)
    val departTime: TextView = itemView.findViewById(R.id.departTime)
    val arriveTime: TextView = itemView.findViewById(R.id.arriveTime)
    val estimatedFlightTIme: TextView = itemView.findViewById(R.id.estimatedFlightTime)
    val flightPrice: TextView = itemView.findViewById(R.id.flightPrice)
}