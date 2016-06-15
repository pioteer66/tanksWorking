package com.mygdx.tanks;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.utils.StringBuilder;
import net.tanks.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GameScreen implements Screen {
    private Game game;
	private SpriteBatch batch;
    final private HashMap<TypeOfObject, Sprite> spriteMap;
	Texture greenTankTexture, redTankTexture, blueTankTexture, orangeTankTexture;
	Texture shrubTexture, brickTexture, stoneTexture, missileTexture;
    //Tank tank = new Tank(1,5, Constants.TANK_START_X * Constants.TANK_SIZE, Constants.TANK_START_Y * Constants.TANK_SIZE);
    Board board;
    StringBuilder comunicate;


    private Date date;
    private long timeStart;
    private long timeEnd;
    private long reloadTime; //milisekundy
    private double missileSpeed;     // jednostki odswiezen
    private Socket connectionSocket;
    private int playerId = -1;
    private Magazine magazine;
    private boolean uninitialized = true; // czy gra renderowana jest pierwszy raz
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;

    public GameScreen(Game game, Magazine magazine){
        this.game=game;
        this.batch= new SpriteBatch();
        this.spriteMap=this.loadTextures();
        stoneTexture = new Texture("niezniszczalny.png");
        redTankTexture = new Texture("czerwonyCzolg.png");
        blueTankTexture = new Texture("niebieskiCzolg.png");
        orangeTankTexture = new Texture("zoltyCzolg.png");
        greenTankTexture = new Texture("zielonyCzolg.png");
        shrubTexture = new Texture("krzak.png");
        brickTexture = new Texture("cegla.png");
        missileTexture = new Texture("pocisk.png");
        board = new Board("plansza.txt");


        //tank.setDirection(LEFT);
        this.date = new Date();
        this.timeStart = date.getTime();
        this.timeEnd = date.getTime();
        this.reloadTime = 500; //ms
        this.missileSpeed = 600.0; // jednostek
        this.magazine = magazine;
        comunicate = new StringBuilder();
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
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        //update();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(brickTexture, 775,775);
        //double x = tank.getX();
        //double y = tank.getY();
        /*batch.draw(new TextureRegion(redTankTexture), (float)x, (float)y,
                (float) tank.getCenterX()-(float)x, (float) tank.getCenterY()-(float)y,
                (float) tank.getWidth(), (float) tank.getHeight(), 1f, 1f, (float) tank.getDirection().getValue()*90);*/
        while(uninitialized) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.magazine.checkMap()) {
                this.board = new Board(magazine.getMap(), magazine.getLivesOnStart());
                uninitialized = false;
                break;
            }
        }
        ois = magazine.ois;
        oos = magazine.oos;
        drawBoard();
        drawMissiles();
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update();
        batch.end();
        this.date = new Date(); // aktualizuje czas
    }

    @Override
    public void hide() {

    }

    private void checkForCollisions(Block object, int j)
    {
        if(object.getSymbol() != 'Z') {
            Rectangle rect = new Rectangle((int) object.getX(), (int) object.getY(), 25, 25);
            for (int i = 0; i < this.board.missilesList.size(); i++) {
                if (rect.contains(this.board.missilesList.get(i).getCenterX(), this.board.missilesList.get(i).getCenterY())) {
                    this.board.missilesList.remove(i);
                    board.objectsList.get(j).setStamina(board.objectsList.get(j).getStamina() -1);
                }
            }
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    private void updateBoardState()
    {
        ArrayList<Block> temp = new ArrayList<Block>();
        for (Block object: board.objectsList){
            if (object.getStamina() != 0){
                temp.add(object);
            }
        }
        board.objectsList.clear();
        for (Block object:temp){
            board.objectsList.add(object);
        }
    }

    private void drawBoard(){
        int j=0;
        for (Block object: board.objectsList){
            switch (object.getSymbol()){
                case 'C':{
                    batch.draw(brickTexture, (int)object.getX(), (int)object.getY());
                    break;
                }
                case 'K':{
                    batch.draw(stoneTexture, (int)object.getX(), (int)object.getY());
                    break;
                }
                case 'Z':{
                    batch.draw(shrubTexture, (int)object.getX(), (int)object.getY());
                    break;
                }
            }
            //checkForCollisions(object, j);
            j++;
        }
        for (Tank tank: board.getTanks()){
                double x= tank.getX();
                double y = tank.getY();
                batch.draw(new TextureRegion(returnProperTexture(tank.getPlayerId())), (float)tank.x, (float)y,
                        (float) tank.getCenterX()-(float)x, (float) tank.getCenterY()-(float)y,
                        (float) tank.getWidth(), (float) tank.getHeight(), 1f, 1f, (float) tank.getDirection().getValue()*90);
        }
       // updateBoardState();
    }

    private Texture returnProperTexture(int id)
    {
        switch (id){
            case 0:{
                return redTankTexture;
            }
            case 1:{
                return  greenTankTexture;
            }
            case 2:{
                return orangeTankTexture;
            }
            case 3:{
                return blueTankTexture;
            }
            default:{
                return null;
            }
        }
    }

    private void updateMissilePosition(Missile missile)
    {
        double missileStep = this.missileSpeed *( 1.0 / Gdx.graphics.getFramesPerSecond()); // predkosc = jednoski / ramke
        switch ( missile.getDirection()){
            case LEFT: {
                missile.x -= missileStep;
                break;
            }
            case RIGHT: {
                missile.x += missileStep;
                break;
            }
            case UP: {
                missile.y += missileStep;
                break;
            }
            case DOWN: {
                missile.y -= missileStep;
                break;
            }
        }
    }

    private void drawMissiles(){
        //Podobna funkcja jak dla rysowania czołgu
        for (Missile missile : board.missilesList){
                    batch.draw(new TextureRegion(missileTexture),
                            (float) missile.getX(), (float) missile.getY(),
                            (float) missile.getCenterX()-(float) missile.getX(), (float) missile.getCenterY()-(float) missile.getY(),
                            (float) missile.getWidth(), (float) missile.getHeight(),
                            1f, 1f,
                            (float) missile.getDirection().getValue()*90);
            updateMissilePosition(missile);
        }
    }

    private void removeRedundantMissiles(){
        //Usuwanie pocisków po wylocie z planszy
        for (int i = 0; i< board.missilesList.size(); i++){
            Missile missile = board.missilesList.get(i);
            switch(missile.getDirection()){
                case LEFT: {
                    if (missile.getX() <= 0) board.missilesList.remove(i);
                    break;
                }
                case RIGHT: {
                    if (missile.getX() >= Constants.WIDTH) board.missilesList.remove(i);
                    break;
                }
                case UP: {
                    if (missile.getY() >= Constants.HEIGHT) board.missilesList.remove(i);
                    break;
                }
                case DOWN: {
                    if (missile.getY() <= 0) board.missilesList.remove(i);
                    break;
                }
            }
        }
    }

    /*private Point getMissileStartingPosition()
    {
        Point start = new Point();
        start.x = 0;
        start.y = 0;
        switch (tank.getDirection()) {
            case LEFT: {
                start.x = (int) (tank.getX());
                start.y = (int) (tank.getY() + tank.height / 2 - 5);
                break;
            }
            case RIGHT: {
                start.x = (int) (tank.getX() + tank.width);
                start.y = (int) (tank.getY() + tank.height / 2 - 5);
                break;
            }
            case UP: {
                start.x = (int) (tank.getX() + tank.width / 2 - 5);
                start.y = (int) (tank.getY() + tank.height);
                break;
            }
            case DOWN: {
                start.x = (int) (tank.getX() + tank.width / 2 - 5);
                start.y = (int) (tank.getY());
                break;
            }

        }
        return start;
    }*/

    /*private void launchMissile(){
        if(this.timeStart >= timeEnd) { //sprawdza czy upłyna rzadany czas przaładowania
            Point start = getMissileStartingPosition();
            //start_x i start_y to początkowa pozycja pocisku
            Missile missile = new Missile(tank, tank.getDirection());
            missile.x = start.x;
            missile.y = start.y;
            board.missilesList.add(missile);
            timeEnd = this.date.getTime() + this.reloadTime;
        }
    }*/

    /*private void collisionDetector(int x, int y){
        //uniemożliwienie wyjechania poza planszę
        if (tank.getX() >= Constants.WIDTH - Constants.TANK_SIZE || tank.getX() <=0 ||
            tank.getY() <= 0 || tank.getY() >= Constants.HEIGHT - Constants.TANK_SIZE)
        {
            tank.x = x;
            tank.y = y;
        } else {  //sprawdzenie kolizji, czyli dzięki temu czołg nie wjeżdża na bloki (chyba, że to zarośla)
            boolean isCollision = false;
            for (Block object : board.objectsList)
            {
                if (object.intersection(tank).width >2 && object.intersection(tank).height >2 && object.getSymbol() != 'Z')
                {
                    isCollision = true;
                    break;
                }
            }
            if (isCollision)   //Jeśli wystąpiła kolizja z blokiem, to cofnij na pole sprzed zmiany
            {
                tank.x = x;
                tank.y = y;
            }
        }
    }*/

    private void update(){
        this.timeStart = date.getTime();
        Tank tank = board.getTanks().get(magazine.getActivePlayerId());
        int x = (int) tank.getX();
        int y = (int) tank.getY();

        try{
            //Odczyt klawiszy
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                comunicate.append(magazine.getActivePlayerId()+ ",1");
                //tank.x -= Constants.TANK_SPEED;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                //tank.x += Constants.TANK_SPEED;
                comunicate.append(magazine.getActivePlayerId()+ ",2");
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                //tank.y += Constants.TANK_SPEED;
                comunicate.append(magazine.getActivePlayerId()+ ",3");
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                //tank.y -= Constants.TANK_SPEED;
                comunicate.append(magazine.getActivePlayerId()+ ",4");
            } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                comunicate.append(magazine.getActivePlayerId()+ ",5");
            }
            else comunicate.append(magazine.getActivePlayerId()+",0");
            oos.writeObject(comunicate.toString());
            //collisionDetector(x, y);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        if (magazine.IsQueueEmpty()) return;
        ArrayList<ArrayList<? extends Packet>> packet = magazine.getPacketFromQueue();
        updatePositions((ArrayList<PositionPacket>)packet.get(0));
    }

    public synchronized void updatePositions(ArrayList <PositionPacket> positionPackets){
        for (PositionPacket packet:positionPackets) {
            magazine.getBoard().getTanks().get(packet.getId()).setLocation((int)packet.getPositionX()*10,
                    (int)packet.getPositionY()*10);
        }
    }

    public void updateMissiles(ArrayList<MissilePacket> missilePackets){
        for (MissilePacket packet:missilePackets) {
            //packetsQueue.add(new MissilePacket(packet.getPlayerId(), packet.getPositionX(), packet.getPositionY()));
        }
    }

    public void updateHits(ArrayList<HitsPacket> hitsPackets){
        for (HitsPacket packet:hitsPackets) {
            //packetsQueue.add(new HitsPacket(packet.getPlayerId(), packet.getPositionX(), packet.getPositionY()));
        }
    }

    public void updateStatistics(ArrayList<PlayerStatisticsPacket> statisticsPackets){
        for (PlayerStatisticsPacket packet: statisticsPackets) {
            //packetsQueue.add(new PlayerStatisticsPacket(packet.getPlayerId(), packet.getPoints(), packet.getRemainingLives()));
        }
    }


    public HashMap<TypeOfObject,Sprite> loadTextures(){
        HashMap<TypeOfObject, Sprite> map = new HashMap<TypeOfObject, Sprite>();
        try {
            map.put(TypeOfObject.Rock, new Sprite(new Texture("niezniszczalny.png")));
            map.put(TypeOfObject.FirstTank, new Sprite(new Texture("czerwonyCzolg.png")));
            map.put(TypeOfObject.SecondTank, new Sprite(new Texture("niebieskiCzolg.png")));
            map.put(TypeOfObject.ThirdTank, new Sprite(new Texture("zoltyCzolg.png")));
            map.put(TypeOfObject.ForthTank, new Sprite(new Texture("zielonyCzolg.png")));
            map.put(TypeOfObject.Bush, new Sprite(new Texture("krzak.png")));
            map.put(TypeOfObject.Brick, new Sprite(new Texture("cegla.png")));
            map.put(TypeOfObject.Missile,new Sprite(new Texture("pocisk.png")));
            map.put(TypeOfObject.Explosion, new Sprite(new Texture("wybuch.png")));
        }catch (Exception e){
            System.out.println("Nie można załadować tekstur");
        }
        //map.forEach((type, sprite) -> sprite.setOriginCenter());
        return map;

    }

	@Override
	public void dispose() {
		batch.dispose();
		stoneTexture.dispose();
		redTankTexture.dispose();
		blueTankTexture.dispose();
		orangeTankTexture.dispose();
		greenTankTexture.dispose();
		shrubTexture.dispose();
        missileTexture.dispose();
		brickTexture.dispose();
	}

}
