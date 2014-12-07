package attributes;

import gameObjectModel.GameObject;

public class Attack extends AbstractAttribute {

    public Attack (double attributeValue) {
        super(attributeValue);
    }

    @Override
    public void action (GameObject obj) {
       // IAttribute temp = new SubAttribute(); 
        subtractAttribute(obj, "Health"); 
                   
    }
}
