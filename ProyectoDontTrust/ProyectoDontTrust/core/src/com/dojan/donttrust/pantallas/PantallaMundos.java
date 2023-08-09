package com.dojan.donttrust.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dojan.donttrust.elementos.Imagen;
import com.dojan.donttrust.elementos.Texto;
import com.dojan.donttrust.io.Entradas;
//import com.dojan.donttrust.io.Entradas;
import com.dojan.donttrust.utiles.Configuracion;
import com.dojan.donttrust.utiles.Recursos;
import com.dojan.donttrust.utiles.Render;

public class PantallaMundos implements Screen {
	Imagen fondo;
	Imagen mundoUno;
	SpriteBatch b;
	String [] textos = {"Jugar Mundo", "Configuracion", "Volver al Menu"};
	Texto opciones [] = new Texto[3];
	Entradas entradas = new Entradas(this);
	boolean opc = false;
	int avance = 130;
	Texto mundo;
	
	@Override
	public void show() {
		b = Render.batch;
		fondo = new Imagen(Recursos.FONDOMUNDOS);
		fondo.setSize(Configuracion.ANCHO, Configuracion.ALTO);
		
		mundo = new Texto(Recursos.FUENTEMENU, 35, Color.WHITE, true);
		mundo.setTexto("JUGAR MUNDO INICIAL");
		mundo.setPosition((Configuracion.ANCHO/2) - (mundo.getAncho() / 2), (Configuracion.ALTO - 200));
	
		Gdx.input.setInputProcessor(entradas);
		
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		b.begin();
			fondo.dibujar();
			mundo.dibujar();
		b.end();		
		
			
		if((entradas.getMouseX()>= mundo.getX()) && (entradas.getMouseX() <=mundo.getX() + mundo.getAncho())) {
					if((entradas.getMouseY()>= mundo.getY() - mundo.getAlto()) && (entradas.getMouseY() <= mundo.getY() + mundo.getAlto())) {
						opc = true;
					}
		}else opc = false;
		if(entradas.isClick()){
			if(opc) {
				Render.app.setScreen(new PantallaJuego());
			}
		}
		if(opc) {
			mundo.setColor(Color.GOLD);
		}else mundo.setColor(Color.WHITE);
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
