package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

public class TodoList {
	
	protected ArrayList<Todos> todolist;
	protected ArrayList<Listner> listners;
	
	
	public TodoList(){
		todolist = new ArrayList<Todos>();
		listners = new ArrayList<Listner>();
	}
	
	public Collection<Todos> getTodos(){
		return todolist;

	}
	
	public void addTodo(Todos todo){
		todolist.add(todo);
		notifyeveryone();
	}
	
	private void notifyeveryone() {
		for(Listner listner: listners){
			listner.update();
		}
		
	}

	public void removeTodo(Todos todo){
		todolist.remove(todo);
	}
	
	public void addListner(Listner l){
		listners.add(l);
	}
	public void removeListner(Listner l){
		listners.remove(l);
	}

}
