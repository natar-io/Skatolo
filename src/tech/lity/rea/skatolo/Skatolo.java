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
package tech.lity.rea.skatolo;

import tech.lity.rea.skatolo.events.CallbackListener;
import tech.lity.rea.skatolo.gui.ControllerInterface;
import tech.lity.rea.skatolo.gui.Tooltip;
import tech.lity.rea.skatolo.events.FieldChangedListener;
import tech.lity.rea.skatolo.gui.CColor;
import tech.lity.rea.skatolo.file.ControllerProperty;
import tech.lity.rea.skatolo.events.ControlListener;
import tech.lity.rea.skatolo.events.ControlBroadcaster;
import tech.lity.rea.skatolo.gui.Canvas;
import tech.lity.rea.skatolo.gui.ControlWindow;
import tech.lity.rea.skatolo.gui.Controller;
import tech.lity.rea.skatolo.gui.Pointer;
import tech.lity.rea.skatolo.gui.PointerList;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.controllers.Textfield;
import tech.lity.rea.skatolo.gui.group.Tab;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.logging.Level;
import processing.core.PGraphics;

/**
 * <p>
 * skatolo is a processing and java library for creating simple control GUIs.
 * The skatolo class, the core of skatolo.
 * </p>
 * <p>
 * All addController-Methods are located inside the skatoloBase class.
 * </p>
 *
 * @see skatolo.skatoloBase
 * @example use/skatolobasics
 */
public class Skatolo extends SkatoloBase {

    /**
     * @exclude
     */
    public ControlWindow controlWindow;

    public final static CColor RETRO = new CColor(0xff00698c, 0xff003652, 0xff08a2cf, 0xffffffff, 0xffffffff);
    public final static CColor skatoloBLUE = new CColor(0xff016c9e, 0xff02344d, 0xff00b4ea, 0xffffffff, 0xffffffff);
    public final static CColor RED = new CColor(0xffaa0000, 0xff660000, 0xffff0000, 0xffffffff, 0xffffffff);
    public final static CColor WHITE = new CColor(0x99ffffff, 0x55ffffff, 0xffffffff, 0xffffffff, 0xffffffff);

    // For Applet mode (in web Browser). 
    public static boolean isApplet = false;

    public static CColor DEFAULT_COLOR = new CColor(skatoloBLUE);
    public CColor color = DEFAULT_COLOR;

    /**
     * @exclude
     */
    private final PApplet papplet;
    private PGraphics graphics;
    private Object objectForIntrospection = null;

    public PApplet getPApplet() {
        return this.papplet;
    }

    public PGraphics getGraphics() {
        return this.graphics;
    }

    public Object getObjectForIntrospection() {
        return this.objectForIntrospection;
    }

    /**
     * @exclude
     */
    public PGraphics pgraphics;

    public static final String VERSION = "2.1.1";// "##version##";

    public static final int standard58 = 0;
    public static final int standard56 = 1;

    public static final int synt24 = 2;
    public static final int grixel = 3;

    public final static int J2D = 1;
    public final static int P2D = 2;
    public final static int P3D = 3;

    static int renderer = J2D;

    /**
     * use this static variable to turn DEBUG on or off.
     */
    public static boolean DEBUG;

    /**
     * @exclude
     */
    public static final Logger logger = Logger.getLogger(Skatolo.class.getName());

    private Map<String, ControllerInterface<?>> controllerMap;
    private ControlBroadcaster controlBroadcaster;
    protected ControlWindow window;
    protected Tooltip tooltip;

    public boolean isMoveable = false;
    public boolean isAutoInitialization = false;
    protected boolean isGlobalControllersAlwaysVisible = true;
    public boolean isTabEventsActive;
    protected boolean isUpdate;

    static protected final PFont BitFontStandard56 = new BitFont(Hacks.decodeBase64(BitFont.standard56base64));
    static protected final PFont BitFontStandard58 = new BitFont(Hacks.decodeBase64(BitFont.standard58base64));

    public ControlFont defaultFont = new ControlFont(BitFontStandard58);
    public ControlFont defaultFontForText = new ControlFont(BitFontStandard56);

    protected boolean isControlFont;
    public ControlFont controlFont = defaultFont;

    /**
     * from version 0.7.2 onwards shortcuts are disabled by default. shortcuts
     * can be enabled using skatolo.enableShortcuts();
     *
     * @see #enableShortcuts()
     */
    protected boolean shortcutsEnabled = false;

    public boolean isAndroid = false;

    /**
     * Create a new instance of skatolo.
     *
     * @param theParent PApplet
     */
    public Skatolo(final PApplet theParent) {
        papplet = theParent;
        init();
    }

