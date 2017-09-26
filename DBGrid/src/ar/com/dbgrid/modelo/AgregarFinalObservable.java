package ar.com.dbgrid.modelo;

import java.util.Observable;

public class AgregarFinalObservable extends Observable {

	public synchronized void setChanged(){
		super.setChanged();
	}
}
