package br.ufrpe.pixengine.snake_game;

import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.components.State;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public class PlayState extends State {
	public PlayState() {
		manager.addObject(new Background("mr.nom/background.png"));
		manager.addObject(new SnakeHead(160, 240));
		manager.addObject(new Trash());
	}

	@Override
	public void update(GameContainer gc, float dt) {
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
