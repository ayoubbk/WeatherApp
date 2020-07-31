package com.bks.weatherapp.ui.citylist

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bks.weather.models.City
import com.bks.weather.state.city.CityListStateEvent
import com.bks.weather.state.city.CityListViewState
import com.bks.weatherapp.R
import com.bks.weatherapp.ui.weatherdetail.CITY_DETAIL_SELECTED_BUNDLE_KEY
import com.bks.weatherapp.util.TopSpacingItemDecoration
import com.bks.weatherapp.util.getCityViewModelFactory
import com.bks.weatherapp.util.toArrayList
import kotlinx.android.synthetic.main.fragment_city_list.*
import java.util.*
import kotlin.collections.ArrayList


private const val TAG = "CityListFragment"
const val CITY_LIST_STATE_BUNDLE_KEY = "com.bks.weatherapp.ui.citylist.state"

class CityListFragment : Fragment(R.layout.fragment_city_list), CityListAdapter.Interaction {

    private val viewModel by viewModels<CityListViewModel> { getCityViewModelFactory() }

    private var listAdapter: CityListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.cities)
        setupFab()
        setupRecyclerView()
        subscribeObserver()
        restoreInstanceState(savedInstanceState)
    }

    private fun setupFab() {
        add_new_city_fab.setOnClickListener {
            openCreateCityDialog()
        }
    }

    private fun setupRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingDecorator)

            listAdapter = CityListAdapter(this@CityListFragment)
            adapter = listAdapter
        }
    }


    private fun subscribeObserver() {

        // Get the DataState result and set it
        viewModel.dataState.observe(viewLifecycleOwner, Observer{ dataState ->
            if(dataState != null){
                dataState.data?.let {data->
                    data.data?.let{event ->
                        event.getContentIfNotHandled()?.let{ viewState ->
                            Log.d(TAG, "CityListFragment, DataState: $viewState")
                            viewState.cityList.let {
                                viewModel.setCityListData(it)
                            }

                            viewState.newCity.let {
                                viewModel.setCityData(it)
                            }
                        }
                    }
                }
            }
        })

        // Observe the ViewState
        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState?.let {
                it.cityList?.let {cityList->
                    listAdapter?.submitList(cityList)
                    listAdapter?.notifyDataSetChanged()
                }

                // city been inserted or selected
                it.newCity?.let {city ->
                    navigateToWeatherDetailFragment(city)
                }
            }
        })
    }


    private fun navigateToWeatherDetailFragment(selectedCity: City){
        val bundle = bundleOf(CITY_DETAIL_SELECTED_BUNDLE_KEY to selectedCity.name)
        findNavController().navigate(
            R.id.action_cityListFragment_to_weatherDetailFragment,
            bundle
        )
        viewModel.setCityData(null)
    }


    override fun onItemSelected(position: Int, item: City) {
        viewModel.setCityData(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.clearList()
        viewModel.setStateEvent(CityListStateEvent.GetAllCitiesEvent)
    }

    override fun onPause() {
        super.onPause()
        saveLayoutManagerState()
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?){
        savedInstanceState?.let { inState ->
            (inState[CITY_LIST_STATE_BUNDLE_KEY] as CityListViewState?)?.let { viewState ->
                viewModel.setViewState(viewState)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val viewState = viewModel.viewState.value
        viewState?.cityList =  ArrayList()
        outState.putParcelable(
            CITY_LIST_STATE_BUNDLE_KEY,
            viewState
        )
        super.onSaveInstanceState(outState)
    }

    private fun saveLayoutManagerState(){
        recycler_view.layoutManager?.onSaveInstanceState()?.let { lmState ->
            viewModel.setLayoutManagerState(lmState)
        }
    }

    fun openCreateCityDialog() {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Enter city name")

            val input = EditText(it)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            input.layoutParams = lp
            builder.setView(input)
            builder.setPositiveButton(android.R.string.yes) { dialog, _ ->

                val newCity = City(UUID.randomUUID().toString(), input.text.toString())

                // add new city to list manually cause there is some issue when returning result after inserting City to DB
                // the event of inserting data to db is triggered and the City is inserted but there is an issue displaying it to recycler view
                val currentCityList = listAdapter?.getCurrentList()?.toArrayList()
                currentCityList?.add(newCity)
                currentCityList?.let {newList ->
                    listAdapter?.submitList(newList)
                    listAdapter?.notifyDataSetChanged()
                }

                // trigger the StateEvent
                viewModel.setStateEvent(
                    CityListStateEvent.AddCityEvent(
                        newCity.name
                    )
                )

                dialog.dismiss()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}