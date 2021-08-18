package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -240840600533728354L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title); // создание окна и его название
		
		frame.setPreferredSize(new Dimension(width, height)); // первоначальный размер
		frame.setMaximumSize(new Dimension(width, height)); // максимальный размер
		frame.setMinimumSize(new Dimension(width, height)); // минимальный размер
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // при закрытии окна программа завершается
		frame.setResizable(false); // без возможности изменить размер
		frame.setLocationRelativeTo(null); // положение по центру
		frame.add(game); // добавляем в окно game
		frame.setVisible(true); // делаем окно видимым
		game.start(); // запуск

	}

}
