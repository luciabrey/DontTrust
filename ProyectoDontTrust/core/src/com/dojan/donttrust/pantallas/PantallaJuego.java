package com.dojan.donttrust.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.dojan.donttrust.elementos.Personaje;
import com.dojan.donttrust.io.Entradas;
import com.dojan.donttrust.utiles.Recursos;
import com.dojan.donttrust.utiles.Render;

public class PantallaJuego implements Screen {
	private TiledMap map; 
	private World world;
	
	private OrthographicCamera camara;
	private Personaje personaje;
	Entradas entradas = new Entradas(this);
	SpriteBatch b;
	
	@Override
	public void show() {
		b = Render.batch;
		Gdx.input.setInputProcessor(entradas);
		map = new TmxMapLoader().load(Recursos.MUNDOUNO);
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camara = new OrthographicCamera();
		camara.setToOrtho(false, 800, 400);
		camara.update();
		
		personaje = new Personaje(100,100);
		
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		
		camara.update();
		b.setProjectionMatrix(camara.combined);
		renderer.setView(camara);
		renderer.render();
		
		b.begin();
			personaje.render(b);
			update();
		b.end();
	}

	private void update() {
		personaje.movimiento(entradas.isDerecha());
		
	}

	@Override
	public void resize(int width, int height) {
		camara.viewportWidth = width;
		camara.viewportHeight = height;
		camara.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		
	}

}
