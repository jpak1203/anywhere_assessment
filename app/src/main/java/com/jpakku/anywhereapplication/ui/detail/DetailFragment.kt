package com.jpakku.anywhereapplication.ui.detail

import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jpakku.anywhereapplication.R
import com.jpakku.anywhereapplication.data.CharacterItem
import com.jpakku.anywhereapplication.databinding.FragmentDetailBinding
import dagger.android.support.AndroidSupportInjection

class DetailFragment : Fragment() {

    companion object {
        const val CHARACTER = "character"
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
            val characterItem = if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(CHARACTER, CharacterItem::class.java)
            } else {
                it.getParcelable(CHARACTER)
            }

            characterItem?.let { item ->
                updateView(item)
            }

        }
    }

    fun updateView(characterItem: CharacterItem) {
        Glide.with(this)
            .load("https://duckduckgo.com/${characterItem.icon}")
            .placeholder(R.drawable.pedro_pascal)
            .into(binding.characterImage)

        binding.characterNameValue.text = characterItem.name
        binding.characterDescriptionValue.text = characterItem.description
    }
}