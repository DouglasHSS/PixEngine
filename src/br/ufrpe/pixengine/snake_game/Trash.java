package br.ufrpe.pixengine.snake_game;

import java.util.ArrayList;
import javafx.scene.image.Image;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public class Trash extends GameObject {
    private Image trash;
    private ArrayList<Image> trashes_options;
    
	public Trash() {
		setTag("trash");

		this.trashes_options = new ArrayList<Image>();
		this.trashes_options.add(new Image("mr.nom/stain1.png"));
		this.trashes_options.add(new Image("mr.nom/stain2.png"));
		this.trashes_options.add(new Image("mr.nom/stain3.png"));
		
		this.updateTrashPosition();
		
		addComponent(new Collider());
	}
	
	/**
	 * Função para atualizar a posição do lixinho, randomicamente.
	 */
	public void updateTrashPosition(){
			this.changeTrashImage();
			
			int[] coodenates = Utils.randomicCoordenates(320, 420, this);
			
			this.setX(coodenates[0]);
			this.setY(coodenates[1]);
	}
	
	/**
	 * Função para modificar a imagem do lixinho.
	 */
	private void changeTrashImage(){
		int trash_number = Utils.randInt(0, 2);
		this.trash = this.trashes_options.get(trash_number);
		this.w = (float) this.trash.getWidth();
		this.h = (float) this.trash.getHeight();
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		updateComponents(gc, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.trash, this.x, this.y);
	}

	@Override
	public void componentEvent(String name, GameObject object) {
		if (name.equalsIgnoreCase("collider")) {
			if(object instanceof SnakeHead){
				SnakeHead snake_head = (SnakeHead) object;
				snake_head.add_new_body_part();
			}
		}
	}

	@Override
	public float getW() {
		return (float) this.trash.getWidth();
	}

	@Override
	public float getH() {
		return (float) this.trash.getHeight();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
