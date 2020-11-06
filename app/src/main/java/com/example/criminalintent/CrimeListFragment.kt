package com.example.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.DateFormat

class CrimeListFragment: Fragment() {

    lateinit var crimeRecyclerView: RecyclerView;
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view=inflater.inflate(R.layout.crime_list_fragment,container,false);
        crimeRecyclerView=view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.layoutManager= LinearLayoutManager(context)

        val crimes=crimeListViewModel.crimes;
        val adapter=CrimeAdapter(crimes);
        crimeRecyclerView.adapter=adapter;


        return view;
        // return super.onCreateView(inflater, container, savedInstanceState)
    }
    companion object{
        fun newInstance():CrimeListFragment{
            return CrimeListFragment()
        }
    }



    private inner class CrimeViewHolder(view: View):RecyclerView.ViewHolder(view) , View.OnClickListener {
        val titleTextView: TextView =itemView.findViewById(R.id.crime_title);
        val dateTextview: TextView =itemView.findViewById(R.id.crime_date)
        private lateinit var crime: Crime

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextview.text = DateFormat.getDateInstance(DateFormat.FULL).format(this.crime.date).toString()
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private inner class CrimeViewHolderTwo(view: View):RecyclerView.ViewHolder(view) , View.OnClickListener {
        val titleTextViewTwo: TextView =itemView.findViewById(R.id.crime_title_two);
        val dateTextViewTwo: TextView =itemView.findViewById(R.id.crime_date_two)
        private lateinit var crimetwo: Crime

        init {
            itemView.setOnClickListener(this)
        }
        fun bindTwo(crime: Crime) {
            this.crimetwo = crime
            titleTextViewTwo.text = this.crimetwo.title
            dateTextViewTwo.text =DateFormat.getDateInstance(DateFormat.FULL).format(this.crimetwo.date).toString()
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${crimetwo.title} pressed!", Toast.LENGTH_SHORT)
                .show()
        }

    }




    private inner class CrimeAdapter(var crimes:List<Crime>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val LAYOUT_ONE = 1
        private val LAYOUT_TWO = 2

        override fun getItemViewType(position: Int): Int {
            if (crimes[position].isPoliceRequires == false)
                return LAYOUT_ONE;
            else
                return LAYOUT_TWO;
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view: View? = null
            var viewHolder: ViewHolder? = null
            if (viewType == LAYOUT_ONE) {
                view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
                viewHolder = CrimeViewHolder(view);
            } else {
                view = layoutInflater.inflate(R.layout.list_item_crime_two, parent, false)
                viewHolder = CrimeViewHolderTwo(view);
            }


            return viewHolder!!
        }

        override fun getItemCount(): Int {
            return crimes.size;
        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val crime = crimes[position]
            if (holder.getItemViewType() == LAYOUT_ONE && holder is CrimeViewHolder) {
                holder.bind(crime)
            } else {
                if (holder.getItemViewType() == LAYOUT_TWO && holder is CrimeViewHolderTwo){
                    holder.bindTwo(crime)
                }
            }

        }


    }}