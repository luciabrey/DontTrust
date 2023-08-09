package com.dojan.donttrust.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dojan.donttrust.sprites.Personaje;
import com.dojan.donttrust.utiles.Configuracion;
import com.dojan.donttrust.utiles.Recursos;
import com.dojan.donttrust.utiles.Render;

public class PantallaJuego implements Screen {
	private TiledMap map;
	private TmxMapLoader mapLoader;
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camara;
	private Viewport gamePort;
	private TextureAtlas atlas;
	
	private Personaje player;
	SpriteBatch b;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	@Override
	public void show() {
		b = Render.batch;
		camara = new OrthographicCamera();
		gamePort = new FitViewport(Configuracion.V_WIDTH, Configuracion.V_HEIGHT, camara);

		atlas = new TextureAtlas(Recursos.PAQUETEPERSONAJES);
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(Recursos.MUNDOUNO);
		mapRenderer = new OrthogonalTiledMapRenderer(map); // , 1 / Configuracion.PPM
		
		float mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
	    float mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
	    
	    camara.setToOrtho(false, mapWidth, mapHeight); // Configurar cámara para igualar las dimensiones del mapa
        camara.position.set(mapWidth / 2, mapHeight / 2, 0); // Centrar la cámara en el centro del mapa
        camara.update();
		
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
      
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        //creamos las colsiones del piso y mostramos a modo de referencia
		
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	bdef.type = BodyDef.BodyType.StaticBody;
        	bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));
        	
        	body = world.createBody(bdef);
        	
        	shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2 ); //divididos 2
        	fdef.shape = shape;
        	body.createFixture(fdef);
        }
        
        player = new Personaje(world, this);
        
	}
	
	public void update(float delta) {
		player.movimientos(delta);
		
		player.update(delta);
		world.step(6/60f, 6, 2);
		
		
		camara.position.x = player.b2body.getPosition().x;
		
		camara.update();
		mapRenderer.setView(camara);
	}
	@Override
	public void render(float delta) {
		update(delta);
		
		Render.limpiarPantalla(0, 0, 0);
		mapRenderer.render();	

		b.begin();
			player.draw(b);
		b.end();
		

		
		b2dr.render(world, camara.combined);
		
		b.setProjectionMatrix(camara.combined);
		camara.update();
		
		mapRenderer.setView(camara);
	
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	@Override
	public void resize(int width, int height) {
//		camara.viewportWidth = width  / 2.5f;
//		camara.viewportHeight = height  / 2.5f;
		//camara.position.set(Configuracion.V_WIDTH / 2, Configuracion.V_HEIGHT / 2, 0);
		gamePort.update(width, height);

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
        // Liberar recursos cuando la pantalla no es visible
        map.dispose();
        mapRenderer.dispose();
	}

	@Override
	public void dispose() {
		map.dispose();
		mapRenderer.dispose();
		
	}

}
