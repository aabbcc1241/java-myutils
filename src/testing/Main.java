package testing;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("main start");

		int width = 800;
		int height = 600;
		int scale = 3;
		String title = "my title";
		double nsPerTick = 1e9D / 10D;
		double nsPerRender = 1e9D / 60D;
		CanvasShellTest cs = new CanvasShellTest(width, height, scale, title, nsPerTick,
				nsPerRender);
		cs.start();
		// cs.stop();
		System.out.println("main end");
	}

}
