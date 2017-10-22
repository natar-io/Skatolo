require 'jruby/core_ext'
require_relative 'skatolo.jar'

%w[
    Bang Button HoverButton Slider Textfield HoverToggle Numberbox
  ].each do |widget|
  java_import "tech.lity.rea.skatolo.gui.controllers.#{widget}"
end

# A reflective Event Handler
class EventHandler

  def initialize(skatolo)
    @skatolo = skatolo
  end

  java_signature 'void controlEvent(tech.lity.rea.skatolo.events.ControlEvent)'
  def controlEvent(controlEvent)
    @skatolo.send_event_to_sketch controlEvent
  end

  EventHandler.become_java!
end

class Skatolo < Java::TechLityReaSkatolo::Skatolo

  def initialize(applet, events_object = nil)
    @event_handler = EventHandler.new self
    @events_object = events_object unless events_object.nil?
    @events_object = applet if events_object.nil?
    super(applet, @event_handler)
    # @applet = applet
  end

  def update
    getAll.each do |controller|
      name = controller.name
      ## There is a method with this name...
      return if @events_object.respond_to?(name + "_value")
      # puts "please declare a method for " + name
      create_getter_for name
      create_setter_for name
    end
  end

  # Event function...
  def send_event_to_sketch(controlEvent)
    controller = get(controlEvent.getName)
    name = controlEvent.getName
    value = controlEvent.getValue
    string_value = controlEvent.getStringValue
    # puts controller.object_id.to_s
    ## There is a method with this name...
    return unless @events_object.respond_to? name
    ## Buttons usually, not arguments.
    return @events_object.send(name) if event_class? controller
    ## Sliders, check arity
    ## try to send the value
    return @events_object.send(name, value) if value_class? controller
    ## Text
    ## Sliders, check arity
    return unless string_value_class? controller
    ## try to send the value
    @events_object.send(name, string_value)
  end

  def create_getter_for(name)
    controller = get(name)
    return if event_class? controller
    value = get_controller_value(controller)
    @events_object.create_method(name + "_value") do
      controller = @skatolo.get(name)
      @skatolo.get_controller_value controller
    end
  end

  def create_setter_for(name)
    controller = get(name)
    return if event_class? controller
    value = get_controller_value(controller)
    # puts "Creating a setter for " + name
    @events_object.create_method(name + "_value=") do |value|
      controller = @skatolo.get(name)
      @skatolo.set_controller_value controller, value
    end
  end

  def get_controller_value(controller)
    return 1 if event_class? controller
    return controller.getValue if value_class? controller
    return unless string_value_class? controller
    controller.getStringValue
  end

  def set_controller_value(controller, value)
    return if event_class? controller
    return controller.setValue value  if value_class? controller
    return unless string_value_class? controller
    controller.setStringValue value
  end

  def event_class?(object)
    [Button, HoverButton, Bang].any? { |klass| object.java_kind_of? klass }
  end

  def value_class?(object_class)
    [Slider, HoverToggle, Numberbox].any? { |klass| object.java_kind_of? klass }
  end

  def string_value_class?(object_class)
    object.java_kind_of? Textfield
  end
end
