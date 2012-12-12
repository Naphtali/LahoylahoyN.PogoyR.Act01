package com.android.lahoylahoyn.pogoyr.act01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AddContact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontact);
		
		Button done = (Button)findViewById(R.id.add_button);
		
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView name = (TextView)findViewById(R.id.addname);
				TextView no = (TextView)findViewById(R.id.addnumber);
				
				String Name = String.valueOf(name.getText());
				String Num = String.valueOf(no.getText());
				
				Details newContact = new Details();
				newContact.setName(Name);
				newContact.setPhoneNumber(Num);
				
				DBHelper db = new DBHelper(getBaseContext());
				db.addContact(newContact);
				
				Intent intnt = new Intent(getBaseContext(), PhonebookActivity.class);
				startActivity(intnt);
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