    /**
     * Create a new instance of skatolo.
     *
     * @param theParent PApplet
     */
    public Skatolo(final PApplet theParent, PFont thePFont) {
        this.papplet = theParent;
        init();
        setFont(thePFont);
    }

    public Skatolo(final PApplet theParent, ControlFont theControlFont) {
        this.papplet = theParent;
        init();
        setFont(theControlFont);
    }

    public Skatolo(final PApplet theParent, final Object objForIntrospection) {
        this.papplet = theParent;
        this.objectForIntrospection = objForIntrospection;
        init();
    }

    public Skatolo(final PApplet theParent, final Object objForIntrospection, PFont thePFont) {
        papplet = theParent;
        this.objectForIntrospection = objForIntrospection;
        init();
        setFont(thePFont);
    }

    public Skatolo(final PApplet theParent, final Object objForIntrospection, ControlFont theControlFont) {
        papplet = theParent;
        this.objectForIntrospection = objForIntrospection;
        init();
        setFont(theControlFont);
    }

    private void checkIfAndroid() {
        Class<?> check = papplet.getClass();
        while (check != null) {
            check = check.getSuperclass();
            if (check != null) {
                if (check.toString().toLowerCase().indexOf("android.app.") > -1) {
                    isAndroid = true;
                    break;
                }
            }
        }
    }

    private void checkGraphics() {
        try {
            if (this.objectForIntrospection == this.papplet) {
                this.graphics = papplet.g;
                return;
            }

            Method m = this.objectForIntrospection.getClass().getMethod("getGraphics");
            this.graphics = (PGraphics) m.invoke(objectForIntrospection);
            System.out.println("Graphics Found !" + this.graphics);

        } catch (NoSuchMethodException noMethod) {
            this.graphics = papplet.g;
            System.out.println("No Method");

        } catch (IllegalAccessException illegal) {
            logger.info("Illegal access to getGraphics(). ");
            System.out.println("Illegal access");
            this.graphics = papplet.g;

        } catch (IllegalArgumentException illegalArgement) {
            logger.info("Illegal argument in getGraphics(). ");
            System.out.println("Illegal argument");
            this.graphics = papplet.g;

        } catch (InvocationTargetException invocationTarget) {
            logger.info("Invocation targetException for getGraphics(). ");
            System.out.println("Invocation targetException for getGraphics(). ");
            this.graphics = papplet.g;
        }
    }

    protected void init() {
        if (this.objectForIntrospection == null) {
            this.objectForIntrospection = papplet;
            System.out.println("No object for introspection, we use PApplet.");
        }
        checkGraphics();

        renderer = (papplet.g.getClass().getCanonicalName().indexOf("Java2D") > -1) ? J2D : P3D;
        checkIfAndroid();
        isTabEventsActive = false;

        controlBroadcaster = new ControlBroadcaster(this);
        controlWindow = new ControlWindow(this, papplet);
        papplet.registerMethod("pre", this);
        papplet.registerMethod("dispose", this);
        controllerMap = new TreeMap<String, ControllerInterface<?>>();

        checkWelcomeMessage();
        tooltip = new Tooltip(this);
        super.init(this);

        setFont(defaultFont);
        loadDefaultKeyboardShortcuts();
        disableShortcuts();
    }

    private void loadDefaultKeyboardShortcuts() {
        mapKeyFor(new ControlKey() {
            public void keyEvent() {
                saveProperties();
            }
        }, PApplet.ALT, PApplet.SHIFT, 's');

        mapKeyFor(new ControlKey() {
            public void keyEvent() {
                loadProperties();
            }
        }, PApplet.ALT, PApplet.SHIFT, 'l');

        mapKeyFor(new ControlKey() {
            public void keyEvent() {
                if (controlWindow.isVisible()) {
                    hide();
                } else {
                    show();
                }
            }
        }, PApplet.ALT, PApplet.SHIFT, 'h');
    }

    void checkWelcomeMessage() {
        if (welcome++ < 1) {
            welcome();
        }
    }
    static int welcome = 0;

    private void welcome() {
        System.out.println("skatolo " + VERSION + " " + "infos, comments, questions at https://github.com/rea-lity-tech/skatolo");
    }

    /**
     * By default event originating from tabs are disabled, use
     * setTabEventsActive(true) to receive controlEvents when tabs are clicked.
     *
     * @param theFlag
     */
    public void setTabEventsActive(boolean theFlag) {
        isTabEventsActive = theFlag;
    }

