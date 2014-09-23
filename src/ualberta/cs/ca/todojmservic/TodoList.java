package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

public class TodoList {
	
	protected ArrayList<Todos> todolist;
	protected ArrayList<Listener> listeners;
	protected ArrayList<Todos> archived;
	
	
	public TodoList(){
		todolist = new ArrayList<Todos>();
		listeners = new ArrayList<Listener>();
	}
	
	public Collection<Todos> getTodos(){
		return todolist;

	}
	
	public void addTodo(Todos todo){
		todolist.add(todo);
		notifyeveryone();
	}
	
	private void notifyeveryone() {
		for(Listener listener: listeners){
			listener.update();
		}
		
	}

	public void removeTodo(Todos todo){
		todolist.remove(todo);
		notifyeveryone();
	}
	
	public void addListener(Listener l){
		listeners.add(l);
	}
	public void removeListener(Listener l){
		listeners.remove(l);
	}



}
