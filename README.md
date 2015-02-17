

## GUI library for the programming environment processing. 

ControlP5 is a GUI and controller library for processing that can be used in application, applet and android mode. Controllers such as Sliders, Buttons, Toggles, Knobs, Textfields, RadioButtons, Checkboxes amongst others are easily added to a processing sketch. They can be arranged in separate control windows, and can be organized in tabs or groups.


## Important notes 

This is a work in progress which just started. It means that the name might change if it becomes really a "fork" of ControlP5. 

For now the focus is to implement a new feature to handle multiple “windows” within one PApplet. For the average Processing user, the use would be the same. For advanced users it means that you could attach a ControlP5 object to a specific PGraphics.  

Another focus is to dive into this library to look at the introspection features and port them to other languages such as [ruby](https://github.com/jashkenas/ruby-processing) or [python](http://py.processing.org/) modes for Processing. Consequently, these other flavours of Processing could benefit from the ease of use of ControlP5 ! 


# Features 

## Automatic controller-event detection

ControlP5 offers a range of controllers that allow you to easily change and adjust values while your sketch is running. Each controller is identified by a unique name assigned when creating a controller. ControlP5 locates variables and functions inside your sketch and will link controllers to matching variables or functions automatically. Controller changes can easily be captured within your sketch by implementing the controlEvent function.

## Show, hide, load, save

Controllers that have been added to your sketch can be arranged in tabs and groups to keep your controller sets organized. All controllers are drawn on top of a processing sketch by default. Several key combinations allow you to show and hide the user interface, and to saved and loaded ControlP5 properties

## Examples

An extensive list of examples can be found at www.sojamo.de/libraries/controlP5/#examples.