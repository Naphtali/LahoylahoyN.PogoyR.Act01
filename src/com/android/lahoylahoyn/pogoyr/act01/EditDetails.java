package com.android.lahoylahoyn.pogoyr.act01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editcontact);
		
		final Bundle extras = getIntent().getExtras();
		Button save = (Button)findViewById(R.id.save_button);
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText name = (EditText)findViewById(R.id.editname);
				EditText no = (EditText)findViewById(R.id.editnumber);
				
				String Name = String.valueOf(name.getText());
				String No = String.valueOf(no.getText());
				
				Details newContact = new Details();
				
				if (Name.isEmpty())
					newContact.setName(extras.getString("contactName"));
				else newContact.setName(Name);
				
				if (No.isEmpty())
					newContact.setPhoneNumber(extras.getString("contactNum"));
				else newContact.setPhoneNumber(No);
				
				DBHelper db = new DBHelper(getBaseContext());
				int oldID = Integer.parseInt(extras.getString("contactID"));
				newContact.setID(oldID);
				db.updateContact(newContact);
				
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
