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
package tech.lity.rea.skatolo.gui.group;

import tech.lity.rea.skatolo.events.ControlEvent;
import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.SkatoloConstants;
import tech.lity.rea.skatolo.gui.ControlWindow;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * Tabs are used to organize controllers. Tabs are arranged horizontally from the top-left corner by
 * default, Tab extends ControllerGroup, for more available methods see the ControllerGroup
 * documentation. Reposition tabs with {@link skatolo.ControlWindow#setPositionOfTabs(int, int)}
 * 
 * @example controllers/skatolotab
 * @nosuperclasses ControllerGroup ControllerGroup
 */
public class Tab extends ControllerGroup<Tab> {

	protected int _myOffsetX = -1000;

	protected int _myOffsetY = -1000;

	public boolean isActive = false;

	private boolean isAlwaysActive = false;

	protected boolean isEventActive = false;

	private float _myValue = 0;

	protected String _myStringValue = "";

	public static int padding = 4;

	public boolean autoWidth = true;

	/**
	 * 
	 * @param theSkatolo skatolo
	 * @param theControlWindow ControlWindow
	 * @param theName String
	 */
	public Tab(Skatolo theskatolo, ControlWindow theControlWindow, String theName) {
		super(theskatolo, null, theName, 0, 0);
		position = new PVector();
		absolutePosition = new PVector();
		isMoveable = false;
		isEventActive = theskatolo.isTabEventsActive;
		_myHeight = 16;
		_myWidth = _myLabel.getWidth() + padding * 2;
		_myLabel.align(LEFT, CENTER).setPadding(0, 0);
	}

	public void setOffset(int theValueX, int theValueY) {
		_myOffsetX = theValueX;
		_myOffsetY = theValueY;
	}

	public int height() {
		return _myHeight;
	}

	public boolean updateLabel() {
		isInside = inside();
		return skatolo.getWindow().getTabs().size() > 2;
	}
        
	public void drawLabel(PGraphics graphics) {
		if (autoWidth) {
			_myWidth = _myLabel.getWidth() + padding * 2;
		}
		graphics.pushMatrix();
		graphics.fill(isInside ? this.getColor().getForeground() : this.getColor().getBackground());
		if (isActive) {
			graphics.fill(this.getColor().getActive());
		}
		graphics.translate(_myOffsetX, _myOffsetY);
		graphics.rect(0, 0, _myWidth - 1, _myHeight);
		_myLabel.draw(graphics, padding, 0, this);
		graphics.popMatrix();
	}

	/**
	 * set the label of the group. TODO overwriting COntrollerGroup.setLabel to set the Width of a
	 * tab after renaming. this should be temporary and fixed in the future.
	 * 
	 * @param theLabel String
	 * @return Tab
	 */
	public Tab setLabel(String theLabel) {
		_myLabel.set(theLabel);
		return this;
	}

	public int width() {
		return _myWidth;
	}

	/**
	 * @param theWidth
	 * @return
	 */
	public Tab setWidth(int theWidth) {
		_myWidth = theWidth + padding;
		autoWidth = false;
		return this;
	}

	public Tab setHeight(int theHeight) {
		_myHeight = theHeight;
		return this;
	}

	protected boolean inside() {
		return (skatolo.getWindow().getPointerX() > _myOffsetX && skatolo.getWindow().getPointerX() < _myOffsetX + _myWidth && skatolo.getWindow().getPointerY() > _myOffsetY && skatolo.getWindow().getPointerY() < _myOffsetY
				+ _myHeight);
	}

	/**
	 * {@inheritDoc}
	 */
	public void mousePressed() {
		skatolo.getWindow().activateTab(this);
		if (isEventActive) {
			skatolo.getControlBroadcaster().broadcast(new ControlEvent(this), SkatoloConstants.METHOD);
		}
	}

	/**
	 * Activates a tab.
	 * 
	 * @param theFlag boolean
	 */
	public Tab setActive(boolean theFlag) {
		isActive = theFlag;
		return this;
	}

	public Tab setAlwaysActive(boolean theFlag) {
		isAlwaysActive = theFlag;
		return this;
	}

	/**
	 * checks if a tab is active.
	 * 
	 * @return boolean
	 */
	public boolean isActive() {
		return isAlwaysActive ? true : isActive;
	}

	public boolean isAlwaysActive() {
		return isAlwaysActive;
	}

	@Override
	public Tab bringToFront() {
		skatolo.getWindow().activateTab(this);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tab moveTo(ControlWindow theWindow) {
		skatolo.getWindow().removeTab(this);
		setTab(theWindow, getName());
		return this;
	}

	/**
	 * activates or deactivates the Event status of a tab, When activated a tab will send a
	 * controlEvent to the main application. By default this is disabled.
	 * 
	 * @param theFlag boolean
	 * @return Tab
	 */
	public Tab activateEvent(boolean theFlag) {
		isEventActive = theFlag;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStringValue() {
		return _myStringValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getValue() {
		return _myValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tab setValue(float theValue) {
		_myValue = theValue;
		return this;
	}

	@Deprecated
	public float value() {
		return _myValue;
	}

	@Deprecated
	public String stringValue() {
		return _myStringValue;
	}

}
