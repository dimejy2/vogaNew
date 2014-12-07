package gameObjectModel;

import java.util.List;

import attributes.AbstractAttribute;


public class GameObject {

	private List< AbstractAttribute> myAttributeList; 
	private Motion myMotion; 

	
	/**
	 * constructor for the GameObject class
	 * @param myAttributes
	 */
	public GameObject( List<AbstractAttribute> myAttributes, Motion mover){
		myAttributeList = myAttributes;
		myMotion = mover; 
	}
	
/*	public boolean isEmpty(){
	    return (myAttributeList==null&&myMotion==null);
	}
	*/
	/**
	 * executes the action associated with the attribute at index attr on the GameObject that
	 * @param attr
	 * @param that
	 */
	public void executeAttribute( AbstractAttribute attr, GameObject that ){
		attr.action(that);	
	}
	
	public Motion getMyMotion(){
		return myMotion; 
	}
	
	public List<AbstractAttribute> getAttributes(){
	    return myAttributeList; 
	}
	

	public String toString(){
	    
	    if(!( myAttributeList == null))return myAttributeList.size()+""; 
	    else return "n" ; 
	}

}
