package br.com.raywenderlich.flighter.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.flighter.databinding.FragmentFlightSearchBinding
import br.com.raywenderlich.flighter.ui.flight.adapter.FlightSearchAdapter
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

        flightSearchViewModel
            .generateFlightsDemoData(
                searchBinding?.fromCityNameSearch?.text.toString(),
                searchBinding?.toCityNameSearch?.text.toString(),
                searchBinding?.flightDepartDateResult?.text.toString()
            )

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
    }

    // Irei precisar usar isso em algum local?
    companion object {

        private const val ARG_PARAM1 = "DEPARTURE"
        private const val ARG_PARAM2 = "ARRIVAL"
        private const val ARG_PARAM3 = "DATE"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            FlightSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}