package com.workmanagerwithroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workmanagerwithroomdb.databinding.SubitemQuoteBinding
import com.workmanagerwithroomdb.modal.quoteResponse.Result

class QuoteDataAdapter(private val quoteData: List<Result>) : RecyclerView.Adapter<QuoteDataAdapter.QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        var binding = SubitemQuoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
    holder.binding.ref = quoteData[position]
    /*holder.bind(quoteData[position])*/
    }

    override fun getItemCount(): Int {
       return quoteData.size
    }
    // view holder
    inner class QuoteViewHolder(val binding: SubitemQuoteBinding): RecyclerView.ViewHolder(binding.root) {
        /*fun bind(item:Result){

        }*/
    }
}
