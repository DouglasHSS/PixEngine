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
    private boolean can_change_position;
    
	public Trash() {
		setTag("trash");
		
		this.can_change_position = true;

		this.trashes_options = new ArrayList<Image>();
		this.trashes_options.add(new Image("mr.nom/stain1.png"));
		this.trashes_options.add(new Image("mr.nom/stain2.png"));
		this.trashes_options.add(new Image("mr.nom/stain3.png")); 
		
		addComponent(new Collider());
	}
	
	/**
	 * Função para atualizar a posição do lixinho, randomicamente.
	 */
	private void updateTrashPosition(GameContainer gc){
		if (this.can_change_position) {
			this.changeTrashImage();
			
			int[] coodenates = Utils.randomicCoordenates(gc, this);
			
			this.setX(coodenates[0]);
			this.setY(coodenates[1]);
			
			this.can_change_position = false;
		}	
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
		this.updateTrashPosition(gc);
		updateComponents(gc, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.trash, this.x, this.y);
	}

	@Override
	public void componentEvent(String name, GameObject object) {
		if (name.equalsIgnoreCase("collider")) {
			this.can_change_position = true;
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
