public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Body(Body b){
		this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
	}

	public double calcDistance(Body body){
		double dx = body.xxPos - this.xxPos;
		double dy = body.yyPos - this.yyPos;
		double r_sqr = (dx*dx) + (dy*dy);
		return Math.sqrt(r_sqr);
	}

	public double calcForceExertedBy(Body body){
		double productOfMasses = this.mass*body.mass;
		double gravitational_constant = 6.67e-11;
		double distance = this.calcDistance(body);
		return gravitational_constant*productOfMasses/(distance*distance);
	}

	public double calcForceExertedByX(Body body){
		double dx = body.xxPos - this.xxPos;
		double exertedForce = this.calcForceExertedBy(body);
		double distance = this.calcDistance(body);
		return (exertedForce*dx)/distance;

	}

	public double calcForceExertedByY(Body body){
		double dy = body.yyPos - this.yyPos;
		double exertedForce = this.calcForceExertedBy(body);
		double distance = this.calcDistance(body);
		return (exertedForce*dy)/distance;
	}

	public double calcNetForceExertedByX(Body[] body){
		double netForceX = 0.0;
		for(Body b : body){
			if(b.equals(this)){
				netForceX = netForceX;
			}else
				netForceX = netForceX + this.calcForceExertedByX(b);
			}
		return netForceX;	
	}

	public double calcNetForceExertedByY(Body[] body){
		double netForceY = 0.0;
		for(Body b : body){
			if(b.equals(this)){
				netForceY = netForceY;
			}else
				netForceY = netForceY + this.calcForceExertedByY(b);
			}
		return netForceY;	
	}

	public void update(double dt,double fX,double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;

		double newVelX = this.xxVel + dt * aX;
		double newVelY = this.yyVel + dt * aY;

		double newXPos = this.xxPos + dt * newVelX;
		double newYPos = this.yyPos + dt * newVelY;

		this.xxVel = newVelX;
		this.yyVel = newVelY;

		this.xxPos = newXPos;
		this.yyPos = newYPos;

	}

	public void draw(){
		String imageToDraw = "images/"+this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
	}

}


