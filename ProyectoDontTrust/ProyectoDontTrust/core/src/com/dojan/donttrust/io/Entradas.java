package com.dojan.donttrust.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.dojan.donttrust.pantallas.PantallaMenu;
import com.dojan.donttrust.pantallas.PantallaMundos;
import com.dojan.donttrust.sprites.Personaje;
import com.dojan.donttrust.utiles.Configuracion;

public class Entradas implements InputProcessor{
	PantallaMenu app;
	PantallaMundos appMundos;
	Personaje appMov;
	private boolean abajo = false, arriba = false;
	private boolean derecha = false, izquierda = false;
	public boolean isDerecha() {
		return derecha;
	}
	public boolean isIzquierda() {
		return izquierda;
	}
	private boolean enter = false; 
	private boolean click = false;	
	private float mouseX = 0, mouseY = 0;

	public Entradas(PantallaMenu app) {
		this.app = app;
	}	
	public Entradas(PantallaMundos pantallaMundos) {
		this.appMundos = appMundos;
	}
	public Entradas(Personaje appMov) {
		this.appMov = appMov;
	}
	public boolean isAbajo() {
		return abajo;
	}

	public boolean isArriba() {
		return arriba;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		//app.tiempo = 0.05f;
		if(keycode == Keys.DOWN) {
			abajo = true;
		}
		if(keycode == Keys.UP) {
			arriba = true;
		}		
		if(keycode == Keys.RIGHT) {
			derecha = true;
		}
		if(keycode == Keys.LEFT) {
			izquierda = true;
		}
		if(keycode == Keys.ENTER) {
			enter = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.DOWN) {
			abajo = false;
		}
		if(keycode == Keys.UP) {
			arriba = false;
		}
		if(keycode == Keys.ENTER) {
			enter = false;
		}
		
		if(keycode == Keys.RIGHT) {
			derecha = false;
		}
		if(keycode == Keys.LEFT) {
			izquierda = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		click = false;
		return false;
	}

	public boolean isClick() {
		return click;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Configuracion.ALTO - screenY; //esto es porque lleva la cuenta al "reves"
		return false;
	}
	
	

	public boolean isEnter() {
		return enter;
	}

	public float getMouseX() {
		return mouseX;
	}

	public float getMouseY() {
		return mouseY;
	}
	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}