    /**
     * autoInitialization can be very handy when it comes to initializing
     * values, e.g. you load a set of controllers, then the values that are
     * attached to the controllers will be reset to its saved state. to turn of
     * auto intialization, call setAutoInitialization(false) right after
     * initializing skatolo and before creating any controller.
     *
     * @param theFlag boolean
     */
    public void setAutoInitialization(boolean theFlag) {
        isAutoInitialization = theFlag;
    }

    /**
     * by default skatolo draws any controller on top of any drawing done in
     * the draw() function (this doesnt apply to P3D where skatolo.draw() has
     * to be called manually in the sketch's draw() function ). to turn off the
     * auto drawing of skatolo, use skatolo.setAutoDraw(false). now you can
     * call skatolo.draw() any time whenever controllers should be drawn into
     * the sketch.
     *
     * @param theFlag boolean
     */
    public void setAutoDraw(boolean theFlag) {
        if (isAutoDraw() && theFlag == false) {
            controlWindow.papplet().unregisterMethod("draw", controlWindow);
        }
        if (isAutoDraw() == false && theFlag == true) {
            controlWindow.papplet().registerMethod("draw", controlWindow);
        }
        controlWindow.setAutoDraw(theFlag);
    }

    /**
     * check if the autoDraw function for the main window is enabled(true) or
     * disabled(false).
     *
     * @return boolean
     */
    public boolean isAutoDraw() {
        return controlWindow.isAutoDraw();
    }

    /**
     *
     * @see skatolo.ControlBroadcaster
     */
    public ControlBroadcaster getControlBroadcaster() {
        return controlBroadcaster;
    }

    /**
     * @see skatolo.ControlListener
     */
    public Skatolo addListener(ControlListener... theListeners) {
        getControlBroadcaster().addListener(theListeners);
        return this;
    }

    /**
     * @see skatolo.ControlListener
     */
    public Skatolo removeListener(ControlListener... theListeners) {
        getControlBroadcaster().removeListener(theListeners);
        return this;
    }

    /**
     * @see skatolo.ControlListener
     */
    public Skatolo removeListener(ControlListener theListener) {
        getControlBroadcaster().removeListener(theListener);
        return this;
    }

    /**
     * @see skatolo.ControlListener
     */
    public ControlListener getListener(int theIndex) {
        return getControlBroadcaster().getListener(theIndex);
    }

    /**
     * @see skatolo.CallbackEvent
     * @see skatolo.CallbackListener
     */
    public Skatolo addCallback(CallbackListener... theListeners) {
        getControlBroadcaster().addCallback(theListeners);
        return this;
    }

    /**
     * @see skatolo.CallbackEvent
     * @see skatolo.CallbackListener
     */
    public Skatolo addCallback(CallbackListener theListener) {
        getControlBroadcaster().addCallback(theListener);
        return this;
    }

    /**
     * @see skatolo.CallbackEvent
     * @see skatolo.CallbackListener
     */
    public Skatolo addCallback(CallbackListener theListener, Controller<?>... theControllers) {
        getControlBroadcaster().addCallback(theListener, theControllers);
        return this;
    }

    /**
     * @see skatolo.CallbackEvent
     * @see skatolo.CallbackListener
     */
    public Skatolo removeCallback(CallbackListener... theListeners) {
        getControlBroadcaster().removeCallback(theListeners);
        return this;
    }

    /**
     * @see skatolo.CallbackEvent
     * @see skatolo.CallbackListener
     */
    public Skatolo removeCallback(Controller<?>... theControllers) {
        getControlBroadcaster().removeCallback(theControllers);
        return this;
    }

    /**
     * @see skatolo.CallbackEvent
     * @see skatolo.CallbackListener
     */
    public Skatolo removeCallback(Controller<?> theController) {
        getControlBroadcaster().removeCallback(theController);
        return this;
    }

    /**
     * TODO
     *
     * @exclude
     * @param theObject
     */
    public void addControlsFor(Object theObject) {

    }

    public Tab getTab(String theName) {
        for (int i = 0; i < controlWindow.getTabs().size(); i++) {
            if (((Tab) controlWindow.getTabs().get(i)).getName().equals(theName)) {
                return (Tab) controlWindow.getTabs().get(i);
            }
        }
        Tab myTab = addTab(theName);
        return myTab;
    }

    public Tab getTab(ControlWindow theWindow, String theName) {
        for (int i = 0; i < theWindow.getTabs().size(); i++) {
            if (((Tab) theWindow.getTabs().get(i)).getName().equals(theName)) {
                return (Tab) theWindow.getTabs().get(i);
            }
        }
        Tab myTab = theWindow.add(new Tab(this, theWindow, theName));
        return myTab;
    }

