package dataHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

import attributes.AbstractAttribute;
import boardObjectModels.Board;
import boardObjectModels.Patch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * DeSerialiser that reads a Json String and converts it into a board object
 * 
 * @author Tanaka Jimha
 *
 */

public class DeSerialiser
{
	private Board myBoard;

	public DeSerialiser(){

	}

	public Board deserialise(Path path){
			
		String json = read(path);
		//Gson gson = new Gson();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>()).registerTypeHierarchyAdapter(AbstractAttribute.class, new CustomSerialisation<AbstractAttribute>());
		//gsonBuilder.registerTypeHierarchyAdapter(IBoard.class, new CustomSerialisation<IBoard>()).registerTypeHierarchyAdapter(IBoard.class, new CustomSerialisation<IBoard>());
		//gsonBuilder.registerTypeHierarchyAdapter(IPatch.class, new CustomSerialisation<IPatch>()).registerTypeHierarchyAdapter(IPatch.class, new CustomSerialisation<IPatch>());

		
		gsonBuilder.registerTypeAdapter(Board.class, new AbstractBoardInstanceCreator());
		gsonBuilder.registerTypeAdapter(Patch.class, new PatchInstanceCreator());
		
		
		Gson gson = gsonBuilder.create();
		myBoard = gson.fromJson(json, Board.class ) ;
		 

		return myBoard;
	}

	private String read(Path path){
		try
		{
			FileInputStream fileIn = new FileInputStream(path.toString());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			String json = (String)in.readObject();
			in.close();
			fileIn.close();
			return json;
		}catch(IOException i)
		{
			//THIS WILL BE CHANGED WHEN ERROR DIALOGUES ARE CREATED
			i.printStackTrace();
			return null;
		}catch(ClassNotFoundException c)
		{
			c.printStackTrace();
			return null;
		}
		
	}

}