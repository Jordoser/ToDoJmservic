package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		ListView listview =  (ListView) findViewById(R.id.EmailList);
		Collection<Todos> emails = TodoSingle.getEmails().getTodos();
		final ArrayList<Todos> emaillist = new ArrayList<Todos>(emails);
		final ArrayAdapter<Todos> todosAdapter = new ArrayAdapter<Todos>(this, android.R.layout.simple_list_item_1,emaillist);	
		listview.setAdapter(todosAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}
	
	public void clear(View v) {
		//Needs to be notified
		//Toast.makeText(this, "Archive", Toast.LENGTH_SHORT).show();
		TodoSingle.getEmails().clear();
	}


}
