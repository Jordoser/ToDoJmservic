package ualberta.cs.ca.todojmservic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class TodoListIO {
	Context context;
	static final String tdfile = "TodoList";
	static final String tdkey = "todolist";
	static private TodoListIO todolistio = null;
	
	public TodoListIO(Context context) {
		this.context = context;
	}

	public static void init(Context context){
		if(todolistio== null){
			if (context== null){
				throw new RuntimeException("Missing Context");
			}
			todolistio = new TodoListIO(context);
		}
	}
	
	public TodoList loadTodo() throws StreamCorruptedException, IOException, ClassNotFoundException{
		SharedPreferences settings = context.getSharedPreferences(tdfile, Context.MODE_PRIVATE);
		String data = settings.getString(tdkey, "");
		if(data.equals("")){
			return new TodoList();
		}else{
			return todoFromString(data);
		}
		
	}
	
	public void saveTodo(TodoList tdl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(tdfile, Context.MODE_PRIVATE);
		Editor edit = settings.edit();
		edit.putString(tdkey, todoToString(tdl));
		edit.commit();
		
	}
	
	private String todoToString(TodoList tdl) throws IOException {
		ByteArrayOutputStream bytout = new ByteArrayOutputStream();
		ObjectOutputStream objout = new ObjectOutputStream(bytout);
		objout.writeObject(tdl);
		objout.close();
		byte bytes[] = bytout.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
		
		
	}

	private TodoList todoFromString(String data) throws StreamCorruptedException, IOException, ClassNotFoundException {
		ByteArrayInputStream bytin = new ByteArrayInputStream(Base64.decode(data, Base64.DEFAULT));
		ObjectInputStream objIn = new ObjectInputStream(bytin);
		return (TodoList) objIn.readObject();
	}

	public static TodoListIO getIO() {
		if (todolistio == null){
			throw new RuntimeException("NotInit");
		}
		return todolistio;
		}
	}



