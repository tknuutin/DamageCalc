package core;

import java.awt.Font;

import javax.swing.JSlider;

/**
 * Custom JSlider wrapper for layout purposes.
 * 
 * @author Tarmo
 *
 */
class CustomSlider extends JSlider{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2615583104373826601L;

	/**
	 * Create the slider from three attributes.
	 * 
	 * @param min - leftmost position of the slider.
	 * @param max - rightmost position.
	 * @param init - initial position.
	 */
	CustomSlider(int min, int max, int init){
		super(min, max, init);
		setMajorTickSpacing(4);
		setMinorTickSpacing(1);
		setPaintTicks(true);
		setPaintLabels(true);
		setSnapToTicks(true);
		setFont(new Font("Arial Bold", Font.PLAIN, 10));
	}

}
