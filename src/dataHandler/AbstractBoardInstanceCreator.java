package dataHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.InstanceCreator;

import boardObjectModels.AbstractBoard;

public class AbstractBoardInstanceCreator implements InstanceCreator<AbstractBoard>{
	
	public AbstractBoard createInstance(Type type)
	   {
		List<Integer> dimensions= new ArrayList<Integer>();
	      return new AbstractBoard(dimensions);
	   }
	
}
