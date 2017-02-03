package problem.sprites;

import java.util.Iterator;

public class NullIterator implements Iterator {
	boolean hashNext = true;
	ISprite s;
	
	public NullIterator(ISprite s){
		this.s = s;
	}
	
	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Object next() {
		if(this.hasNext()){
			this.hashNext = false;
			return this.s;
		}
		return null;
	}

}
