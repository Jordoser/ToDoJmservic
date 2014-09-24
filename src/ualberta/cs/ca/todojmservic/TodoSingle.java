package ualberta.cs.ca.todojmservic;

import java.io.IOException;

public class TodoSingle {
	private static TodoList todolist = null;
	
	static public TodoList getTodoList(){
		if (todolist == null){
			try{
				todolist = TodoListIO.getIO().loadTodo();
				todolist.addListener(new Listener(){
					@Override
					public void update() {
						savedTodo();
					}
				});
			}catch (ClassNotFoundException e){
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize StudentList from StudentListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize StudentList from StudentListManager");
			}
		}
		return todolist;
	}
	
	static public void savedTodo(){
		try{
			TodoListIO.getIO().saveTodo(getTodoList());
		}catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException("could not deserialize");
		}
	}
	
	public void addTodo(Todos todos) {
		getTodoList().addTodo(todos);
		
	}

}
