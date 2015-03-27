# -*- coding: utf-8 -*-

require 'ruby-processing' 
require 'jruby/core_ext'

Processing::App::SKETCH_PATH = __FILE__   unless defined? Processing::App::SKETCH_PATH



class MyApp < Processing::App

  load_library :controlP5
  include_package 'fr.inria.controlP5'

  attr_reader :cp5
  attr_accessor :once, :button

  def setup
    size 800, 800, OPENGL
    @bidon = Bidon.new
    @cp5 = ControlP5.new(self, @bidon) 
  end

  def draw 
    background 0

    if @once == nil 
      puts "add Button" 
       @button = cp5.addButton("button")
        .setPosition(40, 200)
        .setSize(280, 40)
      @once = true
    end

  end

  def test
    puts "test"
  end

end


class Bidon 
#  include_package 'fr.inria.controlP5.events'

  java_signature 'void controlEvent(fr.inria.controlP5.events.ControlEvent)'
  def controlEvent(controlEvent)
    puts "Event"
  end
  Bidon.become_java!
end



MyApp.new  unless defined? $app
