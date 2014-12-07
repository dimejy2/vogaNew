package dataHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import attributes.IAttribute;
import boardObjectModels.Board;
import boardObjectModels.Patch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Serialiser that writes a gameboard into a JSON String using GSON
 * 
 * @author Tanaka Jimha
 *
 */

public class Serialiser {
	
	public Serialiser(){

	}
	private Board myBoard;


	public String serialise(Board board, Path path){
		File file = new File(path.toString());
		myBoard = board;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeHierarchyAdapter(IAttribute.class, new CustomSerialisation<IAttribute>()).registerTypeHierarchyAdapter(IAttribute.class, new CustomSerialisation<IAttribute>());
		//gsonBuilder.registerTypeHierarchyAdapter(IBoard.class, new CustomSerialisation<IBoard>()).registerTypeHierarchyAdapter(IBoard.class, new CustomSerialisation<IBoard>());
		//gsonBuilder.registerTypeHierarchyAdapter(IPatch.class, new CustomSerialisation<IPatch>()).registerTypeHierarchyAdapter(IPatch.class, new CustomSerialisation<IPatch>());

		gsonBuilder.registerTypeAdapter(Board.class, new AbstractBoardInstanceCreator());
		gsonBuilder.registerTypeAdapter(Patch.class, new PatchInstanceCreator());
		Gson gson = gsonBuilder.create();
		
		String json = gson.toJson(board); 
		write(file, json);
		return json;

	}

	public String prettySerialise(Board aBoard){
		myBoard = aBoard;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(aBoard); 
		return json;
	}
	
	private void write(File file, String json){
		try
		{
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(json);
			out.close();
			fileOut.close();

		}catch(IOException i)
		{
			//This will be changed once we have completed dealing with exceptions and showing error dialogues 
			i.printStackTrace();
		}
	}
	
	/*public static void main(String[] args){
		Serialiser s = new Serialiser();
		List<Integer> dimensions = new ArrayList<Integer>();
		dimensions.add(3);
		dimensions.add(5);
		IBoard board = new SquareBoard(dimensions);
		//board.addPatch((IPatch) new Patch(0,0));
		
		Motion motion = new Motion( true, true, false, true,5 );
		GameObject gO = new GameObject(null, motion);
		board.addObject(gO, dimensions);
		
		//System.out.println(s.serialise(board));
*/	}
