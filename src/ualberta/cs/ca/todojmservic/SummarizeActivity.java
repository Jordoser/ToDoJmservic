package ualberta.cs.ca.todojmservic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SummarizeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summarize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summarize, menu);
		return true;
	}

}
