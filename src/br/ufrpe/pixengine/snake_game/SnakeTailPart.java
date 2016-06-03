package br.ufrpe.pixengine.snake_game;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;

public class SnakeTailPart extends SnakeObject {
    Image snake_tail_part;
    
	public SnakeTailPart() {
		setTag("snake_tail_part");
		
		this.snake_tail_part = new Image("mr.nom/tail.png");
		
		this.w = (float) snake_tail_part.getWidth();
		this.h = (float) snake_tail_part.getHeight();
		
		addComponent(new Collider());
	}
	
	public void update(GameContainer gc, float dt, SnakeObject snake_part) {
		updateComponents(gc, dt);
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.snake_tail_part, this.x, this.y);
	}

	@Override
	public void componentEvent(String name, GameObject object) { 
		if (name.equalsIgnoreCase("collider")) {
			object.setDead(true);
		}
	}

	public void update_position(SnakeObject snake_part, boolean moved) {
		this.setX(snake_part.getOld_x());
		this.setY(snake_part.getOld_y());
	}
	
	@Override
	public void dispose() {}



}
