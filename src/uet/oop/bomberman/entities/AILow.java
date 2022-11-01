package com.carlosflorencio.bomberman.entities.ai;

public class AILow extends AI {

	@Override
	public int calculateDirection() {
		return random.nextInt(4);
	}

}
