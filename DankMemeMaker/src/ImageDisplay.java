import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageDisplay extends JLabel {

	private BufferedImage loadedImage;
	private BufferedImage propImage;
	
	public ImageDisplay() {
		super();
		
	}
	
	public void setImage(BufferedImage img) {
		loadedImage = img;
		
		int imgWidth = loadedImage.getWidth();
		int imgHeight = loadedImage.getHeight();
		int width = this.getWidth();
		int height = this.getHeight();
		
		double wratio = width / (0.0 + imgWidth);
		double hratio = 1;
		
		int dx1 = 0;
		int dy1 = 0;
		int dx2 = width;
		int dy2 = height;
		
		int sx1 = 0;
		int sy1 = 0;
		int sx2 = imgWidth;
		int sy2 = imgHeight;
		
		propImage = new BufferedImage(width, height, loadedImage.getType());
		
		propImage.createGraphics().drawImage(loadedImage, 0, 0, width, height, null);
		
		this.setIcon(new ImageIcon(propImage));
	}
	
	
}
