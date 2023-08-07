package com.dojan.donttrust.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dojan.donttrust.elementos.Imagen;
import com.dojan.donttrust.elementos.Texto;
import com.dojan.donttrust.io.Entradas;
import com.dojan.donttrust.utiles.Configuracion;
import com.dojan.donttrust.utiles.Recursos;
import com.dojan.donttrust.utiles.Render;

public class PantallaMenu implements Screen{
	public float tiempo = 0;
	int opc = 1;
	Imagen fondo; 
	Imagen logo;
	SpriteBatch b;
	Entradas entradas = new Entradas(this);
	Texto opciones[] = new Texto[4];
	String textos[] = {"Un Jugador", 
					   "Multijugador",
					   "Configuracion",
					   "Salir"};

	@Override
	public void show() {
		b = Render.batch;
		fondo = new Imagen(Recursos.FONDOMENU);
		logo = new Imagen(Recursos.LOGO);
		
		logo.setSize(350, 350);
		fondo.setSize(Configuracion.ANCHO, Configuracion.ALTO);
		logo.setPosition((Configuracion.ANCHO/2), (Configuracion.ALTO - 200));
		
		Gdx.input.setInputProcessor(entradas);
		
		int avance = 30;
		
		for(int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTEMENU, 35, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Configuracion.ANCHO/2)-(opciones[i].getAncho()/2), 
					   ((Configuracion.ALTO/2) + (opciones[0].getAlto()/2)) - ((opciones[i].getAlto() * i) +(avance*i)));
		}
		
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		b.begin();
			fondo.dibujar();
			logo.dibujar();
			for(int i = 0; i < opciones.length; i++) {
				opciones[i].dibujar();
			}
		b.end();
	
		tiempo+=delta;
		if(entradas.isAbajo()) {
			if(tiempo>0.1f) {
				opc++;
				tiempo = 0;
				if(opc>4) {
					opc = 1;
				}

			}				
		}
		if(entradas.isArriba()) {
			if(tiempo>0.1f) {
				opc--;
				tiempo = 0;
				if(opc<1) {
					opc = 4;
				}

			}				
		}
		
		if((entradas.isEnter()) || (entradas.isClick())) {
			if(opc==1) {
				Render.app.setScreen(new PantallaMundos());
			}
		}
		
		for(int i = 0; i < opciones.length; i++) {
			if((entradas.getMouseX()>= opciones[i].getX()) && (entradas.getMouseX() <= opciones[i].getX() + opciones[i].getAncho())) {
				if((entradas.getMouseY()>= opciones[i].getY() - opciones[i].getAlto()) && (entradas.getMouseY() <= opciones[i].getY() + opciones[i].getAlto())) {
					opc = i + 1;
				}
			}
		}
		
		//Aumento de tamaño y cambio de color
		
		for(int i = 0; i< opciones.length; i++) {
			if(i==(opc - 1)) {
				//opciones[i].setSize(36);
				opciones[i].setColor(Color.GOLD);
			}else {
				//opciones[i].setSize(35);
				opciones[i].setColor(Color.WHITE);
			}
		}
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
