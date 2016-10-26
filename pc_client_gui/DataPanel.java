package pc_client_gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DataPanel extends JPanel
{
	private float xAxis;
	private float yAxis;
	private float zAxis;
	
	private JLabel xAxisLabel;
	private JLabel yAxisLabel;
	private JLabel zAxisLabel;
	
	public DataPanel()
	{
		xAxis = 0;
		yAxis = 0;
		zAxis = 0;
		
		xAxisLabel = new JLabel("Value of xAxis: " + String.valueOf(xAxis));
		yAxisLabel = new JLabel("Value of yAxis: " + String.valueOf(yAxis));
		zAxisLabel = new JLabel("Value of zAxis: " + String.valueOf(zAxis));
		
		this.setLayout(new GridLayout(3,1));
		
		setTextSize(xAxisLabel, 30);
		setTextSize(yAxisLabel, 30);
		setTextSize(zAxisLabel, 30);
		
		this.add(xAxisLabel);
		this.add(yAxisLabel);
		this.add(zAxisLabel);
	}
	
	/**
	 * Sets labels to the correct font
	 * <p>
	 * @param label - label that gets changed
	 * @param size - value that sets the correct letter size
	 */
	private void setTextSize(JLabel label, int size)
	{
		label.setFont(new Font("Arial", Font.PLAIN ,size));
		label.setHorizontalAlignment(JLabel.CENTER);
	}
	
	/**
	 * Function updates panel in order to show new values
	 */
	private void update()
	{
		xAxisLabel.setText("Value of xAxis: " + String.valueOf(xAxis));
		yAxisLabel.setText("Value of yAxis: " + String.valueOf(yAxis));
		zAxisLabel.setText("Value of zAxis: " + String.valueOf(zAxis));
	}
	
	/**
	 * Updates the three values of the gyroscope
	 * <p>
	 * @param x - xAxis
	 * @param y - yAxis
	 * @param z - zAxis
	 */
	public void changeAxisValues(float x, float y, float z)
	{
		xAxis = x;
		yAxis = y;
		zAxis = z;
		update();
	}
}
