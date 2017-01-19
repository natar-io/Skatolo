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
package tech.lity.rea.skatolo.events;

import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.SkatoloConstants;
import tech.lity.rea.skatolo.gui.Controller;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The ControlBroadcaster handles all controller value changes and distributes them accordingly to its listeners. The ControlBroadcaster is
 * primarily for internal use only but can be accessed through an instance of the skatolo class. Instead of accessing the
 * ControlBroadcaster directly, use the convenience methods available from the skatolo class.
 *
 * @see skatolo.skatolo#getControlBroadcaster()
 */
public class ControlBroadcaster {

    	private Skatolo skatolo;

	private int controlEventType = SkatoloConstants.INVALID;

	private ControllerPlug controlEventPlug = null;
	private ControllerPlug controllerCallbackEventPlug = null;

	private String controllerCallbackEventMethod = "controlEvent";

	private ArrayList<ControlListener> controlListeners;
	private Map<CallbackListener, Controller<?>> controllerCallbackListeners;

	private static boolean setPrintStackTrace = true;
	private static boolean ignoreErrorMessage = false;

	private static Map<Class<?>, Field[]> fieldcache = new HashMap<Class<?>, Field[]>();
	private static Map<Class<?>, Method[]> methodcache = new HashMap<Class<?>, Method[]>();

	public boolean broadcast = true;

	public ControlBroadcaster(Skatolo theskatolo) {
		skatolo = theskatolo;
		controlListeners = new ArrayList<ControlListener>();
		controllerCallbackListeners = new ConcurrentHashMap<CallbackListener, Controller<?>>();

                controlEventPlug =            checkObject(skatolo.getObjectForIntrospection(), controllerCallbackEventMethod, new Class[] { ControlEvent.class });
                controllerCallbackEventPlug = checkObject(skatolo.getObjectForIntrospection(), controllerCallbackEventMethod, new Class[] { CallbackEvent.class });

                if (controlEventPlug != null) {
			controlEventType = SkatoloConstants.METHOD;
		}
	}

	public ControlBroadcaster addListener(ControlListener... theListeners) {
		for (ControlListener l : theListeners) {
			controlListeners.add(l);
		}
		return this;
	}

	public ControlBroadcaster removeListener(ControlListener... theListeners) {
		for (ControlListener l : theListeners) {
			controlListeners.remove(l);
		}
		return this;
	}

	/**
	 * Returns a ControlListener by index
	 *
	 * @param theIndex
	 * @return
	 */
	public ControlListener getListener(int theIndex) {
		if (theIndex < 0 || theIndex >= controlListeners.size()) {
			return null;
		}
		return controlListeners.get(theIndex);
	}

	/**
	 * Returns the size of the ControlListener list
	 *
	 * @return
	 */
	public int listenerSize() {
		return controlListeners.size();
	}

	public ControlBroadcaster addCallback(CallbackListener... theListeners) {
		for (CallbackListener l : theListeners) {
			controllerCallbackListeners.put(l, new EmptyController());
		}
		return this;
	}

	public ControlBroadcaster addCallback(CallbackListener theListener) {
		controllerCallbackListeners.put(theListener, new EmptyController());
		return this;
	}

	/**
	 * Adds a CallbackListener for a list of controllers.
	 *
	 * @param theListener
	 * @param theController
	 */
	public void addCallback(CallbackListener theListener, Controller<?>... theController) {
		for (Controller<?> c : theController) {
			controllerCallbackListeners.put(theListener, c);
		}
	}

	public ControlBroadcaster removeCallback(CallbackListener... theListeners) {
		for (CallbackListener c : theListeners) {
			controllerCallbackListeners.remove(c);
		}
		return this;
	}

	public ControlBroadcaster removeCallback(CallbackListener theListener) {
		controllerCallbackListeners.remove(theListener);
		return this;
	}

	/**
	 * Removes a CallbackListener for a particular controller
	 *
	 * @param theController
	 */
	public ControlBroadcaster removeCallback(Controller<?>... theControllers) {
		for (Controller<?> c : theControllers) {
			for (Map.Entry<CallbackListener, Controller<?>> entry : controllerCallbackListeners.entrySet()) {
				if (c != null && entry.getValue().equals(c)) {
					controllerCallbackListeners.remove(entry.getKey());
				}
			}
		}
		return this;
	}

