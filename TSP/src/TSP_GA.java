import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

import javax.swing.*;

public class TSP_GA {
	private JFrame mainFrame;
	private JPanel mainPanel, cityPanel, rightPanel, bottomPanel;
	private JTextField populationSize, mutationRate, maxGen;
	private JLabel titleLabel, populationSizeLabel, mutationRateLabel, maxGenLabel, initialDistanceLabel, initialDistanceValueLabel, solutionDistanceLabel, solutionDistanceValueLabel, numOfCitiesLabel, numOfCitiesValueLabel;
	private JButton startButton, addCity;
	JButton[][] grid;
	
	public TSP_GA() {
		mainFrame = new JFrame("Traveling Salesman Genetic Algorithm");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setSize(800,800);
		GridBagLayout layout = new GridBagLayout();
		mainFrame.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setMinimumSize(new Dimension(600, 633));
		mainPanel.setPreferredSize(new Dimension(600, 633));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		rightPanel = new JPanel();
		//rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		rightPanel.setMinimumSize(new Dimension(200, 800));
		rightPanel.setPreferredSize(new Dimension(200, 800));
		rightPanel.setBackground(Color.white);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		bottomPanel.setMinimumSize(new Dimension(600, 166));
		bottomPanel.setPreferredSize(new Dimension(600, 166));
		bottomPanel.setBackground(Color.white);
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		//gbc.fill = GridBagConstraints.BOTH;
		mainFrame.add(mainPanel, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		//gbc.fill = GridBagConstraints.BOTH;
		mainFrame.add(rightPanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.weighty = 1.0;
		//gbc.fill = GridBagConstraints.SOUTH;
		mainFrame.add(bottomPanel, gbc);
		
		JPanel test = new JPanel();
		test.setMaximumSize(new Dimension(600, 116));
		test.setBackground(Color.lightGray);
		
		titleLabel = new JLabel("Traveling Salesman Problem using Genetic Algorithm");
		test.add(titleLabel);
		mainPanel.add(test);
		
		ButtonHandler handler = new ButtonHandler();
		
		populationSizeLabel = new JLabel("Population Size:");
		rightPanel.add(populationSizeLabel);
		
		populationSize = new JTextField();
		populationSize.setPreferredSize(new Dimension(100, 25));
		populationSize.setText("25");
		rightPanel.add(populationSize);
		
		maxGenLabel = new JLabel("Max Generations:");
		rightPanel.add(maxGenLabel);
		
		maxGen = new JTextField();
		maxGen.setPreferredSize(new Dimension(100, 25));
		maxGen.setText("10000");
		rightPanel.add(maxGen);
		
		mutationRateLabel = new JLabel("Mutation Rate:");
		rightPanel.add(mutationRateLabel);
		
		mutationRate = new JTextField();
		mutationRate.setPreferredSize(new Dimension(100, 25));
		mutationRate.setText("0.015");
		rightPanel.add(mutationRate);
		
		addCity = new JButton();
		addCity.setSize(50, 50);
		addCity.setText("Add City");
		addCity.addActionListener(handler);
		rightPanel.add(addCity);
		
		startButton = new JButton();
		startButton.setSize(50, 50);
		startButton.setText("Start");
		startButton.addActionListener(handler);
		rightPanel.add(startButton);

		
		
		
		City city = new City(60, 200);
        TourManager.addCity(city);
        City city2 = new City(180, 200);
        TourManager.addCity(city2);
        City city3 = new City(80, 180);
        TourManager.addCity(city3);
        City city4 = new City(140, 180);
        TourManager.addCity(city4);
        City city5 = new City(20, 160);
        TourManager.addCity(city5);
        City city6 = new City(100, 160);
        TourManager.addCity(city6);
        City city7 = new City(200, 160);
        TourManager.addCity(city7);
        City city8 = new City(140, 140);
        TourManager.addCity(city8);
        City city9 = new City(40, 120);
        TourManager.addCity(city9);
        City city10 = new City(100, 120);
        TourManager.addCity(city10);
        City city11 = new City(180, 100);
        TourManager.addCity(city11);
        City city12 = new City(60, 80);
        TourManager.addCity(city12);
        City city13 = new City(120, 80);
        TourManager.addCity(city13);
        City city14 = new City(180, 60);
        TourManager.addCity(city14);
        City city15 = new City(20, 40);
        TourManager.addCity(city15);
        City city16 = new City(100, 40);
        TourManager.addCity(city16);
        City city17 = new City(200, 40);
        TourManager.addCity(city17);
        City city18 = new City(20, 20);
        TourManager.addCity(city18);
        City city19 = new City(60, 20);
        TourManager.addCity(city19);
        City city20 = new City(160, 20);
        TourManager.addCity(city20);
        
        numOfCitiesLabel = new JLabel("Number of Cities:");
		bottomPanel.add(numOfCitiesLabel);
		
		numOfCitiesValueLabel = new JLabel(TourManager.numberOfCities() + "");
		bottomPanel.add(numOfCitiesValueLabel);
		
		initialDistanceLabel = new JLabel("Initial Tour Distance:");
		bottomPanel.add(initialDistanceLabel);
		
		initialDistanceValueLabel = new JLabel();
		bottomPanel.add(initialDistanceValueLabel);
		
		solutionDistanceLabel = new JLabel("Solution Tour Distance:");
		bottomPanel.add(solutionDistanceLabel);
		
		solutionDistanceValueLabel = new JLabel();
		bottomPanel.add(solutionDistanceValueLabel);
        
        
        Tour initialTour = new Tour();
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
        	initialTour.setCity(i, TourManager.getCity(i));
        }
        cityPanel = new drawStartingCities(initialTour);
        mainPanel.add(cityPanel);
		
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new TSP_GA();
	}
	
	public class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == startButton) {
				Population pop = new Population(Integer.parseInt(populationSize.getText()), true);
				//System.out.println("Initial distance: " + pop.getFittest().getDistance());
				initialDistanceValueLabel.setText(pop.getFittest().getDistance() + "");
				
