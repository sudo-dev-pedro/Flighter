package br.com.phro.flighter.ui.boarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.phro.flighter.R

class BoardingPassDetailFragment : Fragment() {

    private lateinit var boardingPassDetailviewModel: BoardingPassDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boarding_pass_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        boardingPassDetailviewModel = ViewModelProvider(this).get(BoardingPassDetailViewModel::class.java)
    }

}