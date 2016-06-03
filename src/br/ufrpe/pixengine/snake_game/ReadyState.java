package br.ufrpe.pixengine.snake_game;

import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.components.State;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.input.KeyCode;

public class ReadyState extends State {
	public ReadyState() {
		manager.addObject(new GameImage("mr.nom/background.png"));
		manager.addObject(new GameImage("mr.nom/ready.png",50, 175));
	}

	@Override
	public void update(GameContainer gc, float dt) {
		Input game_input = gc.getInput();
		if (game_input.isKeyPressed(KeyCode.ENTER.ordinal())) {
			gc.getGame().pop();
			gc.getGame().push(new PlayState());
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