    /**
     * registers a Controller with skatolo, a Controller should/must be
     * registered with a unique name. If not, accessing Controllers by name is
     * not guaranteed. the rule here is last come last serve, existing
     * Controllers with the same name will be overridden.
     *
     * @param theController ControllerInterface
     * @return skatolo
     */
    public Skatolo register(Object theObject, String theIndex, ControllerInterface<?> theController) {
        String address = "";
        if (theObject == papplet) {
            address = (theController.getName().startsWith("/")) ? "" : "/";
            address += theController.getName();
        } else {
            address = (((theIndex.length() == 0) || theIndex.startsWith("/")) ? "" : "/");
            address += theIndex;
            address += (theController.getName().startsWith("/") ? "" : "/");
            address += theController.getName();
        }
        theController.setAddress(address);
        if (checkName(theController.getAddress())) {
            /*
             * in case a controller with the same name already exists, will be deleted
             */
            remove(theController.getAddress());
        }
        /* add the controller to the controller map */
        controllerMap.put(theController.getAddress(), theController);

        /* update the properties' controller address */
        List<ControllerProperty> ps = getProperties().get(theController);
        if (ps != null) {
            for (ControllerProperty p : ps) {
                p.setAddress(theController.getAddress());
            }
        }
        /* initialize the controller */

        theController.init();

        /*
         * handle controller plugs and map controllers to its reference objects if applicable.
         */
        if (theController instanceof Controller<?>) {
            if (theObject == null) {
                theObject = papplet;
            }
            if (!theObject.equals(papplet)) {
                ((Controller<?>) ((Controller<?>) theController).unplugFrom(papplet)).plugTo(theObject);
            }

            if (!objectToControllerMap.containsKey(theObject)) {
                objectToControllerMap.put(theObject, new ArrayList<ControllerInterface<?>>());
            }
            objectToControllerMap.get(theObject).add(theController);
        } else {
            /*
             * if theController is of type ControllerGroup, map accordingly here.
             */
        }
        return this;
    }

    /**
     * Returns a List of all controllers currently registered.
     *
     * @return List<ControllerInterface<?>>
     */
    public List<ControllerInterface<?>> getAll() {
        return new ArrayList<ControllerInterface<?>>(controllerMap.values());
    }

    /**
     * Returns a list of controllers or groups of a particular type. The
     * following example will return a list of registered Bangs only:<br />
     * <code><pre>
     * List<Bang> list = skatolo.getAll(Bang.class);
     * println(list);
     * for(Bang b:list) {
     *   b.setColorForeground(color(255,255,0));
     * }
     * </pre></code> Here the foreground color of all Bangs is changed to
     * yellow.
     *
     * @param <T>
     * @param theClass A class that extends ControllerInterface, which applies
     * to all Controllers and ControllerGroups
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> theClass) {
        ArrayList<T> l = new ArrayList<T>();
        for (ControllerInterface ci : controllerMap.values()) {
            if (ci.getClass() == theClass || ci.getClass().getSuperclass() == theClass) {
                l.add((T) ci);
            }
        }
        return l;
    }

    protected void deactivateControllers() {
        for (Textfield t : getAll(Textfield.class)) {
            t.setFocus(false);
        }
    }

    private String checkAddress(String theName) {
        if (!theName.startsWith("/")) {
            return "/" + theName;
        }
        return theName;
    }

    /**
     * @excude
     */
    public void printControllerMap() {
        List<String> strs = new ArrayList<String>();
        System.out.println("============================================");
        for (Iterator it = controllerMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            strs.add(key + " = " + value);
        }
        Collections.sort(strs);
        for (String s : strs) {
            System.out.println(s);
        }
        System.out.println("============================================");
    }

    /**
     * removes a controller by instance.
     *
     * TODO Fix this. this only removes the reference to a controller from the
     * controller map but not its children, fatal for controller groups!
     *
     * @param theController ControllerInterface
     */
    protected void remove(ControllerInterface<?> theController) {
        controllerMap.remove(theController.getAddress());
    }

    /**
     * removes a skatolo element such as a controller, group, or tab by name.
     *
     * @param theString String
     */
    public void remove(String theName) {
        String address = checkAddress(theName);

        if (getController(address) != null) {
            getController(address).remove();
        }

        if (getGroup(address) != null) {
            getGroup(address).remove();
        }

        for (int i = 0; i < controlWindow.getTabs().size(); i++) {
            if (controlWindow.getTabs().get(i).getAddress().equals(address)) {
                controlWindow.getTabs().get(i).remove();
            }
        }
        controllerMap.remove(address);
    }

    public ControllerInterface<?> get(String theName) {
        String address = checkAddress(theName);
        if (controllerMap.containsKey(address)) {
            return controllerMap.get(address);
        }
        return null;
    }

