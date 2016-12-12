package com.example.searchtext;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	//private static final Student Student = null;
	EditText txtSearch;
	ListView lv;
	ArrayList<Student> list= new ArrayList<Student>();;
	ArrayList<Student> source= new ArrayList<Student>();;
	//adapter
	MyAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //list= new ArrayList<Student>();
       // source= new ArrayList<Student>();
        
        txtSearch=(EditText) this.findViewById(R.id.editText1);
        lv=(ListView)this.findViewById(R.id.listView1);
        adapter= new MyAdapter(this,list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);

        source.add(new Student(R.drawable.img1,"Alpha, Bravo","BSIT"));
        source.add(new Student(R.drawable.img2,"Charlie, Delta","BSIT"));
        source.add(new Student(R.drawable.img3,"Echo, Foxtrot","BSIT"));
        source.add(new Student(R.drawable.img4,"Golf, Hotel","BSIT"));
        source.add(new Student(R.drawable.img5,"India, Juliet","BSIT"));
        source.add(new Student(R.drawable.img6,"Kilo, Lima","BSIT"));
        source.add(new Student(R.drawable.img7,"Mother, November","BSIT"));
        source.add(new Student(R.drawable.img8,"Oscar, Papa","BSIT"));
        source.add(new Student(R.drawable.img9,"Quebec, Rio","BSIT"));
                
        this.txtSearch.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				list.clear();

				Pattern p=Pattern.compile(arg0.toString());
				for(int i=0; i<source.size();i++){
					Matcher m=p.matcher(source.get(i).getStudentName());
					Matcher n=p.matcher(source.get(i).getCourse());
					
					if(m.find() || n.find()){
					list.add(source.get(i));
					
					
				}
				adapter.notifyDataSetChanged();
				}
				
			}});
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(list.get(arg2).getStudentName());
		
		ImageView myiv=new ImageView(this);
			myiv.setImageResource(list.get(arg2).getImage());
		TextView myname=new TextView(this);
			myname.setText(list.get(arg2).getStudentName());
		TextView mycourse=new TextView(this);
			mycourse.setText(list.get(arg2).getCourse());
		
		LinearLayout mainLayout=new LinearLayout(this);	
				mainLayout.setOrientation(LinearLayout.HORIZONTAL);
				mainLayout.addView(myiv);
				
		LinearLayout subLayout=new LinearLayout(this);
			subLayout.setOrientation(LinearLayout.VERTICAL);
			subLayout.addView(myname);
			subLayout.addView(mycourse);
			
			mainLayout.addView(subLayout);
			
			builder.setView(mainLayout);
			builder.setNeutralButton("Okey", null);
			
	AlertDialog dialog=builder.create();
		dialog.show();

	}


   
    
}

