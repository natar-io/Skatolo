---
layout: project
section-type: project
title: SVGExtended
---
## SVGExtended

SVG support for Processing is quite flawed, this library exends a bit the possibilities.

## Features

Images are now loaded and displayed. It is known to work for both link images, when the image is just a link in the svg code, and also for embedded images.

Text is also supported. The support is not perfect but it works with many font. We generate the font on the fly, and then use it. The font quality can be increased by setting the static float ```PShapeSVGExtended.TEXT_QUALITY```. The higher the value, the btter the quality. Default is 1.0.

How to use:

{% highlight java %}
void settings(){
    size(300, 300);
}
void setup(){
    PShape shape = new PShapeSVGExtended("file.svg")
}

void draw(){
    image(shape, 100, 100, 100, 100);
}
{% endhighlight %}


## Links

* GitHub page: [https://github.com/Rea-lity-Tech/SVGExtended](https://github.com/Rea-lity-Tech/SVGExtended)
* Download the latest version: [SVGExtended.tar.gz](https://github.com/Rea-lity-Tech/SVGExtended/releases/download/v1.0/SVGExtended.tgz)
* Get the [source code](https://github.com/Rea-lity-Tech/SVGExtended/archive/v1.0.zip).
