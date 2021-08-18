package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx,my,220,110,200,64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			
			//help button
			if(mouseOver(mx,my,220,210,200,64)) {
				Game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx,my,220,310,200,64)) {
				System.exit(1);
			}
		}
		else if(Game.gameState == STATE.Help) {
			//back button for help
			if(mouseOver(mx,my,220,310,200,64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		else if(Game.gameState == STATE.End) {
			//try again button
			if(mouseOver(mx,my,220,310,200,64)) {
				Game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("impact", 4, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 20);

		//1 - bold
		//2 - italic
		//3 - bold-italic
		//4... - normal
		if(Game.gameState == STATE.Menu) {
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wawe", 265, 80);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(220, 110, 200, 64);
			g.drawString("Play", 290, 152);
	
			g.setColor(Color.white);
			g.drawRect(220, 210, 200, 64);
			g.drawString("Help", 290, 252);
			
			g.setColor(Color.white);
			g.drawRect(220, 310, 200, 64);
			g.drawString("Quit", 290, 352);
		} else if(Game.gameState == STATE.Help) {			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 265, 80);
			
			
			g.setFont(fnt);
			g.drawString("Why are you gay?", 20, 150);
			g.setColor(Color.blue);
			g.drawString("Who say's  i'm gay?", 20, 200);
			g.setColor(Color.red);
			g.drawString("You are GAY!", 20, 250);


			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(220, 310, 200, 64);
			g.drawString("Back", 285, 352);
		} else if(Game.gameState == STATE.End) {
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Game Over", 235, 80);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Your score: " + hud.getScore(), 20, 200);
			g.drawString("Your level: " + hud.getLevel(), 20, 230);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(220, 310, 200, 64);
			g.drawString("Try Again", 255, 350);
		}
	}
}
