import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class DankMeme implements Meme {
	
	public String phrase;
	public BufferedImage image;
	public final BufferedImage outputImage;
	
	public DankMeme(BufferedImage pic, String phrse) {
		this.phrase = phrse;
		this.image = pic;
		
		int width = image.getWidth();
		int height = image.getHeight();
		int newPicHeight = height + (height / 2);
		
		this.outputImage = new BufferedImage(width, newPicHeight, image.getType());
		
		Graphics2D g2 = outputImage.createGraphics();
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, newPicHeight);
		
		g2.drawImage(image, 0, 0, width, height, null);
		
		g2.setFont(this.scaleFont(phrase, new Rectangle(0, height, width, height / 2), g2));
		
		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(phrase, g2);
		int x = (width - (int) r.getWidth()) / 2;
		int y = (height / 2 - (int) r.getHeight()) / 2 + fm.getAscent();
		
		g2.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2.setColor(Color.BLACK);
		g2.drawString(phrase, 0, height + fm.getAscent());
		
		g2.dispose();
	}
	
	private Font scaleFont(String text, Rectangle rect, Graphics g) {
	    float fontSize = 20.0f;
	    Font font;

	    font = g.getFont().deriveFont(fontSize);
	    int width = g.getFontMetrics(font).stringWidth(text);
	    fontSize = (rect.width / width ) * fontSize;
	    return g.getFont().deriveFont(fontSize);
	}
	
	public BufferedImage getMeme(){
		
		
		return outputImage;
		
	}

}