package com.android.lahoylahoyn.pogoyr.act01;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PhonebookActivity extends ListActivity {

	private DBHelper db;
	private ListAdapter adapter;
	private static int flag=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DBHelper(this);
		
		Cursor cursor = getContacts();
		
		if (flag == 0) {
			flag=1;
			while (cursor.moveToNext()) {
		        String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
		        db.addContact(new Details(displayName, "00000"));
	        }
		}
		
		
		List<Details> contacts = db.getAllContacts();
		List<String> names = new ArrayList<String>();
		
		for(int i=0; i < contacts.size(); i++) {
			names.add(contacts.get(i).getName());
		}
		String[] objs = names.toArray(new String[]{});
		adapter = new ListAdapter(this, contacts, objs);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_phonebook, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case (R.id.add_button):
			Intent intnt = new Intent(getBaseContext(), AddContact.class);
			startActivity(intnt);
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intnt = new Intent(getBaseContext(),ContactDetails.class);
		TextView conname = (TextView) v.findViewById(R.id.name);
		TextView connum = (TextView) v.findViewById(R.id.number);
		TextView conID = (TextView) v.findViewById(R.id.ID);
		intnt.putExtra("contactName",conname.getText());
		intnt.putExtra("contactNum", connum.getText());
		intnt.putExtra("contactID", conID.getText());
		startActivity(intnt);
	}
	
	@SuppressWarnings("deprecation")
	private Cursor getContacts() {
        // Run query
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] { ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME };
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
            + ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
            + " COLLATE LOCALIZED ASC";
        
        return managedQuery(uri, projection, selection, selectionArgs,
            sortOrder);
        
	}
		
	public class ListAdapter extends ArrayAdapter<String> {
    	private final Context context;
    	List<Details> contacts;
    	
		public ListAdapter(Context context, List<Details> contacts, String[] obj) {
			super(context, R.layout.activity_phonebook, obj);
			this.context = context;
			this.contacts = contacts;			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
			
			View retrow = convertView;
			if (retrow==null)
				retrow = (View)inflater.inflate(R.layout.activity_phonebook, parent, false);
			
			String number = String.valueOf(contacts.get(position).getID());
			
			TextView name = (TextView)retrow.findViewById(R.id.name);
			TextView num = (TextView)retrow.findViewById(R.id.number);
			TextView contID = (TextView)retrow.findViewById(R.id.ID);
			
			name.setText(contacts.get(position).getName());
			num.setText(contacts.get(position).getPhoneNumber());
			contID.setText(number);
									
			return retrow;
		}
	}
}

