package controller.common;

import java.io.Serializable;

import model.Data.Level;

public class common implements Serializable {

	private static final long serialVersionUID = 1L;
	public Level level;

	public char[][] makestring(Level level) {
		return level.makestring();
	}
}