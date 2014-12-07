package attributes;

import gameObjectModel.GameObject;

public abstract class AbstractAttribute {

	protected Double myValue; 
	private String myName;
	
	public AbstractAttribute(String name, Double attributeValue){
	    myName = name;    
		myValue = attributeValue; 
		
	}
	
	public abstract void action(GameObject obj); 

	
	public Double getValue(){
		return myValue; 
	} 
	
	public void setValue (double newValue){
	    myValue = newValue;
	}
	
	public String getName(){
        return myName; 
    }
	
	public String toString(){
	    return this.getClass().getSimpleName(); 
	}
}