	public ControlBroadcaster plug(Object theObject, final String theControllerName, final String theTargetMethod) {
		plug(theObject, skatolo.getController(theControllerName), theTargetMethod);
		return this;
	}

	public ControlBroadcaster plug(Object theObject, final Controller<?> theController, final String theTargetMethod) {
		if (theController != null) {
			ControllerPlug myControllerPlug = checkObject(theObject, theTargetMethod, SkatoloConstants.acceptClassList);
			if (myControllerPlug == null) {
				return this;
			} else {
				if (theController.checkControllerPlug(myControllerPlug) == false) {
					theController.addControllerPlug(myControllerPlug);
				}
				return this;
			}
		}
		return this;
	}

	static Field[] getFieldsFor(Class<?> theClass) {
		if (!fieldcache.containsKey(theClass)) {
			fieldcache.put(theClass, theClass.getDeclaredFields());
		}
		return fieldcache.get(theClass);
	}

	static Method[] getMethodFor(Class<?> theClass) {
		if (!methodcache.containsKey(theClass)) {
			methodcache.put(theClass, theClass.getDeclaredMethods());
		}
		return methodcache.get(theClass);
	}

	protected static ControllerPlug checkObject(final Object theObject, final String theTargetName, final Class<?>[] theAcceptClassList) {

		Class<?> myClass = theObject.getClass();

		Method[] myMethods = getMethodFor(myClass);

		for (int i = 0; i < myMethods.length; i++) {
			if ((myMethods[i].getName()).equals(theTargetName)) {

				if (myMethods[i].getParameterTypes().length == 1) {

					// hack to detect controlEvent(CallbackEvent) which is otherwise
					// overwritten by controlEvent(ControlEvent)
					if (theAcceptClassList.length == 1) {
						if (theAcceptClassList[0] == CallbackEvent.class) {
							ControllerPlug cp = new ControllerPlug(CallbackEvent.class, theObject, theTargetName, SkatoloConstants.EVENT, -1);
							if (cp.getMethod() == null) {
								return null;
							}
							return cp;
						}
					}
					if (myMethods[i].getParameterTypes()[0] == SkatoloConstants.controlEventClass) {
						return new ControllerPlug(ControlEvent.class, theObject, theTargetName, SkatoloConstants.EVENT, -1);
					}
					for (int j = 0; j < theAcceptClassList.length; j++) {
						if (myMethods[i].getParameterTypes()[0] == theAcceptClassList[j]) {
							return new ControllerPlug(theObject, theTargetName, SkatoloConstants.METHOD, j, theAcceptClassList);
						}
					}
				} else if (myMethods[i].getParameterTypes().length == 0) {
					return new ControllerPlug(theObject, theTargetName, SkatoloConstants.METHOD, -1, theAcceptClassList);
				}
				break;
			}
		}

		Field[] myFields = getFieldsFor(myClass);

		for (int i = 0; i < myFields.length; i++) {
			if ((myFields[i].getName()).equals(theTargetName)) {

				for (int j = 0; j < theAcceptClassList.length; j++) {
					if (myFields[i].getType() == theAcceptClassList[j]) {
						return new ControllerPlug(theObject, theTargetName, SkatoloConstants.FIELD, j, theAcceptClassList);
					}
				}
				break;
			}
		}
		return null;
	}

	public ControlBroadcaster broadcast(final ControlEvent theControlEvent, final int theType) {
            if (broadcast) {
                for (ControlListener cl : controlListeners) {
                    cl.controlEvent(theControlEvent);
                }

                boolean called = false;
                if (theControlEvent.isTab() == false && theControlEvent.isGroup() == false) {
                    if (theControlEvent.getController().getControllerPlugList().size() > 0) {

                        if (theType == SkatoloConstants.STRING) {
                            for (ControllerPlug cp : theControlEvent.getController().getControllerPlugList()) {
                                callTarget(cp, theControlEvent.getStringValue());
                            }
                        } else if (theType == SkatoloConstants.ARRAY) {

                        } else {
                            for (ControllerPlug cp : theControlEvent.getController().getControllerPlugList()) {
                                if (cp.checkType(SkatoloConstants.EVENT)) {
                                    invokeMethod(cp.getObject(), cp.getMethod(), new Object[] { theControlEvent });
                                    called = true;
                                } else {
                                    callTarget(cp, theControlEvent.getValue());
                                }
                            }
                        }
                    }
                }
                if(called)
                    return this;
                if (controlEventType == SkatoloConstants.METHOD) {
                    invokeMethod(controlEventPlug.getObject(), controlEventPlug.getMethod(), new Object[] { theControlEvent });
                }
            }
            return this;
	}

