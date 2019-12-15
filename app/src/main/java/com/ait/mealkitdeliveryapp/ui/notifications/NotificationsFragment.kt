package com.ait.mealkitdeliveryapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ait.mealkitdeliveryapp.R
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import org.w3c.dom.Text

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        var animation = AnimationUtils.loadAnimation(activity, R.anim.dropdown)
        root.tvTestQ.setOnClickListener{
            if (root.tvTestA.visibility == View.GONE){
                root.tvTestA.visibility = View.VISIBLE
                root.tvTestA.animation = animation
            } else {
                root.tvTestA.visibility = View.GONE
            }

        }
        return root
    }
}