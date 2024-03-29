package com.android.lahoylahoyn.pogoyr.act01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		final Bundle extras = getIntent().getExtras();
		TextView name = (TextView)findViewById(R.id.contact_name);
		TextView num = (TextView)findViewById(R.id.contact_num);
		Button edit = (Button)findViewById(R.id.edit_button);
		Button delete = (Button)findViewById(R.id.delete_button);
		
		name.setText(extras.getString("contactName"));
		num.setText(extras.getString("contactNum"));
		
		edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intnt = new Intent(getBaseContext(), EditDetails.class);
				intnt.putExtras(extras);
				startActivity(intnt);
			}
		});
		
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DBHelper db = new DBHelper(getBaseContext());
				db.deleteContact(extras.getString("contactID"));
				Intent i = new Intent(getBaseContext(),PhonebookActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_phonebook, menu);
		return true;
	}

}
