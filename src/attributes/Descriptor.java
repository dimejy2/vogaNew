package attributes;

public abstract class Descriptor{

    protected String myValue; 
    private String myName;
    
    public Descriptor(String name, String attributeValue){
        myName = name;    
        myValue = attributeValue; 
        
    }
    
    public String getValue(){
        return myValue; 
    } 
    
    public void setValue (String newValue){
        myValue = newValue;
    }
    
    public String getName(){
        return myName; 
    }
    
    public String toString(){
        return this.getClass().getSimpleName(); 
    }
}