				GA.setMutationRate(Double.parseDouble(mutationRate.getText()));
				
				pop = GA.evolvePopulation(pop);
				for (int i = 0; i < Integer.parseInt(maxGen.getText()); i ++)
				{
					pop = GA.evolvePopulation(pop);
				}
				
				solutionDistanceValueLabel.setText(pop.getFittest().getDistance() + "");
				
				mainPanel.remove(cityPanel);
				cityPanel = new drawCities(pop.getFittest());

				mainPanel.add(cityPanel);
				mainPanel.validate();
				mainFrame.repaint();
				/*System.out.println("Finished");
				System.out.println("Final distance: " + pop.getFittest().getDistance());
				System.out.println("Solution:");
				System.out.println(pop.getFittest());*/
			}
			
			if (e.getSource() == addCity) {
				int xCoord = 0;
				int yCoord = 0;
				String xCoordString, yCoordString;
				boolean flag = true;
				do {
					try {
						xCoordString = JOptionPane.showInputDialog(null, "x coordinate:");
						xCoord = Integer.parseInt(xCoordString);
						if (xCoord > 200 || xCoord < 0)
							throw new NumberOutOfRangeException();
						flag = true;
					} catch(NumberFormatException err) {
						JOptionPane.showMessageDialog(null, "Please enter a valid integer that is between 0 and 200");
						flag = false;					
					}
					catch(NumberOutOfRangeException err) {
						JOptionPane.showMessageDialog(null, "Please enter an integer that is between 0 and 200");
						flag = false;
					}
				} while (flag == false);
				
				do {
					try {
						yCoordString = JOptionPane.showInputDialog(null, "y coordinate:");
						yCoord = Integer.parseInt(yCoordString);
						if (yCoord > 200 || yCoord < 0)
							throw new NumberOutOfRangeException();
						flag = true;
					} catch(NumberFormatException err) {
						JOptionPane.showMessageDialog(null, "Please enter a valid integer that is between 0 and 200");
						flag = false;					
					}
					catch(NumberOutOfRangeException err) {
						JOptionPane.showMessageDialog(null, "Please enter an integer that is between 0 and 200");
						flag = false;
					}
				} while (flag == false);
				
				City city = new City(xCoord, yCoord);
				TourManager.addCity(city);
				
				Tour initialTour = new Tour();
		        for (int i = 0; i < TourManager.numberOfCities(); i++) {
		        	initialTour.setCity(i, TourManager.getCity(i));
		        }
		        
		        numOfCitiesValueLabel.setText(TourManager.numberOfCities() + "");
		        mainPanel.remove(cityPanel);
		        cityPanel = new drawStartingCities(initialTour);
		        mainPanel.add(cityPanel);
		        mainPanel.validate();
				mainFrame.repaint();
			}
		}
	}
}
