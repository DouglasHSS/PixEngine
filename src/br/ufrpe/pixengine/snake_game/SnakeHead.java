package br.ufrpe.pixengine.snake_game;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class SnakeHead extends GameObject {
    private Image snake_head;
    private KeyCode direction;
    private float speed = 100;


	public SnakeHead(float x, float y) {
		setTag("snake_head");
		
		this.x = x;
		this.y = y;
		
		this.snake_head = new Image("mr.nom/headleft.png"); 
		this.w = (float) snake_head.getWidth();
		this.h = (float) snake_head.getHeight();
		
		addComponent(new Collider());
	}

	@Override
	public void update(GameContainer gc, float dt) {
		Input game_input = gc.getInput();
		
		setHeadMovement(game_input);
		movingSnake(dt);
		checkBoundaries(gc);
		
		updateComponents(gc, dt);
	}
	
	/**
	 * Função para atualizar a posição da cabeça da cobra caso ela
	 * atinga os limites do game container.
	 * 
	 * @param gc
	 */
	public void checkBoundaries(GameContainer gc){
		float x_end = (float) (gc.getWidth() - this.snake_head.getWidth());	
		if (this.x < 0){
			this.setX(x_end);
		}else if (this.x > x_end) {
			this.setX(0);
		}
		
		float y_end = (float) (gc.getHeight() - this.snake_head.getHeight()); 
		if (this.y < 0){
			this.setY(y_end);
		}else if (this.y > y_end) {
			this.setY(0);
		}
	}
	
	/**
	 * Função para configurar o movimento que esta sendo
	 * realizado pela cobra.
	 * 
	 * @param game_input: Controlador de input do jogo
	 */
	public void setHeadMovement(Input game_input){
		if (game_input.isKeyPressed(KeyCode.UP.ordinal())) {
			this.direction = KeyCode.UP;
			this.snake_head = new Image("mr.nom/headup.png"); 
		}else if (game_input.isKeyPressed(KeyCode.LEFT.ordinal())) {
			this.direction = KeyCode.LEFT;
			this.snake_head = new Image("mr.nom/headleft.png"); 
		}else if (game_input.isKeyPressed(KeyCode.DOWN.ordinal())) {
			this.direction = KeyCode.DOWN;
			this.snake_head = new Image("mr.nom/headdown.png"); 
		}else if (game_input.isKeyPressed(KeyCode.RIGHT.ordinal())) {
			this.direction = KeyCode.RIGHT;
			this.snake_head = new Image("mr.nom/headright.png"); 
		}
	}
	
	/**
	 * Função para atualizar a posição da cabeça da cobra dependendo
	 * de sua direção e velocidade
	 * 
	 * @param dt
	 */
	public void movingSnake(float dt){
		if (this.direction == KeyCode.UP) {
			this.y -= dt * this.speed;
		}else if (this.direction == KeyCode.LEFT) {
			this.x -= dt * this.speed;
		}else if (this.direction == KeyCode.DOWN) {
			this.y += dt * this.speed;
		}else if (this.direction == KeyCode.RIGHT) {
			this.x += dt * this.speed;
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.snake_head, this.x, this.y);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void componentEvent(String name, GameObject object) {

	}
	

}
