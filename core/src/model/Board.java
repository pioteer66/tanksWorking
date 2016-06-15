package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.mygdx.tanks.Constants;

public class Board {
    public ArrayList <Block> objectsList;
    public ArrayList <Missile> missilesList;
    private ArrayList<Tank> tanks;

    public Board(String path) {
        objectsList = new ArrayList<Block>();
        missilesList = new ArrayList<Missile>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.read();
            for (int i = 1; i <= 32; i++) {
                String line = br.readLine();
                for (int j = 0; j < 32; j++) {
                    char sign = line.charAt(j * 2);
                    switch (sign) {
                        case 'C': {
                            this.objectsList.add(new Brick(j*25, Constants.HEIGHT -i*25));
                            break;
                        }
                        case 'K': {
                            this.objectsList.add(new Stone(j*25, Constants.HEIGHT -i*25));
                            break;
                        }
                        case 'Z': {
                            this.objectsList.add(new Shrub(j*25, Constants.HEIGHT -i*25));
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Nie znaleziono pliku!");
        } catch (IOException ex) {
            ex.getMessage();
        }

    }


    public Board(char[][] map, int lives){
        objectsList = new ArrayList<Block>();
        missilesList = new ArrayList<Missile>();
        tanks = new ArrayList<Tank>();
        this.objectsList.clear();
        Tank tank;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                char sign = map[i][j];
                switch (sign) {
                    case 'C': {
                        this.objectsList.add(new Brick(j*25, Constants.HEIGHT -(i+1)*25));
                        break;
                    }
                    case 'K': {
                        this.objectsList.add(new Stone(j*25, Constants.HEIGHT -(i+1)*25));
                        break;
                    }
                    case 'Z': {
                        this.objectsList.add(new Shrub(j*25, Constants.HEIGHT -(i+1)*25));
                        break;
                    }
                    case '0': {
                        tank = new Tank(0, lives, j*25, Constants.HEIGHT - (i+1)*25);
                        tank.setDirection(Direction.LEFT);
                        this.tanks.add(tank);
                        break;
                    }
                    case '1': {
                        tank = new Tank(1, lives, j*25, Constants.HEIGHT - (i+1)*25);
                        tank.setDirection(Direction.RIGHT);
                        this.tanks.add(tank);
                        break;
                    }
                    case '2': {
                        tank = new Tank(2, lives, j*25, Constants.HEIGHT - (i+1)*25);
                        tank.setDirection(Direction.UP);
                        this.tanks.add(tank);
                        break;
                    }
                    case '3': {
                        tank = new Tank(3, lives, j*25, Constants.HEIGHT - (i+1)*25);
                        tank.setDirection(Direction.DOWN);
                        this.tanks.add(tank);
                        break;
                    }
                }
            }
        }
    }

    public ArrayList<Block> getObjectsList() {
        return objectsList;
    }

    public void setObjectsList(ArrayList<Block> objectsList) {
        this.objectsList = objectsList;
    }

    public ArrayList<Missile> getMissilesList() {
        return missilesList;
    }

    public void setMissilesList(ArrayList<Missile> missilesList) {
        this.missilesList = missilesList;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }
}
