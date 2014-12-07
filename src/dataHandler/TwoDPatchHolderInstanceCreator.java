package dataHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.AbstractBoard;
import boardObjectModels.TwoDPatchHolder;

import com.google.gson.InstanceCreator;

public class TwoDPatchHolderInstanceCreator implements InstanceCreator<TwoDPatchHolder> {
	public TwoDPatchHolder createInstance(Type type)
	   {
		List<Integer> dimensions= new ArrayList<Integer>();
	      return new TwoDPatchHolder(dimensions);
	   }
}
