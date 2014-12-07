package inputs;

import attributes.IAttribute;
import boardObjectModels.Patch;

public class InputInteract extends AbstractInput{
        
        private Patch myActor; 
        private Patch myReceiver;
        private IAttribute myAttribute;
        
        public InputInteract(Patch actor, IAttribute attribute, Patch receiver){
            myActor = actor;
            myReceiver = receiver;
            myAttribute = attribute;
        }
        
        public Patch getActor(){
           return myActor; 
        }
        public Patch getReceiver(){
            return myReceiver;
        }
        public IAttribute getAttribute(){
            return myAttribute;
        }

		public AbstractInput oppositeMethod() {
			// TODO Auto-generated method stub
			return null;
		}

        @Override
        public AbstractInput getMyOpposite () {
            // TODO Auto-generated method stub
            return null;
        }
    
}
