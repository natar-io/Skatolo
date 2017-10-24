# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'skatolo/version'
require 'rake'

Gem::Specification.new do |s|
  s.name        = 'skatolo'
  s.version     = Skat::VERSION
  s.date        = '2017-07-25'
  s.summary     = "Skatolo - Gui library for jruby_art"
  s.description = "Based on ControlP5 for new purposes and JRubyArt. Only works with JRubyArt and propane."
  s.authors     = ["Jeremy Laviole"]
  s.email       = 'laviole@rea.lity.tech'
  s.add_runtime_dependency "jruby_art",  '~> 1.2', '>= 1.2.4'
  s.files       = FileList['lib/**/*',  'examples/**/*'].exclude(/jar/).to_a
  s.files << 'lib/skatolo.jar'
  s.homepage    = 'https://github.com/Rea-lity-Tech/skatolo'
  s.license     = 'LGPL-3.0'
end
