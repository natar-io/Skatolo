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
package tech.lity.rea.skatolo.extra;

import tech.lity.rea.skatolo.gui.group.Textarea;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class Println {

	int max = -1;

	final Textarea c;

	String buffer = "";

	boolean paused;


	public Println(Textarea theTextarea) {
		c = theTextarea;
		run();
	}


	public Println setMax(int theMax) {
		max = theMax;
		return this;
	}


	private void run() {
		try {
			final PipedInputStream pi = new PipedInputStream();
			final PipedOutputStream po = new PipedOutputStream(pi);
			System.setOut(new PrintStream(po, true));

			(new Thread() {

				public void run() {
					final byte[] buf = new byte[1024];
					try {
						while (true) {
							final int len = pi.read(buf);
							if (len == -1) {
								break;
							}
							if (!paused) {
								if (!c._myScrollbar.isMousePressed()) {
									c.append(buffer + new String(buf, 0, len), max);
									buffer = "";
									c.scroll(1);
								}
								else {
									buffer += new String(buf, 0, len);
								}
							}
						}
					} catch (IOException e) {
					}
				}
			}).start();
		} catch (IOException e) {
			System.out.println("Problems setting up console");
		}
	}


	public void clear() {
		c.clear();
	}


	public void pause() {
		paused = true;
	}


	public void play() {
		paused = false;
	}

}
