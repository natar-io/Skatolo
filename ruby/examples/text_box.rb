require 'skatolo'
# In this simple sketch we attach two buttons to skatolo in the regular way,
# named buttons 'press_me' and 'reset' thanks to some fancy metaprogramming
# we can create methods :press_me and :reset for the buttons
attr_reader :skatolo, :back_color, :font

def settings
  size(400, 300)
end

def setup
  sketch_title 'Skatolo Text Box'
  @skatolo = Skatolo.new(self)
  skatolo.add_button('press_me')
	       .set_position(10, 10)
         .set_size(50, 20)
	       .set_label('Press Me!')
  @input = skatolo.add_textfield('input_name')
	       .set_position(10, 50)
         .set_size(100, 20)
   skatolo.update # this step is important
   @back_color = color(200, 0, 200)
   text_font(create_font('monospace', 30))
end

def create_method(name, &block)
  self.class.send(:define_method, name, &block)
end

def draw
  background(back_color)
  if input_name_value.length > 0
    text("Hello #{input_name_value}", 10,120)
    no_loop
  end
end
