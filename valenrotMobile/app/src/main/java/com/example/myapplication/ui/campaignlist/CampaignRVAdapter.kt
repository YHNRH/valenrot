package com.example.myapplication.ui.campaignlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.DeleteEntityInterface
import com.example.myapplication.viewmodel.CharacterViewModel

class CampaignRVAdapter(
    val context: Context,
    private val characterViewModel : CharacterViewModel,
    private val activity : Fragment,
    private val campaignClickDeleteInterface: DeleteEntityInterface<Campaign>,
    private val campaignClickInterface: CampaignClickInterface
    ) :
        RecyclerView.Adapter<CampaignRVAdapter.ViewHolder>(){

        private val allCampaigns = ArrayList<Campaign>()
        private lateinit var characterRV: RecyclerView


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val noteTV = itemView.findViewById<TextView>(R.id.name)
            val deleteIV = itemView.findViewById<TextView>(R.id.delete)
            val expandBtn = itemView.findViewById<ImageView>(R.id.expand)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.campaign_list_item,
                parent, false
            )

            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            characterRV = holder.itemView.findViewById(R.id.list)
            characterRV.layoutManager = LinearLayoutManager(context)
            val characterRVAdapter = CharacterRVAdapter(context//,this
                     )

            characterRV.adapter = characterRVAdapter
            characterViewModel.allCharacters.observe(activity) { list ->
                list?.let {
                    characterRVAdapter.updateList(it, allCampaigns[position])
                }
            }

            holder.noteTV.text = allCampaigns[position].name
            holder.deleteIV.setOnClickListener {
                campaignClickDeleteInterface.onDeleteClick(allCampaigns[position])
            }
            holder.itemView.setOnClickListener {
                campaignClickInterface.onCampaignClick(allCampaigns[position])
            }


            characterRV.visibility = if (allCampaigns[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

            if (allCampaigns[position].isExpanded){
                val bitmap = BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.expand_icon
                )
                val cx = bitmap.width / 2f
                val cy = bitmap.height / 2f
                val flippedBitmap = bitmap.flip(1f, -1f, cx, cy)
                holder.expandBtn.setImageBitmap(flippedBitmap)
            } else {
                holder.expandBtn.setImageResource(R.drawable.expand_icon)
            }
            holder.expandBtn.setOnClickListener {
                allCampaigns[position].isExpanded = !allCampaigns[position].isExpanded
                notifyDataSetChanged()
            }

        }

        override fun getItemCount(): Int {
            return allCampaigns.size
        }

        fun updateList(newList: List<Campaign>) {
            allCampaigns.clear()
            allCampaigns.addAll(newList)
            notifyDataSetChanged()
        }


    private fun Bitmap.flip(x: Float, y: Float, cx: Float, cy: Float): Bitmap {
        val matrix = Matrix().apply { postScale(x, y, cx, cy) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
}

    interface CampaignClickInterface {
        fun onCampaignClick(campaign: Campaign)
    }
