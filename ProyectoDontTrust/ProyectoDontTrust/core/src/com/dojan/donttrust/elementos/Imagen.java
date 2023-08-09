package com.dojan.donttrust.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dojan.donttrust.utiles.Render;

public class Imagen {
	private Texture t;
	private Sprite s;
	GlyphLayout layout;
	float x = 0, y = 0;
	
	public Imagen(String ruta) {
		t = new Texture(ruta);
		s = new Sprite(t);
	}
	
	public void dibujar() {
		s.draw(Render.batch);
	}
	
	public void setTransparencia(float a) {
		s.setAlpha(a);
	}
	public void setSize(int ancho, int alto) {
		s.setSize(ancho, alto);
	}
	
	public void setPosition(int x, int y) {
		s.setCenter(x, y);
	}
	
}