    public <C> C get(Class<C> theClass, String theName) {
        for (ControllerInterface<?> ci : controllerMap.values()) {
            if (ci.getClass() == theClass || ci.getClass().getSuperclass() == theClass) {
                return (C) get(theName);
            }
        }
        return null;
    }

    /**
     * @exclude @see skatolo.skatolo#getAll(Class)
     * @return List<ControllerInterface>
     */
    public List<ControllerInterface<?>> getList() {
        LinkedList<ControllerInterface<?>> l = new LinkedList<ControllerInterface<?>>();
        l.addAll(controlWindow.getTabs().get());
        l.addAll(getAll());
        return l;
    }

    public float getValue(String theIndex) {
        Controller c = getController(theIndex);
        if (c != null) {
            return c.getValue();
        }
        return Float.NaN;
    }

    public Controller<?> getController(String theName) {
        String address = checkAddress(theName);
        if (controllerMap.containsKey(address)) {
            if (controllerMap.get(address) instanceof Controller<?>) {
                return (Controller<?>) controllerMap.get(address);
            }
        }
        return null;
    }

    public ControllerGroup<?> getGroup(String theGroupName) {
        String address = checkAddress(theGroupName);
        if (controllerMap.containsKey(address)) {
            if (controllerMap.get(address) instanceof ControllerGroup<?>) {
                return (ControllerGroup<?>) controllerMap.get(address);
            }
        }
        return null;
    }

    private boolean checkName(String theName) {
        if (controllerMap.containsKey(checkAddress(theName))) {
            Skatolo.logger().warning("Controller with name \"" + theName + "\" already exists. overwriting reference of existing controller.");
            return true;
        }
        return false;
    }

    public void moveControllersForObject(Object theObject, ControllerGroup<?> theGroup) {
        if (objectToControllerMap.containsKey(theObject)) {
            ArrayList<ControllerInterface<?>> cs = objectToControllerMap.get(theObject);
            for (ControllerInterface<?> c : cs) {
                ((Controller<?>) c).moveTo(theGroup);
            }
        }
    }

    public void move(Object theObject, ControllerGroup<?> theGroup) {
        moveControllersForObject(theObject, theGroup);
    }

    protected void clear() {
        controlWindow.clear();
        controllerMap.clear();
    }

    // TODO: find the use of this and comment. 
    /**
     * @exclude
     */
    public void pre() {
        Iterator<FieldChangedListener> itr = fieldChangedListenerMap.values().iterator();
        while (itr.hasNext()) {
            itr.next().update();
        }
    }

    /**
     * call draw() from your program when autoDraw is disabled. Only used for
     * autoDraw.
     *
     * @exclude
     */
    public void draw() {
        if (!isAutoDraw()) {
            controlWindow.draw();
        }
    }

    /**
     * call draw() from your program when autoDraw is disabled.
     *
     * @exclude
     */
    public void draw(PGraphics graphics) {
        if (!isAutoDraw()) {
            controlWindow.draw(graphics);
        }
    }
    
    public void setGraphics(PGraphics graphics){
        this.graphics = graphics;
    }

    /**
     * convenience method to access the main window (ControlWindow class).
     */
    public ControlWindow getWindow() {
        return getWindow(papplet);
    }

    // TODO: usefull ?
//    public void mouseEvent(MouseEvent theMouseEvent) {
//        getWindow().mouseEvent(theMouseEvent);
//    }
//
//    public void keyEvent(KeyEvent theKeyEvent) {
//        getWindow().keyEvent(theKeyEvent);
//    }
    
    
    /**
     * Add a Pointer with a specific ID (for multi-touch). 
     * @param id
     * @return the pointer to update manually, or with updatePointer()
     */
    public Pointer addPointer(int id) {
        return controlWindow.getPointers().addPointer(id);
    }

    public void updatePointer(int id, int x, int y) {
        try{
        controlWindow.getPointers().updatePointer(id, x, y);
        } catch (IllegalArgumentException exception){
            // TODO: better exception.
            // TODO: logging.
            System.out.println("Invalid pointer " + id + ". Update not done. ");
        }
    }
    
    public void updatePointerPress(int id, boolean pressed) {
        try{
        controlWindow.getPointers().updatePointer(id, pressed);
        } catch (IllegalArgumentException exception){
            // TODO: better exception.
            // TODO: logging.
            System.out.println("Invalid pointer " + id + ". Update not done. ");
        }
    }
    
    public void removePointer(int id){
        controlWindow.getPointers().removePointer(id);
    }
    
    public PointerList getPointers(){
        return controlWindow.getPointers();
    }
    
    public Collection<Pointer> getPointerList(){
        return controlWindow.getPointers().values();
    }

