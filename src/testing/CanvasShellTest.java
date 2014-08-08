package testing;

import java.awt.Color;

import myutils.CanvasShell;
import myutils.Colors;

public class CanvasShellTest extends CanvasShell {
	private static final long serialVersionUID = 1L;

	public CanvasShellTest(int width, int height, int scale, String title, double nsPerTick,
			double nsPerRender) {
		super(width, height, scale, title, nsPerTick, nsPerRender);

	}

	@Override
	protected void init() {		
		setBackground(Color.BLACK);
		//pixels[WIDTH/2+HEIGHT/2*WIDTH]=Colors.get(1,0,0);		
	}

	@Override
	protected void myTick() {
		screen.add(WIDTH/2+screen.xOffset,(HEIGHT/2+screen.yOffset)*WIDTH,Colors.get(1,0,0));
	}

	@Override
	protected void myRender() {
		/*for (int y = 0; y < HEIGHT; y++)
			for (int x = 0; x < WIDTH; x++) {
				double r = -1.0 * x / WIDTH ;
				double g = 1.0 * y / HEIGHT;
				double b = 1.0 * (x + y) / (WIDTH + HEIGHT);
				r+=tickCount;
				g+=tickCount;
				b+=tickCount;
				//System.out.println("r:" + r + ", g:" + g + ", b:" + b);
				pixels[x + y * WIDTH] = (int) Colors.get(r, g, b);
				// pixels[x + y * WIDTH] = Colors.get(1,1,1);
			}*/
	}

	@Override
	protected void myDebugInfo() {
				
	}

	@Override
	protected void myKeyHandling() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void myMouseHandling() {
		// TODO Auto-generated method stub
		
	}

}
