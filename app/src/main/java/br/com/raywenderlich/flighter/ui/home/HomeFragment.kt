package br.com.raywenderlich.flighter.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.raywenderlich.flighter.R
import br.com.raywenderlich.flighter.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val calendar: Calendar = Calendar.getInstance()

    private var homeBinding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        return homeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding?.departInput?.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        /**
         * Tente passar os argumentos fazendo o uso da tag
         * argument no nav_graph.
         */
        homeBinding?.btnSearchFlight?.setOnClickListener {
            val bundle = Bundle().apply {
                putString(DEPARTURE, homeBinding?.fromInput?.text.toString())
                putString(ARRIVAL, homeBinding?.toInput?.text.toString())
                putString(DATE, homeBinding?.departInput?.text.toString())
            }

            findNavController().navigate(R.id.action_navigation_home_to_navigation_search, bundle)
        }
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateInputDate()
    }


    private fun updateInputDate() {
        val dateFormat = "dd/MM/yyyy"
        val stringDateFormat = SimpleDateFormat(dateFormat, Locale.US)
        homeBinding?.departInput?.setText(
            stringDateFormat.format(
                calendar.time
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }

    companion object {
        const val ARRIVAL = "ARRIVAL"
        const val DEPARTURE = "DEPARTURE"
        const val DATE = "DATE"
    }
}
