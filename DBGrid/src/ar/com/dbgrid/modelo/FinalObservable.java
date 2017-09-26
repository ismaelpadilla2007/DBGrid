package ar.com.dbgrid.modelo;

import java.util.Observable;

public class FinalObservable extends Observable {
	
	public synchronized void setChanged(){
		super.setChanged();
	}
}