    public Pointer getMousePointer(){
        return controlWindow.getMousePointer();
    }
    
    public int getMouseX(){
        return controlWindow.getPointerX();
    }
    public int getMouseY(){
        return controlWindow.getPointerY();
    }
    
    /**
     * convenience method to check if the mouse (or pointer) is hovering over
     * any controller. only applies to the main window. To receive the mouseover
     * information for a ControlWindow use
     * getWindow(nameOfWindow).isMouseOver();
     */
    public boolean isMouseOver() {
        return getWindow(papplet).isMouseOver();
    }

    /**
     * convenience method to check if the mouse (or pointer) is hovering over a
     * specific controller. only applies to the main window. To receive the
     * mouseover information for a ControlWindow use
     * getWindow(nameOfWindow).isMouseOver(ControllerInterface<?>);
     */
    public boolean isMouseOver(ControllerInterface<?> theController) {
        return getWindow(papplet).isMouseOver(theController);
    }

    /**
     * convenience method to check if the mouse (or pointer) is hovering over a
     * specific controller. only applies to the main window. To receive the
     * mouseover information for a ControlWindow use
     * getWindow(nameOfWindow).getMouseOverList();
     */
    public List<ControllerInterface<?>> getMouseOverList() {
        return getWindow(papplet).getMouseOverList();
    }

    public ControlWindow getWindow(PApplet theApplet) {
        if (theApplet.equals(papplet)) {
            return controlWindow;
        }
        // TODO !!! check for another window in case theApplet is of type
        // PAppletWindow.
        return controlWindow;
    }

    /**
     * adds a Canvas to the default sketch window.
     *
     * @see skatolo.Canvas
     */
    public Skatolo addCanvas(Canvas theCanvas) {
        getWindow().addCanvas(theCanvas);
        return this;
    }

    public Skatolo removeCanvas(Canvas theCanvas) {
        getWindow().removeCanvas(theCanvas);
        return this;
    }

    public Skatolo setColor(CColor theColor) {
        setColorBackground(theColor.getBackground());
        setColorForeground(theColor.getForeground());
        setColorActive(theColor.getActive());
        setColorCaptionLabel(theColor.getCaptionLabel());
        setColorValueLabel(theColor.getValueLabel());
        return this;
    }

    public CColor getColor() {
        return color;
    }

    /**
     * sets the active state color of tabs and controllers, this cascades down
     * to all known controllers.
     */
    public Skatolo setColorActive(int theColor) {
        color.setActive(theColor);
        controlWindow.setColorActive(theColor);
        return this;
    }

    /**
     * sets the foreground color of tabs and controllers, this cascades down to
     * all known controllers.
     */
    public Skatolo setColorForeground(int theColor) {
        color.setForeground(theColor);
        controlWindow.setColorForeground(theColor);
        return this;
    }

    /**
     * sets the background color of tabs and controllers, this cascades down to
     * all known controllers.
     */
    public Skatolo setColorBackground(int theColor) {
        color.setBackground(theColor);
        controlWindow.setColorBackground(theColor);
        return this;
    }

    /**
     * sets the label color of tabs and controllers, this cascades down to all
     * known controllers.
     */
    public Skatolo setColorCaptionLabel(int theColor) {
        color.setCaptionLabel(theColor);
        controlWindow.setColorLabel(theColor);
        return this;
    }

    /**
     * sets the value color of controllers, this cascades down to all known
     * controllers.
     */
    public Skatolo setColorValueLabel(int theColor) {
        color.setValueLabel(theColor);
        controlWindow.setColorValue(theColor);
        return this;
    }

    /**
     * Enables/disables Controllers to be moved around when ALT-key is down and
     * mouse is dragged. Other key events are still available like ALT-h to hide
     * and show the controllers To disable all key events, use disableKeys()
     */
    public Skatolo setMoveable(boolean theFlag) {
        isMoveable = theFlag;
        return this;
    }

    /**
     * Checks if controllers are generally moveable
     *
     */
    public boolean isMoveable() {
        return isMoveable;
    }

    /**
     * Saves the current values of controllers into a default properties file
     *
     * @see skatolo.ControllerProperties
     */
    public boolean saveProperties() {
        return properties.save();
    }

    /**
     * Saves the current values of controllers into a file, the filepath is
     * given by parameter theFilePath.
     *
     * @see skatolo.ControllerProperties
     */
    public boolean saveProperties(String theFilePath) {
        return properties.saveAs(theFilePath);
    }

    public boolean saveProperties(String theFilePath, String... theSets) {
        return properties.saveAs(theFilePath, theSets);
    }

