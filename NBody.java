public class NBody{

	public static double readRadius(String path){
		In in = new In(path);

		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String path){
			In in = new In(path);
			in.readLine();
			in.readLine();
			Body [] bodies = new Body[5];
			int i = 0;
			while(i < 5){
				double xxPos = in.readDouble();
				double yyPos = in.readDouble();
				double xxVel = in.readDouble();
				double yyVel = in.readDouble();
				double mass = in.readDouble();
				String imgFileName = in.readString(); 
				bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
				i++;
			}

			return bodies;

}

	public static void main (String [] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double universeRadius = readRadius(fileName);
		Body [] bodies = readBodies(fileName);

		StdDraw.setScale(-(universeRadius), universeRadius);
		StdDraw.clear();
		String imageToDraw = "images/starfield.jpg";
		StdDraw.picture(0, 0, imageToDraw);

		for (Body b : bodies){
			b.draw();
		}

		StdDraw.show();
		StdDraw.enableDoubleBuffering();

		
		for(double time = 0.0; time<=T; time = time+dt){
			double[] xForces = new double[5];
			double[] yForces = new double[5];

			for(int i = 0; i < 5; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int i = 0; i < 5; i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
			}

		StdDraw.picture(0, 0, imageToDraw);
		for (Body b : bodies) {
			b.draw();
			}
		StdDraw.show();

		}


	}

}