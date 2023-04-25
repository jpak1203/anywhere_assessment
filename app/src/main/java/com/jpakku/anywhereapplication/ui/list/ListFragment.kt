package com.jpakku.anywhereapplication.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.jpakku.anywhereapplication.data.CharacterItem
import com.jpakku.anywhereapplication.databinding.FragmentListBinding
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var binding: FragmentListBinding
    private lateinit var listener: OnItemSelectedListener


    private val characterListAdapter by lazy { CharacterListAdapter { it.onClick() } }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[ListFragmentViewModel::class.java]

        initView()
        subscribe()
        setSearchView()
        return binding.root
    }

    private fun initView() {
        binding.listRecyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        binding.listRecyclerView.adapter = characterListAdapter
    }

    private fun subscribe() {
        viewModel.getAllCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { list ->
                characterListAdapter.submitList(list)
            }
    }

    interface OnItemSelectedListener {
        fun onCharacterSelected(name: String, description: String, icon: String)
    }

    private fun CharacterItem.onClick() {
        updateDetail(name, description, icon)
    }

    private fun updateDetail(name: String, description: String, icon: String) {
        listener.onCharacterSelected(name, description, icon)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<CharacterItem>()

        viewModel.getAllCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { originalList ->
                if (!query.isNullOrEmpty()) {
                    list.addAll(originalList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(query.toString().lowercase(Locale.getDefault())) ||
                                it.description.lowercase(Locale.getDefault()).contains(query.toString().lowercase(
                                    Locale.getDefault()))
                    })
                } else {
                    list.addAll(originalList)
                }

                characterListAdapter.submitList(list)
            }
    }

    private fun setSearchView() {
        binding.listSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

        })
    }
}