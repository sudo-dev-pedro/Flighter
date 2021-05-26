package br.com.phro.flighter.ui.flight.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.phro.flighter.R

class FlightSearchViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val fromInitials: TextView = itemView.findViewById(R.id.fromInitialsResult)
    val toInitials: TextView = itemView.findViewById(R.id.toInitialsResult)
    val departTime: TextView = itemView.findViewById(R.id.departTimeResult)
    val arriveTime: TextView = itemView.findViewById(R.id.arriveTimeResult)
    val estimatedFlightTIme: TextView = itemView.findViewById(R.id.estimatedFlightTime)
    val flightPrice: TextView = itemView.findViewById(R.id.flightPrice)
    val airlineName: TextView = itemView.findViewById(R.id.airlineName)
}