package com.mygdx.tanks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.tanks.TanksGame;
import com.mygdx.tanks.PacketMagazine;
import com.mygdx.tanks.SocketWorker;
import java.net.Socket;


public class DesktopLauncher {
	public static final int PORT = 8088;
	public static final String ip = "localhost";
	private static final PacketMagazine packetMagazine = new PacketMagazine();

	public static void main (String[] arg) {
		try {
		Socket socket = new Socket(ip, PORT);
		SocketWorker sw = new SocketWorker(socket,packetMagazine);
		new Thread(sw).start();


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height= 800;
		config.resizable = false;
		config.backgroundFPS = 60;
		new LwjglApplication(new TanksGame(packetMagazine), config);
			// trzeba jeszcze zamykać socket ale  nie wiem gdzie , na razie
		} catch (Exception ex){
			System.out.println(ex);
		}

	}
}
