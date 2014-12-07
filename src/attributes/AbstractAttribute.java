package attributes;

import gameObjectModel.GameObject;

public abstract class AbstractAttribute {

	protected Object myValue; 
	
	public AbstractAttribute( Object attributeValue){
		myValue = attributeValue; 
		
	}
	
	public abstract void action(GameObject obj); 

	
	protected void subtractAttribute(GameObject obj, String attribute){
		
		for(IAttribute attr : obj.getAttributes() ){
            if(attr.toString().equals(attribute) && attr.getValue() instanceof Double ) 
            	 attr.setValue( (Double) attr.getValue() - (Double)this.getValue() ) ; 
		}
	}
	
	
	public Object getValue(){
		return myValue; 
	} 
	
	public void setValue (double newValue){
	    myValue = newValue;
	}
	
	public String toString(){
	    return this.getClass().getSimpleName(); 
	}
}
