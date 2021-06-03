package br.com.phro.flighter.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.phro.flighter.R
import br.com.phro.flighter.database.entity.Flight
import br.com.phro.flighter.databinding.FragmentFlightSearchBinding
import br.com.phro.flighter.ui.flight.adapter.FlightSearchAdapter
import org.koin.android.ext.android.inject

class FlightSearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    private var searchBinding: FragmentFlightSearchBinding? = null
    private var rvFlightsResult: RecyclerView? = null

    private val flightSearchViewModel: FlightSearchViewModel by inject()
    private val flightSearchAdapter: FlightSearchAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentFlightSearchBinding.inflate(inflater, container, false)

        return searchBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        searchBinding?.fromCityNameSearch?.text = param1
        searchBinding?.toCityNameSearch?.text = param2
        searchBinding?.flightDepartDateResult?.text = param3

        flightSearchViewModel.getFlights(
            searchBinding?.fromCityNameSearch?.text.toString(),
            searchBinding?.toCityNameSearch?.text.toString()
        )

        observeLiveData()
    }

    private fun observeLiveData() {
        flightSearchViewModel.flightResults.observe(viewLifecycleOwner) {
            flightSearchAdapter.updateFlightList(it)
        }
    }

    private fun initRecyclerView() {
        rvFlightsResult = searchBinding?.rvFlightsResult
        rvFlightsResult?.adapter = flightSearchAdapter
        rvFlightsResult?.setHasFixedSize(true)
        flightSearchAdapter.onClickItem {
            navigateToFlightDetails(it)
        }
    }

    private fun navigateToFlightDetails(flight: Flight) {
        findNavController()
            .navigate(
                R.id.action_navigation_search_to_navigation_flight_details,
                prepareBundle(flight)
            )
    }

    private fun prepareBundle(flight: Flight): Bundle {
        return Bundle().apply {
            flight.id?.let {
                putLong(FLIGHT_ID, it)
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "DEPARTURE"
        private const val ARG_PARAM2 = "ARRIVAL"
        private const val ARG_PARAM3 = "DATE"
        const val FLIGHT_ID = "FLIGHT_ID"
    }
}