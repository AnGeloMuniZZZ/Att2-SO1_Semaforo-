package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {

	private int id;
	private Semaphore semaforo = new Semaphore(1);
	private static int tempo;
	private int resto = 0;
	private int percentual;

	public ThreadCozinha(int id) {
		this.id = id;
	}

	@Override
	public void run() {

		switch (id % 2) {
		case (1):
			Sopa();
			try {
				semaforo.acquire();
				Entrega();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			break;
		case (0):
			Lasanha();
			try {
				semaforo.acquire();
				Entrega();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			break;
		}
	}

	private void Sopa() {
		System.out.println("Começando a fazer o " + id + "° prato, Sopa de Cebola");
		tempo = (int) ((Math.random() * 301) + 500);
		while (resto < tempo) {
			try {
				sleep(100);
				percentual = (resto / tempo * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("O prato n° " + id + " está " + percentual + "% cozido");
			resto = resto + 100;
		}
		System.out.println("O prato nº " + id + " está pronto, indo para entrega");
	}

	private void Lasanha() {
		System.out.println("Começando a fazer o " + id + "° prato, Lasanha a Bolonhesa");
		tempo = (int) ((Math.random() * 601) + 600);
		while (resto < tempo) {
			try {
				sleep(100);
				percentual = ((resto / tempo) * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("O prato n° " + id + " está " + percentual + "% cozido");
			resto += 100;
		}
		System.out.println("O prato nº " + id + " está pronto, indo para entrega");
	}

	private void Entrega() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O " + id + "° prato foi entregue");
	}

}
