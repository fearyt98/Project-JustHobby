package com.aurimteam.justhobby.user.main.home.user_groups

import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.*
import kotlinx.android.synthetic.main.fragment_user_groups.*
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.RecyclerView
import android.support.design.widget.BottomNavigationView
import android.widget.*
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.user.search.search.SearchFragment

class UserGroupsFragment : Fragment(), IUserGroupsView {

    private val presenter = UserGroupsPresenter(this, UserGroupsModel())
    private val adapter = UserGroupsAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_groups, container, false)
    }

    override fun showUserCourses(userGroups: List<GroupResponse>, included: IncludedResponse?) {
        if (userGroups.isEmpty() || included == null) {
            userGroupsProgressBar.visibility = View.GONE
            userGroupsRecyclerView.visibility = View.GONE
            userGroupsClear.visibility = View.VISIBLE
        } else {
            toggleContentPB(false)
            adapter.onDataChange(userGroups, included)
        }
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (context != null)
            presenter.getUserCourses(context!!)
        userGroupsBtnBack.setOnClickListener { back() }
        userGroupsClearBtn.setOnClickListener { openSearch() }
        userGroupsRecyclerView.layoutManager = LinearLayoutManager(context)
        userGroupsRecyclerView.adapter = adapter
        setUpItemTouchHelper()
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            userGroupsProgressBar.visibility = View.VISIBLE
            userGroupsRecyclerView.visibility = View.GONE
        } else {
            userGroupsProgressBar.visibility = View.GONE
            userGroupsRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if (devMode != null && devMode) {
            val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
        toggleContentPB(false)
    }

    override fun deletedUserGroup(position: Int) {
        adapter.removeItem(position)
        if (adapter.itemCount == 0) {
            userGroupsProgressBar.visibility = View.GONE
            userGroupsRecyclerView.visibility = View.GONE
            userGroupsClear.visibility = View.VISIBLE
        }
    }

    private fun openSearch() {
        activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.selectedItemId = R.id.navigation_search
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.mainNavContainerFragment, SearchFragment())
            .commit()
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }

    private fun setUpItemTouchHelper() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val swipedPosition = viewHolder.adapterPosition
                if(context != null)
                    adapter.deleteGroup(context!!, swipedPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                /*if (viewHolder.adapterPosition == -1) return

                val itemView = viewHolder.itemView
                val layoutParams = itemView.userGroupsDeleteBtn.layoutParams
                layoutParams.width = if (-dX.toInt() >= 300) 300 else -dX.toInt()
                itemView.userGroupsDeleteBtn.layoutParams = layoutParams*/

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }
        val mItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        mItemTouchHelper.attachToRecyclerView(userGroupsRecyclerView)
    }
}
