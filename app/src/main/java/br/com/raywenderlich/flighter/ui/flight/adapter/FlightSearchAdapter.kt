package br.com.raywenderlich.flighter.ui.flight.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.flighter.R
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.ui.flight.viewholder.FlightSearchViewHolder

class FlightSearchAdapter : RecyclerView.Adapter<FlightSearchViewHolder>() {

    private var flightList: List<Flight> = emptyList()
    var onClickAction: (flight: Flight) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightSearchViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_flight_result, parent, false
            )

        return FlightSearchViewHolder(item)
    }

    override fun onBindViewHolder(holder: FlightSearchViewHolder, position: Int) {
        holder.itemView.apply {
            holder.fromInitials.text = flightList[position].departureCityInitials
            holder.toInitials.text = flightList[position].arrivalCityInitials
            holder.estimatedFlightTIme.text = flightList[position].estimatedFlightTime
            holder.arriveTime.text = flightList[position].arrivalDate
            holder.departTime.text = flightList[position].departureDate
            // Formate para o formato de reais
            holder.flightPrice.text = flightList[position].totalPrice.toString()
            setOnClickListener {
                onClickAction(flightList[position])
            }
        }
    }

    override fun getItemCount(): Int = flightList.size

    fun updateFlightList(newFlightsList: List<Flight>) {
        flightList = newFlightsList
        notifyDataSetChanged()
    }

    fun onClickItem(flight: (Flight) -> Unit) {
        onClickAction = flight
    }
}