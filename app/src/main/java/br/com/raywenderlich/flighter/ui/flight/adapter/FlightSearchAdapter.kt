package br.com.raywenderlich.flighter.ui.flight.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.flighter.R
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.ui.flight.viewholder.FlightSearchViewHolder

class FlightSearchAdapter(
    private val flightList: List<Flight>
    // Listener para o clique
) : RecyclerView.Adapter<FlightSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightSearchViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_flight_result, parent, false
            )

        return FlightSearchViewHolder(item)
    }

    override fun onBindViewHolder(holder: FlightSearchViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = flightList.size
}