package ca.polymtl.ourscureuil;

public class Score {
	static Score instance;
	
	static Score getInstance() {
		if(instance == null) {
			instance = new Score();
		}
		return instance;
	}
	
	private int maxLife;
	private int remainingLife;
	private boolean gameOver;
	
	private Score() {
		maxLife = 5;
		remainingLife = 5;
	}
	
	public void setLife(int count) {
		maxLife = count;
		remainingLife = count;
		gameOver = false;
	}
	
	public void killALife() {
		if(remainingLife > 0)
			remainingLife--;
		else
			gameOver = true;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public int getRemainingLifeCount() {
		// TODO Auto-generated method stub
		return remainingLife;
	}

	public int getTotalLifeCount() {
		// TODO Auto-generated method stub
		return maxLife;
	}
	
	
	
	
}
