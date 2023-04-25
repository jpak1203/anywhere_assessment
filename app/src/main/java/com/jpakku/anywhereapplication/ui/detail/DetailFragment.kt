package com.jpakku.anywhereapplication.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jpakku.anywhereapplication.R
import com.jpakku.anywhereapplication.databinding.FragmentDetailBinding
import dagger.android.support.AndroidSupportInjection

class DetailFragment : Fragment() {

    companion object {
        const val NAME = "name"
        const val DESC = "desc"
        const val ICON = "icon"
    }

    private lateinit var binding: FragmentDetailBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        initView()
        return binding.root
    }

    private fun initView() {
        arguments?.let {
            if (!it.isEmpty) {
                val name = it.getString(NAME).toString()
                val description = it.getString(DESC).toString()
                val icon = it.getString(ICON).toString()

                updateView(name, description, icon)
            }
        }
    }

    fun updateView(name: String, description: String, icon: String?) {
        Glide.with(this)
            .load("https://duckduckgo.com/$icon")
            .placeholder(R.drawable.pedro_pascal)
            .into(binding.characterImage)

        binding.characterNameValue.text = name
        binding.characterDescriptionValue.text = description
    }
}