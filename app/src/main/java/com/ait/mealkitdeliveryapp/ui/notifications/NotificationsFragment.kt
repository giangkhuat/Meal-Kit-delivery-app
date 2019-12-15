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

        root.tvQ1.setOnClickListener{
            if (root.tvAnswer1.visibility == View.GONE){
                root.tvAnswer1.visibility = View.VISIBLE
                root.tvAnswer1.animation = animation
            } else {
                root.tvAnswer1.visibility = View.GONE
            }

        }


        root.tvQ2.setOnClickListener{
            if (root.tvAnswer2.visibility == View.GONE){
                root.tvAnswer2.visibility = View.VISIBLE
                root.tvAnswer2.animation = animation
            } else {
                root.tvAnswer2.visibility = View.GONE
            }

        }

        root.tvQ3.setOnClickListener{
            if (root.tvAnswer3.visibility == View.GONE){
                root.tvAnswer3.visibility = View.VISIBLE
                root.tvAnswer3.animation = animation
            } else {
                root.tvAnswer3.visibility = View.GONE
            }

        }

        return root
    }
}