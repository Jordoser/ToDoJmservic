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
import android.widget.TextView;
import android.widget.Toast;

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
		
		//Observer Pattern
		TodoSingle.getEmails().addListener(new Listener(){

			@Override
			public void update() {
				emaillist.clear();
				Collection<Todos> todos = TodoSingle.getEmails().getTodos();
				emaillist.addAll(todos);
				todosAdapter.notifyDataSetChanged();
			}
			
			
		});
		
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
	
	public void addAll(View v){
		Collection<Todos> todo = TodoSingle.getTodoList().getTodos();
		Collection<Todos> arcs = ArchiveSingle.getArchive().getTodos();
		ArrayList<Todos> all = new ArrayList<Todos>();
		all.addAll(arcs);
		all.addAll(todo);
		TodoSingle.getEmails().set(all);
		
	}
	
	public void sendList(View v){
		String email = "";
		ListView listview =  (ListView) findViewById(R.id.EmailList);
		for(int to=0; to<listview.getChildCount();to++){
			if (to < listview.getChildCount() -1){
				TextView text = (TextView) listview.getChildAt(to);
				email += text.getText().toString()+"\n";
			}else{
				TextView text = (TextView) listview.getChildAt(to);
				email += text.getText().toString();
			}
		
		}
		
		Intent send = new Intent(android.content.Intent.ACTION_SEND);
		send.setType("plain/text");
		send.putExtra(Intent.EXTRA_TEXT, email);
		startActivityForResult(send, 1);
		
		
		
	}


}
