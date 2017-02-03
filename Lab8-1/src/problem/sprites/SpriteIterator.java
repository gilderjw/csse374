package problem.sprites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SpriteIterator implements Iterator<ISprite> {
	Stack<Iterator> stack;
	
	public SpriteIterator (Iterator<ISprite> iterator) {
		this.stack = new Stack<Iterator>();
		this.stack.push(iterator);
	}
	
	@Override
	public boolean hasNext() {
		if (this.stack.isEmpty()){
			return false;
		} else {
			Iterator<ISprite> itr = this.stack.peek();
			if(!itr.hasNext()){
				stack.pop();
				return this.hasNext();
			} else {
				return true;
			}
		}
	}

	@Override
	public ISprite next() {
		if(this.hasNext()){
			Iterator<ISprite> itr = this.stack.peek();
			ISprite sprite = itr.next();
			if (sprite != null){
				this.stack.push(sprite.iterator());
			} else {
				System.err.println("thing was null");
				this.stack.pop();
				return this.next();
			}
			return sprite;
		}
		return null;
	}

}
