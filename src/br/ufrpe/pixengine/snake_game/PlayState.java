package br.ufrpe.pixengine.snake_game;

import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.components.State;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.input.KeyCode;

public class PlayState extends State {
	public PlayState(int speed) {
		manager.addObject(new GameImage("mr.nom/background_game.png"));
		manager.addObject(new SnakeHead(126, 240, speed));
		manager.addObject(new Trash());
	}

	@Override
	public void update(GameContainer gc, float dt) {
		Input game_input = gc.getInput();
		if (game_input.isKeyPressed(KeyCode.ENTER.ordinal())) {
			gc.getGame().push(new PauseState());
		} 
		manager.updateObjects(gc, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		manager.renderObjects(gc, r);
	}

	@Override
	public void dipose() {
		manager.diposeObjects();
	}

	public ObjectManager getManager() {
		return manager;
	}

	public void setManager(ObjectManager manager) {
		this.manager = manager;
	}

}
