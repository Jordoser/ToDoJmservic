package ualberta.cs.ca.todojmservic;

import java.io.IOException;

public class ArchiveSingle {
	
	private static ArchiveList archive = null;
	
	static public ArchiveList getArchive(){
		if (archive == null){
			try{
				archive = ArcIO.getIO().loadarc();
				archive.addListener(new Listener(){
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
		return archive;
	}
	protected static void savedTodo() {
		try{
			ArcIO.getIO().savearc(getArchive());
		}catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException("could not deserialize");
		}
		
	}
	public void addArchive(Todos todos) {
		getArchive().addTodo(todos);
	}
}
