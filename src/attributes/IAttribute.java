package attributes;

import gameObjectModel.GameObject;

public interface IAttribute {
	

	/**
	 * performs the action associated with the attribute on the GameObject obj
	 * @param obj
	 */
	public void action( GameObject obj);
	
	
	/**
	 * These methods don't make sense here - they need to be moved
	 * If the interface doesn't have a value you shouldn't be able
	 * to call getValue() right?
	 * 
	 */

    public Object getValue ();

    public void setValue (double d);

}
