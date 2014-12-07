package dataHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boardObjectModels.PatchHolder;

import com.google.gson.InstanceCreator;

public class TwoDPatchHolderInstanceCreator implements InstanceCreator<PatchHolder> {
	public PatchHolder createInstance(Type type)
	   {
		List<Integer> dimensions= new ArrayList<Integer>();
	      return new PatchHolder(dimensions);
	   }
}