    /**
     * Loads properties from a default properties file and changes values of
     * controllers accordingly.
     *
     * @see skatolo.ControllerProperties
     * @return
     */
    public boolean loadProperties() {
        return properties.load();
    }

    /**
     * Loads properties from a properties file and changes the values of
     * controllers accordingly, the filepath is given by parameter theFilePath.
     *
     * @param theFilePath
     * @return
     */
    public boolean loadProperties(String theFilePath) {
        theFilePath = checkPropertiesPath(theFilePath);
        File f = new File(theFilePath);
        if (f.exists()) {
            return properties.load(theFilePath);
        }
        theFilePath = checkPropertiesPath(theFilePath + ".ser");
        f = new File(theFilePath);
        if (f.exists()) {
            return properties.load(theFilePath);
        }
        logger.info("Properties File " + theFilePath + " does not exist.");
        return false;
    }

    public String checkPropertiesPath(String theFilePath) {
        theFilePath = (theFilePath.startsWith("/") || theFilePath.startsWith(".")) ? theFilePath : papplet.sketchPath(theFilePath);
        return theFilePath;
    }

    /**
     * @exclude @param theFilePath
     * @return
     */
    public boolean loadLayout(String theFilePath) {
        theFilePath = checkPropertiesPath(theFilePath);
        File f = new File(theFilePath);
        if (f.exists()) {
            getLayout().load(theFilePath);
            return true;
        }
        logger.info("Layout File " + theFilePath + " does not exist.");
        return false;
    }

    /**
     * @exclude @param theFilePath
     */
    public void saveLayout(String theFilePath) {
        getLayout().save(theFilePath);
    }

    /**
     * Returns the current version of skatolo
     *
     * @return String
     */
    public String version() {
        return VERSION;
    }

    /**
     * shows all controllers and tabs in your sketch.
     *
     * @see skatolo.skatolo#isVisible()
     * @see skatolo.skatolo#hide()
     */
    public void show() {
        controlWindow.show();
    }

    public Skatolo setBroadcast(boolean theValue) {
        controlBroadcaster.broadcast = theValue;
        return this;
    }

    /**
     * returns true or false according to the current visibility flag.
     *
     * @see skatolo.skatolo#show()
     * @see skatolo.skatolo#hide()
     */
    public boolean isVisible() {
        return controlWindow.isVisible();
    }

    /**
     * hide all controllers and tabs inside your sketch window.
     *
     * @see skatolo.skatolo#show()
     * @see skatolo.skatolo#isVisible()
     */
    public void hide() {
        controlWindow.hide();
    }

    /**
     * forces all controllers to update.
     *
     * @see skatolo.skatolo#isUpdate()
     * @see skatolo.skatolo#setUpdate()
     */
    public void update() {
        controlWindow.update();
    }

    /**
     * checks if automatic updates are enabled. By default this is true.
     *
     * @see skatolo.skatolo#update()
     * @see skatolo.skatolo#setUpdate(boolean)
     * @return
     */
    public boolean isUpdate() {
        return isUpdate;
    }

    /**
     * changes the update behavior according to parameter theFlag
     *
     * @see skatolo.skatolo#update()
     * @see skatolo.skatolo#isUpdate()
     * @param theFlag
     */
    public void setUpdate(boolean theFlag) {
        isUpdate = theFlag;
        controlWindow.setUpdated(theFlag);
    }

    @Deprecated
    public boolean setFont(int theBitFontIndex) {
        logger.warning("BitFont is now of type PFont, use setFont(PFont) instead.");
        return false;
    }

    public boolean setFont(ControlFont theControlFont) {
        controlFont = theControlFont;
        isControlFont = true;
        updateFont(controlFont);
        return isControlFont;
    }

    public boolean setFont(PFont thePFont, int theFontSize) {
        controlFont = new ControlFont(thePFont, theFontSize);
        isControlFont = true;
        updateFont(controlFont);
        return isControlFont;
    }

    public boolean setFont(PFont thePFont) {
        controlFont = new ControlFont(thePFont);
        isControlFont = true;
        updateFont(controlFont);
        return isControlFont;
    }

    protected void updateFont(ControlFont theControlFont) {
        controlWindow.updateFont(theControlFont);
    }

    public ControlFont getFont() {
        return controlFont;
    }

    /**
     * disables shortcuts such as alt-h for hiding/showing controllers
     *
     */
    public void disableShortcuts() {
        shortcutsEnabled = false;
    }

    /**
     * enables shortcuts.
     */
    public void enableShortcuts() {
        shortcutsEnabled = true;
    }

    public boolean areShortcutsEnabled() {
        return shortcutsEnabled;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip theTooltip) {
        tooltip = theTooltip;
    }

