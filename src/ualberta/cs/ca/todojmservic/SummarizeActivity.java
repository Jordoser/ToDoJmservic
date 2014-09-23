package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class SummarizeActivity extends Activity {
	Collection<Todos> todos = TodoSingle.getTodoList().getTodos();
	final ArrayList<Todos> todoslist = new ArrayList<Todos>(todos);
	
	Collection<Todos> archs = ArchiveSingle.getArchive().getTodos();
	final ArrayList<Todos> archivelist = new ArrayList<Todos>(archs);

	//CheckedActiveText
	//UnCheckedActiveText
	//CheckedArchivedText
	//UncheckedArchivedText
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summarize);
		
		
		//Setting all values
		TextView chat = (TextView) findViewById(R.id.CheckedActiveText);
		chat.setText(TodoSingle.getTodoList().Count());
		
		TextView unat = (TextView) findViewById(R.id.UncheckedActiveText);
		unat.setText(TodoSingle.getTodoList().UnCount());
		
		TextView charc = (TextView) findViewById(R.id.CheckedArchivedText);
		charc.setText(ArchiveSingle.getArchive().Count());
		
		TextView unarc = (TextView) findViewById(R.id.UncheckedArchivedText);
		unarc.setText(ArchiveSingle.getArchive().UnCount());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summarize, menu);
		return true;
	}

}
