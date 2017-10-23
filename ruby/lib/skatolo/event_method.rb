# boilerplate code for skatolo sketch
module MagicMethod

  def create_method(name, &block)
    self.class.send(:define_method, name, &block)
  end
end
