package com.dojan.donttrust;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.dojan.donttrust.DontTrust;
import com.dojan.donttrust.utiles.Configuracion;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Dont Trust");
		config.setResizable(false);
		config.setWindowedMode(Configuracion.ANCHO, Configuracion.ALTO);
		new Lwjgl3Application(new DontTrust(), config);
	}
}
