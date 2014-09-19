package ualberta.cs.ca.todojmservic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		TodoSingle todoinst = new TodoSingle();
		EditText todotext = (EditText) findViewById(R.id.AddTodoEditText);
		todoinst.addTodo(new Todos(todotext.getText().toString()));
		
	}
}
