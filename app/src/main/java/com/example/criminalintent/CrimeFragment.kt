package com.example.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment

class CrimeFragment: Fragment() {
    lateinit var crime:Crime;
    lateinit var textField: TextView
    lateinit var dateButton: Button;
    lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime=Crime();
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        val view= inflater.inflate(R.layout.fragment_crime,container,false)

        textField=view.findViewById(R.id.crime_title)

        dateButton=view.findViewById(R.id.crime_date);
        solvedCheckBox=view.findViewById(R.id.cime_solved);

        solvedCheckBox.apply {
            setOnClickListener{
                crime.isSolved=isChecked

            }

        }

        dateButton.apply{
            text=crime.date.toString();
            crime.isSolved=false;


        }


        return view;

    }

    override fun onStart() {
        super.onStart()


        val titleWatcher=object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                crime.title=p0.toString();


            }

        }

        textField.addTextChangedListener(titleWatcher);





    }
}

