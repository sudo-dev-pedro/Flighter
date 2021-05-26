package br.com.raywenderlich.flighter.ui.flight

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.raywenderlich.flighter.databinding.FragmentFlightDetailsBinding
import br.com.raywenderlich.flighter.ui.flight.FlightSearchFragment.Companion.FLIGHT_ID
import org.koin.android.ext.android.inject

class FlightDetailsFragment : Fragment() {
    private var flightIdArgument: Long? = null

    private var flightDetailsBinding: FragmentFlightDetailsBinding? = null
    private val flightDetailsViewModel: FlightDetailsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            flightIdArgument = it.getLong(FLIGHT_ID)
        }

        flightDetailsViewModel.getFlightById(flightIdArgument)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        flightDetailsBinding = FragmentFlightDetailsBinding.inflate(inflater, container, false)

        return flightDetailsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flightDetailsViewModel.flightDetails.observe(viewLifecycleOwner) { flightDetails ->
            flightDetailsBinding?.airlineName?.text = flightDetails.airlineName

            flightDetailsBinding?.fromInitials?.text = flightDetails.departureCityInitials
            flightDetailsBinding?.departDate?.text = flightDetails.departureDate
            flightDetailsBinding?.departTerminal?.text = flightDetails.departureTerminal.toString()

            flightDetailsBinding?.toInitials?.text = flightDetails.arrivalCityInitials
            flightDetailsBinding?.arriveDate?.text = flightDetails.arrivalDate
            flightDetailsBinding?.arriveTerminal?.text = flightDetails.arrivalTerminal.toString()

            flightDetailsBinding?.flightCodeContent?.text = flightDetails.id.toString()

            flightDetailsBinding?.estimatedFlightTimeContent?.text = flightDetails.estimatedFlightTime

            flightDetailsBinding?.flightPriceContent?.text = flightDetails.totalPrice.toString()
        }
    }

}