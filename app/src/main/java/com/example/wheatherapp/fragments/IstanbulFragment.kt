package com.example.wheatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wheatherapp.R

class IstanbulFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment'ın layout dosyasını şişir
        return inflater.inflate(R.layout.fragment_istanbul, container, false)
    }
}