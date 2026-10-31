package com.example.fragmentexample.feature.slide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentexample.R
import com.example.fragmentexample.databinding.ItemSlideCardBinding

/** ページ領域いっぱいに、サンプルカードを1枚ずつ表示します。 */
class SlideCardAdapter : RecyclerView.Adapter<SlideCardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemSlideCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false,
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(position + 1, itemCount)
    }

    override fun getItemCount(): Int = 5

    class CardViewHolder(private val binding: ItemSlideCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Int, total: Int) {
            val context = binding.root.context
            binding.cardTitle.text = context.getString(R.string.slide_card_title, number)
            binding.cardPosition.text = context.getString(R.string.slide_card_position, number, total)
        }
    }
}