    public void setMouseWheelRotation(int theRotation) {
        getWindow().setMouseWheelRotation(theRotation);
    }

    /**
     * skatolo.begin() and skatolo.end() are mechanisms to auto-layout controllers, see
     * the skatolobeginEnd example.
     */
    public ControllerGroup<?> begin() {
        // TODO replace controlWindow.tab("default") with
        // controlWindow.tabs().get(1);
        return begin(controlWindow.getTab("default"));
    }

    public ControllerGroup<?> begin(ControllerGroup<?> theGroup) {
        setCurrentPointer(theGroup);
        return theGroup;
    }

    public ControllerGroup<?> begin(int theX, int theY) {
        // TODO replace controlWindow.tab("default") with
        // controlWindow.tabs().get(1);
        return begin(controlWindow.getTab("default"), theX, theY);
    }

    public ControllerGroup<?> begin(ControllerGroup<?> theGroup, int theX, int theY) {
        setCurrentPointer(theGroup);
        theGroup.autoPosition.x = theX;
        theGroup.autoPosition.y = theY;
        theGroup.autoPositionOffsetX = theX;
        return theGroup;
    }

    public ControllerGroup<?> begin(ControlWindow theWindow) {
        return begin(theWindow.getTab("default"));
    }

    public ControllerGroup<?> begin(ControlWindow theWindow, int theX, int theY) {
        return begin(theWindow.getTab("default"), theX, theY);
    }

    public ControllerGroup<?> end(ControllerGroup<?> theGroup) {
        releaseCurrentPointer(theGroup);
        return theGroup;
    }

    /**
     * skatolo.begin() and skatolo.end() are mechanisms to auto-layout controllers, see
     * the skatolobeginEnd example.
     */
    public ControllerGroup<?> end() {
        return end(controlWindow.getTab("default"));
    }

    public void addPositionTo(int theX, int theY, List<ControllerInterface<?>> theControllers) {
        PVector v = new PVector(theX, theY);
        for (ControllerInterface<?> c : theControllers) {
            c.setPosition(PVector.add(c.getPosition(), v));
        }
    }

    public void addPositionTo(int theX, int theY, ControllerInterface<?>... theControllers) {
        addPositionTo(theX, theY, Arrays.asList(theControllers));
    }

    /**
     * disposes and clears all skatolo elements. When running in applet mode,
     * opening new tabs or switching to another tab causes the applet to call
     * dispose(). therefore dispose() is disabled when running in applet mode.
     * TODO implement better dispose handling for applets.
     *
     * @exclude
     */
    public void dispose() {
        if (!isApplet) {
            clear();
        }
    }
    
    public void delete(){
        this.papplet.unregisterMethod("pre", this);
        this.papplet.unregisterMethod("draw", this);
        this.papplet.unregisterMethod("dispose", this);
        controlWindow.delete();
    }

    /* add Objects with Annotation */
    public static Logger logger() {
        return logger;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Invisible {
    }

    public @Retention(RetentionPolicy.RUNTIME)
    @interface Layout {
    }

}

// new controllers
// http://www.cambridgeincolour.com/tutorials/photoshop-curves.htm
// http://images.google.com/images?q=synthmaker
// http://en.wikipedia.org/wiki/Pie_menu
//
// inspiration
// http://www.explodingart.com/arb/Andrew_R._Brown/Code/Code.html
// projects using skatolo
// http://www.danielsauter.com/showreel.php?id=59
// http://www.raintone.com/2009/03/fractalwavetables-v2/
// http://www.flickr.com/photos/jrosenk/3631041263/
// http://www.gtdv.org/
// http://0p0media.com/aurapixlab/
// http://www.introspector.be/index.php?/research/dook/
// http://createdigitalmotion.com/2009/11/29/processing-beats-keyframing-typestar-karaoke-machine-generates-kinetic-lyrics/
// http://www.yonaskolb.com/predray
// http://www.creativeapplications.net/processing/cop15-identity-processing/
// http://vimeo.com/9158064 + http://vimeo.com/9153342 processing-city,
// sandy-city
//
// http://vimeo.com/24358631 http://diplome.canalblog.com/
// http://www.yannickmathey.com/prototyp
// http://vimeo.com/24665893 http://jcnaour.com/#Kinect-Graffiti-
// http://www.onformative.com/work/zero-visuals/
// http://www.youtube.com/watch?v=DIPgRMtYqGQ
// TODO
// (1) file dialog:
// http://java.sun.com/j2se/1.5.0/docs/api/java/awt/FileDialog.html
// (2) add ControlIcon for custom icons with PImage
// gui addons inspiration.
// http://www.futureaudioworkshop.com/
//
// new color schemes http://ethanschoonover.com/solarized

