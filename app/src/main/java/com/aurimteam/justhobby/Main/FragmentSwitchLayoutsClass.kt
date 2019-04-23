package com.aurimteam.justhobby.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.aurimteam.justhobby.Main.HomeActivity.HomeAdapter


class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.activity_home_timeline, null)

        /*// Replace 'android.R.id.list' with the 'id' of your RecyclerView
        val mRecyclerView = view.findViewById(R.id.eventsRecyclerView) as RecyclerView
        val mLayoutManager = LinearLayoutManager(this.activity)
        mRecyclerView.layoutManager = mLayoutManager

        val mAdapter = HomeAdapter()
        mRecyclerView.adapter = mAdapter*/
        return view
    }
}

class RecommendedPopular : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home_timeline, null)
    }
}
