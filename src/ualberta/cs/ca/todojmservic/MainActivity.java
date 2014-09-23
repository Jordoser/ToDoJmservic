package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Get ListObjects
		ListView listview =  (ListView) findViewById(R.id.ToDoListView);
		Collection<Todos> todos = TodoSingle.getTodoList().getTodos();
		final ArrayList<Todos> todoslist = new ArrayList<Todos>(todos);
		final ArrayAdapter<Todos> todosAdapter = new ArrayAdapter<Todos>(this, android.R.layout.simple_list_item_1,todoslist);
		listview.setAdapter(todosAdapter);
		
		//Observer Pattern
		TodoSingle.getTodoList().addListener(new Listener(){

			@Override
			public void update() {
				todoslist.clear();
				Collection<Todos> todos = TodoSingle.getTodoList().getTodos();
				todoslist.addAll(todos);
				todosAdapter.notifyDataSetChanged();
			}
			
			
		});
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
				ad.setMessage("Delete "+ todoslist.get(position).toString()+"?");
				ad.setCancelable(true);
				final int finalPosition = position;
				ad.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Todos todo = todoslist.get(finalPosition);
						TodoSingle.getTodoList().removeTodo(todo);
						
					}
					
				});
				//Crashing for some reason :(
				ad.setNeutralButton("Archive", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Todos todo = todoslist.get(finalPosition);
						TodoSingle.getTodoList().removeTodo(todo);
						ArchiveSingle.getArchive().addTodo(todo);
					
						
					}
					
				});
				ad.setNegativeButton("Cancel", new OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void archive(MenuItem menu) {
		Toast.makeText(this, "Archive", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
		startActivity(intent);
	}

	public void summarize(MenuItem menu) {
		Toast.makeText(this, "summarize", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, SummarizeActivity.class);
		startActivity(intent);
	}

	public void addTodo(View v){
		Toast.makeText(this, "Add Todo",Toast.LENGTH_SHORT).show();
		TodoSingle ts = new TodoSingle();
		EditText newTodoText = (EditText) findViewById(R.id.AddTodoEditText);
		ts.addTodo(new Todos(newTodoText.getText().toString()));
	
		
	}
}
