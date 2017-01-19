/* 
 *  skatolo is a processing gui library.
 * 
 * Copyright (C)  2017 by RealityTechSASU
 * Copyright (C)  2015-2016 by Jeremy Laviole
 * Copyright (C)  2006-2012 by Andreas Schlegel
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * 
 */
package tech.lity.rea.skatolo.gui.controllers;

import tech.lity.rea.skatolo.gui.CColor;
import java.util.ArrayList;
import java.util.ListIterator;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Used by Chart, a chart data set is a container to store chart data.
 */
@SuppressWarnings("serial") public class ChartDataSet extends ArrayList<ChartData> {

	protected CColor _myColor;

	protected float _myStrokeWeight = 1;

	protected int[] colors = new int[0];

	protected final String _myName;


	public ChartDataSet(String theName) {
		_myName = theName;
		_myColor = new CColor();
	}


	public CColor getColor() {
		return _myColor;
	}


	public ChartDataSet setColors(int... theColors) {
		colors = theColors;
		return this;
	}


	public int[] getColors() {
		return colors;
	}


	public int getColor(int theIndex) {
		if (colors.length == 0) {
			return getColor().getForeground();
		}
		if (colors.length == 2) {
			return PGraphics.lerpColor(colors[0], colors[1], theIndex / (float) size(), PApplet.RGB);
		}
		if (theIndex >= 0 && theIndex < colors.length) {
			return colors[theIndex];
		}
		return getColor(0);
	}


	public ChartDataSet setStrokeWeight(float theStrokeWeight) {
		_myStrokeWeight = theStrokeWeight;
		return this;
	}


	public float getStrokeWeight() {
		return _myStrokeWeight;
	}


	public float[] getValues() {
		float[] v = new float[size()];
		int n = 0;
		ListIterator<ChartData> litr = listIterator();
		while (litr.hasNext()) {
			v[n++] = litr.next().getValue();
		}
		return v;
	}

}
