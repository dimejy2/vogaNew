package gameObjectModel;

import java.util.List;

import attributes.IAttribute;


public class GameObject {

	private List< IAttribute> myAttributeList; 
	private Motion myMotion; 

	
	/**
	 * constructor for the GameObject class
	 * @param myAttributes
	 */
	public GameObject( List<IAttribute> myAttributes, Motion mover){
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
	public void executeAttribute( IAttribute attr, GameObject that ){
		attr.action(that);	
	}
	
	public Motion getMyMotion(){
		return myMotion; 
	}
	
	public List<IAttribute> getAttributes(){
	    return myAttributeList; 
	}
	

	public String toString(){
	    
	    if(!( myAttributeList == null))return myAttributeList.size()+""; 
	    else return "n" ; 
	}

}
