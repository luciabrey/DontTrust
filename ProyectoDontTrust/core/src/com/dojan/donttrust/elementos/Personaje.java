package com.dojan.donttrust.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dojan.donttrust.utiles.Recursos;

public class Personaje extends Sprite{
	private Sprite sprite;
	private Texture [] regionMovimiento;
	private Texture imagen;
	private TextureRegion frameActual;
	int x, y;
	//private float speed = 60 * 2, gravity = 60 * 1.8f;
	
	public Personaje(int x, int y) {
		this.x = x;
		this.y = y; 
		
		imagen = new Texture(Recursos.PERHOMDERECHA);
		sprite = new Sprite(
				new Texture(Recursos.PERHOMDERECHA)
				,10, 16);
		TextureRegion [][] temp = TextureRegion.split(imagen, imagen.getWidth()/3, imagen.getHeight());
		
//		regionMovimiento = new Texture[3];
//		for(int i = 0; i < regionMovimiento.length; i++) {
//			regionMovimiento[i] = temp[0][i];
//		}
//		sprite.setPosition(x, y);
	}
	
	public void draw(int x, int y) {


	}
	public void movimiento(boolean derecha) {
		if(derecha) {
			x = x + 3;
//			for(int i = 0; i < regionMovimiento.length; i++) {
//				sprite.setTexture(regionMovimiento[i + 1]);
//			}
		}
		
		sprite.setPosition(x, y);
	}
	

	public void render(final SpriteBatch batch) {	
		batch.draw(sprite, x, y);
		
	}
	
	
}
