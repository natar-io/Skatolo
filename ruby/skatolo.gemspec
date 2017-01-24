Gem::Specification.new do |s|
  s.name        = 'skatolo'
  s.version     = '1.1.0'
  s.date        = '2017-01-19'
  s.summary     = "Skatolo - Gui library for jruby_art"
  s.description = "Based on ControlP5 for new purposes and JRubyArt. Only works with JrubyArt and require Skatolo library to be installed in Processing. "
  s.authors     = ["Jeremy Laviole"]
  s.email       = 'laviole@rea.lity.tech'
  s.add_runtime_dependency "jruby_art",  '~> 1.2', '>= 1.2.4'
  s.files       = ["lib/skatolo.rb"]
  s.homepage    = 'https://github.com/Rea-lity-Tech/skatolo'
  s.license     = 'LGPL'
end