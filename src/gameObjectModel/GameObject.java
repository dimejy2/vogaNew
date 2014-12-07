package gameObjectModel;

import java.util.List;

import attributes.AbstractAttribute;


public class GameObject {

	private List<AbstractAttribute> myAttributeList; 
	private AbstractRange myMotionRange; 
	private AbstractRange myInteractRange;

	
	/**
	 * constructor for the GameObject class
	 * @param myAttributes
	 */
	public GameObject( List<AbstractAttribute> myAttributes, AbstractRange motionRange, AbstractRange interactRange){
		myAttributeList = myAttributes;
		myMotionRange = motionRange;
		myInteractRange = interactRange;
	}
	
	/**
	 * executes the action associated with the attribute at index attr on the GameObject that
	 * @param attr
	 * @param that
	 */
	public void executeAttribute( AbstractAttribute attr, GameObject that ){
		attr.action(that);	
	}
	
	public AbstractRange getMotionRange(){
		return myMotionRange; 
	}
	
	public AbstractRange getAttackRange(){
        return myInteractRange; 
    }
	
	public List<AbstractAttribute> getAttributes(){
	    return myAttributeList; 
	}
	

	public String toString(){
	    
	    if(!( myAttributeList == null))return myAttributeList.size()+""; 
	    else return "n" ; 
	}

}
