package com.dojan.donttrust.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.dojan.donttrust.io.Entradas;
import com.dojan.donttrust.pantallas.PantallaJuego;
import com.dojan.donttrust.utiles.Configuracion;

public class Personaje extends Sprite{
	// animaciones del personaje
	public enum State { FALLING, JUMPING, STANDING, RUNNING};
	
	//Variables de estado y fisicas
	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	//texturas y animaciones
	private TextureRegion standHombre;
	private Animation  homCaminando;
	private Animation  homSaltando;
	
	private float stateTimer;
	private Entradas entradas = new Entradas(this);

	private boolean runningRight;
	
	public Personaje(World world, PantallaJuego screen) {
		super(screen.getAtlas().findRegion("little_mario"));
		
		Gdx.input.setInputProcessor(entradas);
		
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		//crear texturas y animaciones
		//caminando
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 0; i < 4; i++) {
			frames.add(new TextureRegion(getTexture(), i * 16, 0 , 16, 16));
		}
		
		homCaminando = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
		
		//saltando
		
		for(int i = 0; i < 6; i++) {
			frames.add(new TextureRegion(getTexture(), i * 16, 0 , 16, 16));
		}
		homSaltando = new Animation<TextureRegion>(0.1f, frames);
		standHombre = new TextureRegion(getTexture(), 0, 0, 16, 16);
		
		definePersonajeHom();
		
		setBounds(0, 0, 16 , 16);
		setRegion(standHombre);
	}
	public void update(float delta) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		setRegion(getFrame(delta));
	}

	private TextureRegion getFrame(float delta) {
		currentState = getState();
		
		TextureRegion region = standHombre ;
		switch(currentState) {
		case JUMPING:
			region = (TextureRegion) homSaltando.getKeyFrame(stateTimer);
			break;
		case FALLING:
			//proximamente
			break;
		case RUNNING:
			region = (TextureRegion) homCaminando.getKeyFrame(stateTimer, true);
			break;
		case STANDING:
			//proximamente
			break;
		default:
			region = standHombre;
			break;
		}
		//para saber que lado corre y dar vuelta la animacion segun corresponda
		if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
		    region.flip(true, false);
		    runningRight = false;
		} else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
		    region.flip(true, false);
		    runningRight = true;
		}
		
		// Actualizar el temporizador de estado
		stateTimer = currentState == previousState ? stateTimer + delta : 0;
		previousState = currentState;
		return region;
		
	}
	private State getState() {
		//para determinar el estadoa actual del personaje
		if(b2body.getLinearVelocity().y>0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
			return State.JUMPING;
		}
		else if(b2body.getLinearVelocity().y < 0) {
			return State.FALLING;
		}else if(b2body.getLinearVelocity().x != 0) {
			return State.RUNNING;
		}else return State.STANDING;
		
	}
	private void definePersonajeHom() {
		//Creamos el cuerpo y las colisiones del personaje con le mapa
		BodyDef bdef = new BodyDef();
		bdef.position.set((48 / Configuracion.PPM), (48 / Configuracion.PPM) + 48);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5  );
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}
	
	public void movimientos(float delta) {
		if(entradas.isArriba()) {
			b2body.applyLinearImpulse(0, 30f, 0, 0, true);
		}
		if(entradas.isDerecha()) {
			//derecha - arriba - izquierda- abajo
			b2body.applyLinearImpulse(new Vector2(30f, 0), b2body.getWorldCenter(), true);
		}
		if(entradas.isIzquierda()) {
			b2body.applyLinearImpulse(new Vector2(-30f, 0), b2body.getWorldCenter(),true);
		}
	}
}
