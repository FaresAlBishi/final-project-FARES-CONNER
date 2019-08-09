package com.example.finalproject.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.finalproject.R
import com.example.finalproject.adapters.EmployeeRecyclerViewAdapter
import com.example.finalproject.models.Employee
import com.example.finalproject.network.Endpoints
import com.example.finalproject.network.RetroFitInstance

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_id_content.*
import kotlinx.android.synthetic.main.fragment_id_end.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Marshall Ladd
 */
class id_content : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_content, container, false)
    }

    // 06
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val adapter = EmployeeRecyclerViewAdapter { employee ->
            // 08 Create the Bundle to pass the information to the next Fragment
            val bundle = Bundle()
            bundle.putInt(getString(R.string.EMP_ID), employee.id)
            bundle.putString(getString(R.string.EMP_SALARY), employee.salary)
            bundle.putInt(getString(R.string.EMP_AGE), employee.age)
            bundle.putString(getString(R.string.EMP_NAME), employee.employee_name)
            bundle.putString(getString(R.string.EMP_JOB),employee.job)
            bundle.putString(
                getString(R.string.EMP_EMAIL), employee.email
            )

            // 08 pass bundle to the Fragment, through Navigation
            // 06

            findNavController().navigate(
                R.id.action_id_content_to_id_end,
                bundle
            )     // bundle is added in step 08.  Remove for step 06
        }

        /*
        06 Link our adapter to the RecyclerView

        recyclerView is the id of the RecyclerView in our XML file.  We get access to it
        using Kotlin synthetics.
         */
        recyclerView.apply {
            this.adapter = adapter
            // 06 A Fragment's Context is nullable.  This checks if it is or not.
            context?.let {
                /*
                06 A RecyclerView requires a LayoutManager. A LinearLayoutManager gives
                us vertical scrolling rows. There are LayoutManagers for creating grids and
                going horizontal as well. All require a Context Object, represented by 'it'.
                 */
                this.layoutManager = LinearLayoutManager(it)
            }
        }

        // 06_2 Uncomment the next line during this step for testing the RecyclerView
        // displayTestData(adapter)

        // 07 Comment out displayTestData() above.
        // 07 Performs our network request and displays the data.
        fetchDataFromServer(adapter)
    }

    /**
     * 07
     */
    private fun fetchDataFromServer(adapter: EmployeeRecyclerViewAdapter) {
        // Gets our endpoints as defined in the Endpoints interface
        val apiCalls = RetroFitInstance.retrofit
        // Gets the specific call we want
        val request = apiCalls.create(Endpoints::class.java).getEmployeeList()
        /* This line is where the actual request is triggered. It requires a Callback.
        A Callback is a way of telling the app what to do when it gets a response
        back from the API.

        NOTE: Make sure you import the RetroFit2 Callback and that the Callback type
        inside the <> matches what is inside the <> in the declaration of the function
        in the Endpoints interface.
        */
        request.enqueue(object : Callback<List<Employee>> {
            // Tell the app what to do if the network call fails for any reason.
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                // Logcat Warn
                Log.w(javaClass.name, "getEmployeeList() failed. Error: ${t.message}")
                // Show pop up if Fragment is still in view
                constraintLayout?.let {
                    Snackbar.make(it, "Network request failed.", Snackbar.LENGTH_LONG).show()
                }
            }

            // Tell the app what to do if the network call responds.  This does not mean that it
            // got your data yet.  A 404 from the API is a response.
            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                Log.d(javaClass.name,"responses.size = ${response.body()?.size ?: "0"}")
                // Get response code
                when (response.code()) {
                    // 200 equals a successful GET request that will contain the data requested
                    200 -> {
                        // data requested may have succeeded, but there may have been 0 results,
                        // so we check for null first
                        response.body()?.let {
                            // if not null, send the data to the ListAdapter so that it can be
                            // shown in the RecyclerView
                            adapter.submitList(it)
                        }
                    }
                    // If response code is anything but 200, show an error and the error code
                    else -> {
                        constraintLayout?.let {
                            Snackbar.make(it, "Something went wrong. CODE: ${response.code()}", Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        })
    }


}

