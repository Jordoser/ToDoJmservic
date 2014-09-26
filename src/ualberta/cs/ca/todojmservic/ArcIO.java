//Reads and writes the archive from and to file

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

public class ArcIO {
	Context context;
	static final String arfile = "ArchiveList";
	static final String arkey = "Archive Key";
	static private ArcIO arcio = null;
	
	
	
	
	public ArcIO(Context context){
		this.context = context;
	}
	
	public static ArcIO getIO() {
		if (arcio == null){
			throw new RuntimeException("NotInit,Arc");
		}
		return arcio;
		}


	
	public static void init(Context context){
		if(arcio== null){
			if (context== null){
				throw new RuntimeException("Missing Context");
			}
			arcio = new ArcIO(context);
		}
	}
	
	
	public ArchiveList loadarc() throws StreamCorruptedException, IOException, ClassNotFoundException{
		SharedPreferences setup = context.getSharedPreferences(arfile, Context.MODE_PRIVATE);
		String adata = setup.getString(arkey, "");		
		if(adata.equals("")){
			return new ArchiveList();
		}else{
			return arcFromString(adata);
		}
	}
		
		public void savearc(ArchiveList tdl) throws IOException{
			SharedPreferences setup = context.getSharedPreferences(arfile, Context.MODE_PRIVATE);
			Editor edit1 = setup.edit();
			edit1.putString(arkey, arcToString(tdl));
			edit1.commit();
		
	}

	private String arcToString(TodoList tdl) throws IOException {
		ByteArrayOutputStream bytout = new ByteArrayOutputStream();
		ObjectOutputStream objout = new ObjectOutputStream(bytout);
		objout.writeObject(tdl);
		objout.close();
		byte bytes[] = bytout.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
		}

	private ArchiveList arcFromString(String adata) throws StreamCorruptedException, IOException, ClassNotFoundException {
		ByteArrayInputStream bytin = new ByteArrayInputStream(Base64.decode(adata, Base64.DEFAULT));
		ObjectInputStream objIn = new ObjectInputStream(bytin);
		return (ArchiveList) objIn.readObject();
	}
}
	
	
