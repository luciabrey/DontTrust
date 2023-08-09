package com.dojan.donttrust;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dojan.donttrust.pantallas.PantallaCarga;
import com.dojan.donttrust.pantallas.PantallaJuego;
import com.dojan.donttrust.utiles.Render;

public class DontTrust extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		Render.batch = new SpriteBatch();
		Render.app = this;
		this.setScreen(new PantallaCarga());//ACA PANTALLA DE CARGA
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		Render.batch.dispose();
	}

}
