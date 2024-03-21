package view;

import controller.ThreadCozinha;

public class Main {

	public static void main(String[] args) {
		
		for(int id = 1; id<6; id++) {
			Thread cozir = new ThreadCozinha(id);
			cozir.start();
		}
	}

}
