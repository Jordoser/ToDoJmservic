package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ArchiveActivity extends Activity {
	public TodoList emails;


	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archive);
		ArcIO.init(this.getApplicationContext());
		emails = new TodoList();
		//Get ListObjects
		ListView listview =  (ListView) findViewById(R.id.ArchiveListView);
		Collection<Todos> todos = ArchiveSingle.getArchive().getTodos();
		final ArrayList<Todos> archivelist = new ArrayList<Todos>(todos);
		final ArrayAdapter<Todos> archiveAdapter = new ArrayAdapter<Todos>(this, android.R.layout.simple_list_item_1,archivelist);
		listview.setAdapter(archiveAdapter);
		
		ArchiveSingle.getArchive().addListener(new Listener(){

			@Override
			public void update() {
				archivelist.clear();
				Collection<Todos> todos = ArchiveSingle.getArchive().getTodos();
				archivelist.addAll(todos);
				archiveAdapter.notifyDataSetChanged();
			}
			
			
		});
		
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				final int finalPosition = position;
				Todos todo = archivelist.get(finalPosition);
				ArchiveSingle.getArchive().check(todo);
				
			}
			
			
		});
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder ad = new AlertDialog.Builder(ArchiveActivity.this);
				ad.setMessage("Delete "+ archivelist.get(position).toString()+"?");
				ad.setCancelable(true);
				final int finalPosition = position;
				ad.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Todos todo = archivelist.get(finalPosition);
						TodoSingle.getTodoList().removeTodo(todo);
						
					}
					
				});
				//Crashing for some reason :(
				ad.setNeutralButton("UnArchive", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Todos todo = archivelist.get(finalPosition);
						TodoSingle.getTodoList().addTodo(todo);
						ArchiveSingle.getArchive().removeTodo(todo);
					
						
					}
					
				});
				ad.setNegativeButton("AddToEmail", new OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Todos todo = archivelist.get(finalPosition);
						emails.addTodo(todo);
						TodoSingle.getEmails().addTodo(todo);
					}
					});
					
				ad.show();
				return false;
			}
			
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}

}