	protected void callTarget(final ControllerPlug thePlug, final float theValue) {
		if (thePlug.checkType(SkatoloConstants.METHOD)) {

			invokeMethod(thePlug.getObject(), thePlug.getMethod(), thePlug.getMethodParameter(theValue));
		} else if (thePlug.checkType(SkatoloConstants.FIELD)) {
			invokeField(thePlug.getObject(), thePlug.getField(), thePlug.getFieldParameter(theValue));
		}
	}

	protected void callTarget(final ControllerPlug thePlug, final String theValue) {
		if (thePlug.checkType(SkatoloConstants.METHOD)) {
			invokeMethod(thePlug.getObject(), thePlug.getMethod(), new Object[] { theValue });
		} else if (thePlug.checkType(SkatoloConstants.FIELD)) {
			invokeField(thePlug.getObject(), thePlug.getField(), theValue);
		}
	}

	private void invokeField(final Object theObject, final Field theField, final Object theParam) {
		try {
			theField.set(theObject, theParam);
		} catch (IllegalAccessException e) {
			Skatolo.logger().warning(e.toString());
		}
	}

	private void invokeMethod(final Object theObject, final Method theMethod, final Object[] theParam) {
		try {
			if (theParam[0] == null) {
				theMethod.invoke(theObject, new Object[0]);
			} else {
				theMethod.invoke(theObject, theParam);
			}
		} catch (IllegalArgumentException e) {
			Skatolo.logger().warning(e.toString());
			/**
			 * TODO thrown when plugging a String method/field.
			 */
		} catch (IllegalAccessException e) {
			printMethodError(theMethod, e);
		} catch (InvocationTargetException e) {
			printMethodError(theMethod, e);
		} catch (NullPointerException e) {
			printMethodError(theMethod, e);
		}

	}

	public String getEventMethod() {
		return controllerCallbackEventMethod;
	}

	public void invokeAction(CallbackEvent theEvent) {
		boolean invoke;
		for (Map.Entry<CallbackListener, Controller<?>> entry : controllerCallbackListeners.entrySet()) {
			invoke = (entry.getValue().getClass().equals(EmptyController.class)) ? true : (entry.getValue().equals(theEvent.getController())) ? true : false;
			if (invoke) {
				entry.getKey().controlEvent(theEvent);
			}
		}

		if (controllerCallbackEventPlug != null) {
			invokeMethod(skatolo.getObjectForIntrospection(), controllerCallbackEventPlug.getMethod(), new Object[] { theEvent });
		}
	}

	private void printMethodError(Method theMethod, Exception theException) {
		if (!ignoreErrorMessage) {
			Skatolo.logger().severe(
					"An error occured while forwarding a Controller event, please check your code at " + theMethod.getName() + (!setPrintStackTrace ? " " + "exception:  " + theException : ""));
			if (setPrintStackTrace) {
				theException.printStackTrace();
			}
		}
	}

	public static void ignoreErrorMessage(boolean theFlag) {
		ignoreErrorMessage = theFlag;
	}

	public static void setPrintStackTrace(boolean theFlag) {
		setPrintStackTrace = theFlag;
	}

	private class EmptyController extends Controller<EmptyController> {

		protected EmptyController() {
			this(0, 0);
		}

		protected EmptyController(int theX, int theY) {
			super("empty" + ((int) (Math.random() * 1000000)), theX, theY);
			// TODO Auto-generated constructor stub
		}

		@Override public EmptyController setValue(float theValue) {
			// TODO Auto-generated method stub
			return this;
		}

	}

	/**
	 * @exclude
	 */
	@Deprecated public void plug(final String theControllerName, final String theTargetMethod) {
		plug(skatolo.getObjectForIntrospection(), theControllerName, theTargetMethod);
	}
}
