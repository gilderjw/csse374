package problem.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import problem.sprites.ISprite;
import problem.sprites.NullIterator;
import problem.sprites.SpriteFactory;
import problem.sprites.SpriteIterator;

public class AnimationPanel extends JPanel {
	private static final long serialVersionUID = -9141525646098105526L;
	
	private List<ISprite> sprites;
	private volatile boolean animating;
	private long sleepTime;
	private SpriteFactory factory;

	public AnimationPanel(long sleepTime) {
		super(true);
		animating = false;
		sprites = Collections.synchronizedList(new ArrayList<ISprite>());
		this.sleepTime = sleepTime;
		this.factory = SpriteFactory.getInstance();
	}
	
	public void add(ISprite s) {
		sprites.add(s);
		this.repaint();
	}	
	
	@Override
	public Dimension getSize() {
		Dimension d = super.getSize();
		d.width -= factory.getWidth();
		d.height -= factory.getHeight();
		return d;
	}
	
	public void animate() {
		if(animating)
			return;
		
		animating = true;
		
		Thread animator = new Thread() {
			@Override
			public void run() {
				while(animating) {
					long start = System.currentTimeMillis();

					synchronized(sprites) {
						SpriteIterator spr = new SpriteIterator(sprites.iterator());
						
						while(spr.hasNext()){
							spr.next().move(getSize());
						}
					}
					repaint();

					long end = System.currentTimeMillis();
					long delta = sleepTime - (end - start);
					delta = (delta > 0)? delta : 0;
					
					try {
						Thread.sleep(delta);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		animator.start();
	}
	
	public void reset() {
		animating = false;
		sprites.clear();
		repaint();
	}
	
	void drawOnlyLeaves(Graphics2D g, ISprite s){
		Iterator<ISprite> itr = s.iterator();
		if(itr instanceof NullIterator){
			g.draw(s.getShape());
		} else {
			while(itr.hasNext())
				drawOnlyLeaves(g, itr.next());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)g;
		synchronized(sprites) {
			sprites.stream().forEach((ISprite s) -> drawOnlyLeaves(graphics, s));
//			SpriteIterator spr = new SpriteIterator(sprites.iterator());
//			while(spr.hasNext()){
//				Shape shape = spr.next().getShape();
//				if(shape != null){
//					graphics.draw(shape);
//				}
//			}
		}
	}
}
