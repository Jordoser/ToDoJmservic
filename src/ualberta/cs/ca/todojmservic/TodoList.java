package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

import android.R.string;

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
	
	public void check(Todos todotask) {
		if (todotask.toString().toLowerCase().contains("\u2713")){
			int index1 = todolist.indexOf(todotask);
			todolist.remove(todotask);
			Todos todotask2 = new Todos (todotask.notdone());
			todolist.add(index1,todotask2);
			notifyeveryone();
		}
		else{
			int index1 = todolist.indexOf(todotask);
			todolist.remove(todotask);
			Todos todotask2 = new Todos (todotask.done());
			todolist.add(index1,todotask2);
			notifyeveryone();
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
