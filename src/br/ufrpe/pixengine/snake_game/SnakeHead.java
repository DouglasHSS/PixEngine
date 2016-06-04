package br.ufrpe.pixengine.snake_game;

import java.util.ArrayList;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class SnakeHead extends SnakeObject {
    private Image snake_head;
    private ArrayList<SnakeTailPart> snake_tail_array;
    private float gap;
    private boolean has_moved;

	public SnakeHead(float x, float y, int speed) {
		setTag("snake_head");
		
		this.x = x;
		this.y = y;
		
		this.old_x = x;
		this.old_y = y;
		
		this.speed = speed;
		
		this.gap = 0;
		
		this.snake_head = new Image("mr.nom/headleft.png");
		this.direction  = KeyCode.LEFT;
		
		this.w = (float) this.snake_head.getWidth();
		this.h = (float) this.snake_head.getHeight();
		
		this.snake_tail_array = new ArrayList<SnakeTailPart>();

		addComponent(new Collider());
	}

	/**
	 * Função que que se encarregará de adicionar um novo  
	 * SnakeTailPart.
	 */
	public void add_new_body_part(){
		this.snake_tail_array.add(new SnakeTailPart());
	}
	
	/**
	 * Função para atualizar a direção da cabeça 
	 * da cobra
	 * 
	 * @param game_input: Controlador de input do jogo
	 */
	private void updateHeadDiretion(Input game_input){
		if (game_input.isKeyPressed(KeyCode.UP.ordinal())) {
			this.setDirection(KeyCode.UP);
		}else if (game_input.isKeyPressed(KeyCode.LEFT.ordinal())) {
			this.setDirection(KeyCode.LEFT);
		}else if (game_input.isKeyPressed(KeyCode.DOWN.ordinal())) {
			this.setDirection(KeyCode.DOWN);
		}else if (game_input.isKeyPressed(KeyCode.RIGHT.ordinal())) {
			this.setDirection(KeyCode.RIGHT);
		}
	}
	
	/**
	 * Função para atualizar a posição da cabeça da cobra.
	 * 
	 * @param dt
	 */
	private void movingSnakeHead(){
		if (this.direction == KeyCode.UP) {
			this.setX(this.x);
			this.setY( this.y - this.w);
			this.snake_head = new Image("mr.nom/headup.png");
		}else if (this.direction == KeyCode.LEFT) {
			this.setX(this.x - this.w);
			this.setY(this.y);
			this.snake_head = new Image("mr.nom/headleft.png");
		}else if (this.direction == KeyCode.DOWN) {
			this.setX(this.x);
			this.setY(this.y + this.w);
			this.snake_head = new Image("mr.nom/headdown.png");
		}else if (this.direction == KeyCode.RIGHT) {
			this.setX(this.x + this.w);
			this.setY(this.y);
			this.snake_head = new Image("mr.nom/headright.png");
		}
	}
	
	/**
	 * Função para atualizar a posição da cabeça da cobra caso ela
	 * atinga os limites do game container.
	 * 
	 * @param gc
	 */
	private void checkBoundaries(GameContainer gc){
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
	
	@Override
	public void update(GameContainer gc, float dt) {
		if(this.isDead()){
			gc.getGame().pop();
			gc.getGame().push(new GameOverState());
		}
		
		this.gap += this.speed;
		this.has_moved = false;
		
		Input game_input = gc.getInput();
		this.updateHeadDiretion(game_input);
		
		if (this.w <= (int)gap){
			this.checkBoundaries(gc);
			this.movingSnakeHead();
			
			this.gap = 0;
			this.has_moved = true;
		}
		updateComponents(gc, dt);
		for (SnakeTailPart tail_part : snake_tail_array) {
			tail_part.updateComponents(gc, dt);
		}
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.snake_head, this.x, this.y);
		
		SnakeObject snake_part = this;
		for (SnakeTailPart tail_part : snake_tail_array) {
			if (has_moved){
				tail_part.update_position(snake_part, has_moved);
			}
			tail_part.render(gc, r);
			snake_part = tail_part;
		}
	}
	
	@Override
	public void dispose() {}
	@Override
	public void componentEvent(String name, GameObject object) {
		if (name.equalsIgnoreCase("collider")) {
			if(object instanceof Trash){
				Trash trash = (Trash) object;
				trash.updateTrashPosition();
			}
		}
	}
}
