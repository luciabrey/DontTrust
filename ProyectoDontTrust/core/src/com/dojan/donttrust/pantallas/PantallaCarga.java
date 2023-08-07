package com.dojan.donttrust.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dojan.donttrust.elementos.Imagen;
import com.dojan.donttrust.utiles.Configuracion;
import com.dojan.donttrust.utiles.Recursos;
import com.dojan.donttrust.utiles.Render;

public class PantallaCarga implements Screen{

	Imagen fondo;
	SpriteBatch b;
	float a = 0;
	boolean fadeInTerminado = false, termina = false;
	float contTiempo = 0, tiempoEspera = 5;
	float contTiempoTermina = 0, tiempoEsperaTermina = 5;
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOCARGA);
		fondo.setSize(300, 300);
		fondo.setTransparencia(a);
		fondo.setPosition(Configuracion.ANCHO/2, Configuracion.ALTO/2);
		b = Render.batch;
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		b.begin();
			fondo.dibujar();//mostramos el fondo
			
		b.end();
		
		procesarFade();
	}

	private void procesarFade() {
		if(!fadeInTerminado) {
			a+=0.01f;
			if(a>1) {
				a=1;
				fadeInTerminado = true;
			}
		} else {
			contTiempo+=0.05f;
			if(contTiempo>tiempoEspera) {
				a-=0.01f;
				if(a<0) {
					a=0;
					termina = true;
				}
			}
		}
		
		fondo.setTransparencia(a);
		
		if(termina) {
			contTiempoTermina += 0.05f;
			if(contTiempoTermina>tiempoEsperaTermina) {
				Render.app.setScreen(new PantallaMenu());
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
