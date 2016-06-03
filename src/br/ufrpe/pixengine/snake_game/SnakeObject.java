package br.ufrpe.pixengine.snake_game;

import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.input.KeyCode;

public abstract class SnakeObject extends GameObject{
	protected float old_x, old_y;
	protected KeyCode direction;
	protected float speed = 1;


	@Override
	public void setX(float x) {
		this.old_x = (Float)this.x != null ? this.x : x;
		super.setX(x);
	}

	@Override
	public void setY(float y) {
		this.old_y = (Float)this.y != null ? this.y : y;
		super.setY(y);
	}
	
	public void setDirection(KeyCode key_code) {
		this.direction = key_code;
	}
	
	public float getOld_x() {
		return this.old_x;
	}

	public float getOld_y() {
		return this.old_y;
	}


	@Override
	public void componentEvent(String name, GameObject object) {}

	@Override
	public void dispose() {}
	
	@Override
	public void update(GameContainer gc, float dt) {}

	@Override
	public void render(GameContainer gc, Renderer r) {}


}
