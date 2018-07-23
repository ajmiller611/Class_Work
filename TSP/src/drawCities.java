import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class drawCities extends JPanel {
	Tour tourObj = new Tour();
	
	public drawCities(Tour tour) {
		setMinimumSize(new Dimension(410,410));
		setPreferredSize(new Dimension(410,410));
		setMaximumSize(new Dimension(410,410));
		//setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.BLACK));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		tourObj = tour;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.black);
		for (int i = 0; i < tourObj.tourSize(); i++) {
			City currentCity = tourObj.getCity(i);
			g.fillOval(currentCity.getX() * 2, (200 - currentCity.getY()) * 2, 10, 10);
			if (i != tourObj.tourSize() - 1)
			{
				g.drawLine(tourObj.getCity(i).getX() * 2 + 5, (200 - tourObj.getCity(i).getY()) * 2 + 5, tourObj.getCity(i + 1).getX() * 2 + 5, (200 - tourObj.getCity(i + 1).getY()) * 2 + 5);
			}
			else {
				g.drawLine(tourObj.getCity(i).getX() * 2 + 5, (200 - tourObj.getCity(i).getY()) * 2 + 5, tourObj.getCity(0).getX() * 2 + 5, (200 - tourObj.getCity(0).getY()) * 2 + 5);
			}
		}
		repaint();
	}
}
